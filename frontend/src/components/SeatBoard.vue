<template>
  <div class="container">
    <h1>員工座位安排系統</h1>

    <div class="toolbar">
      <label for="employee">請選擇員工：</label>
      <select id="employee" v-model="selectedEmpId">
        <option value="">-- 請選擇員工 --</option>
        <option
            v-for="emp in employees"
            :key="emp.empId"
            :value="emp.empId"
        >
          {{ emp.empId }} - {{ emp.name }}
        </option>
      </select>

      <button class="submit-btn" @click="submitSelectedSeat" :disabled="!pendingSeat">
        送出
      </button>
    </div>

    <div v-for="floor in groupedSeats" :key="floor.floorNo" class="floor-block">
      <h2>{{ floor.floorNo }} 樓</h2>
      <div class="seat-grid">
        <div
            v-for="seat in floor.seats"
            :key="seat.floorSeatSeq"
            class="seat-card"
            :class="seatClass(seat)"
            @click="handleSeatClick(seat)"
        >
          {{ seat.floorNo }}樓：座位{{ seat.seatNo }}
          <span v-if="seat.empId">【員編: {{ seat.empId }}】</span>
        </div>
      </div>
    </div>

    <div class="legend">
      <div><span class="legend-box empty"></span>空位</div>
      <div><span class="legend-box occupied"></span>已佔用</div>
      <div><span class="legend-box pending"></span>請選擇</div>
    </div>

    <p class="hint">已佔用座位可直接點擊清除；空位需先選員工，再點擊座位。</p>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { assignSeat, clearSeat, getSeatMap } from '../api/seatApi'

const seats = ref([])
const employees = ref([])
const selectedEmpId = ref('')
const pendingSeat = ref(null)

const loadData = async () => {
  try {
    const response = await getSeatMap()
    seats.value = response.data.seats
    employees.value = response.data.employees
    pendingSeat.value = null
  } catch (error) {
    alert(error.response?.data?.message || '載入資料失敗')
  }
}

onMounted(() => {
  loadData()
})

const groupedSeats = computed(() => {
  const map = {}

  for (const seat of seats.value) {
    if (!map[seat.floorNo]) {
      map[seat.floorNo] = {
        floorNo: seat.floorNo,
        seats: []
      }
    }
    map[seat.floorNo].seats.push(seat)
  }

  return Object.values(map).sort((a, b) => a.floorNo - b.floorNo)
})

const seatClass = (seat) => {
  if (pendingSeat.value && pendingSeat.value.floorSeatSeq === seat.floorSeatSeq) {
    return 'pending'
  }
  if (seat.empId) {
    return 'occupied'
  }
  return 'empty'
}

const handleSeatClick = async (seat) => {
  if (seat.empId) {
    const yes = confirm(`確定要清除 ${seat.floorNo}樓 座位${seat.seatNo} 的員工資料嗎？`)
    if (!yes) return

    try {
      await clearSeat({ floorSeatSeq: seat.floorSeatSeq })
      await loadData()
    } catch (error) {
      alert(error.response?.data?.message || '清除失敗')
    }
    return
  }

  if (!selectedEmpId.value) {
    alert('請先選擇員工')
    return
  }

  pendingSeat.value = seat
}

const submitSelectedSeat = async () => {
  if (!selectedEmpId.value || !pendingSeat.value) {
    alert('請先選擇員工與座位')
    return
  }

  try {
    await assignSeat({
      empId: selectedEmpId.value,
      floorSeatSeq: pendingSeat.value.floorSeatSeq
    })
    alert('座位更新成功')
    selectedEmpId.value = ''
    pendingSeat.value = null
    await loadData()
  } catch (error) {
    alert(error.response?.data?.message || '送出失敗')
  }
}
</script>

<style scoped>
.container {
  max-width: 1100px;
  margin: 20px auto;
  font-family: Arial, sans-serif;
  background-color: #ffffff;
  color: #222222;
  padding: 20px;
}

h1 {
  text-align: center;
  margin-bottom: 24px;
  color: #222222;
}

.toolbar {
  display: flex;
  gap: 12px;
  align-items: center;
  margin-bottom: 24px;
  flex-wrap: wrap;
  color: #222222;
}

select {
  padding: 8px;
  min-width: 240px;
}

.submit-btn {
  padding: 8px 16px;
  background: #0b5ed7;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

.submit-btn:disabled {
  background: #a0a0a0;
  cursor: not-allowed;
}

.floor-block {
  margin-bottom: 24px;
}

.floor-block h2 {
  color: #222222;
}

.seat-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
}

.seat-card {
  padding: 18px 12px;
  border-radius: 8px;
  text-align: center;
  font-weight: bold;
  cursor: pointer;
  user-select: none;
}

.empty {
  background-color: #e9e9e9;
  color: #222222;
}

.occupied {
  background-color: #ff1d1d;
  color: #ffffff;
}

.pending {
  background-color: #7cf17c;
  color: #222222;
}

.legend {
  margin-top: 20px;
  display: flex;
  gap: 20px;
  color: #222222;
}

.legend-box {
  display: inline-block;
  width: 24px;
  height: 16px;
  margin-right: 6px;
  vertical-align: middle;
  border-radius: 4px;
}

.hint {
  margin-top: 16px;
  color: #555555;
}
</style>