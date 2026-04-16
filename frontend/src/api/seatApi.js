import axios from 'axios'

const api = axios.create({
    baseURL: 'http://localhost:8080/api/seats'
})

export const getSeatMap = () => api.get('')
export const assignSeat = (payload) => api.post('/assign', payload)
export const clearSeat = (payload) => api.post('/clear', payload)