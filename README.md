# 員工座位安排系統（Seat System）

## 執行環境與版本需求

請先確認已安裝以下環境：

### 必要環境

* JDK 17
* MySQL Server 8
* MySQL Workbench
* Node.js 20+
* npm 10+
* IntelliJ IDEA（建議）

### 本專案使用技術

* Java 17
* Spring Boot 3.5.13
* Maven
* MySQL 8
* Vue 3
* Vite
* Axios

---

## 如何執行專案

### 下載專案

```bash
git clone https://github.com/BARRY100000/seat-system
cd seat-system
```

---

### 初始化資料庫

開啟 MySQL Workbench，連線至本機 MySQL，依序執行以下 SQL 檔案：

```
DB/ddl.sql
DB/dml.sql
DB/sp.sql
```

#### 此步驟會完成：

* 建立資料庫 `esun_seat_system`
* 建立 `employee`、`seating_chart` 資料表
* 匯入測試資料
* 建立 Stored Procedure

---

### 設定後端資料庫連線

編輯以下檔案：

```
src/main/resources/application.yaml
```

修改為你的 MySQL 帳號密碼：

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/esun_seat_system?useSSL=false&serverTimezone=Asia/Taipei&characterEncoding=utf8
    username: root
    password: 你的MySQL密碼
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

---

### 啟動後端（Spring Boot）

使用 IntelliJ IDEA 開啟專案，執行：

```
SeatSystemApplication.java
```

#### 預設後端網址：

```
http://localhost:8080
```

#### 測試 API：

```
http://localhost:8080/api/seats
```

若回傳 JSON，表示啟動成功。

---

### 啟動前端

```bash
cd frontend
npm install
npm install axios
npm run dev
```

#### 前端網址：

```
http://localhost:5173
```

---

### 開啟系統

在瀏覽器輸入：

```
http://localhost:5173
```

即可進入系統。

---

## 執行順序

請務必依照以下順序操作：

1. 安裝必要環境
2. 執行 `DB/ddl.sql`
3. 執行 `DB/dml.sql`
4. 執行 `DB/sp.sql`
5. 修改 `application.yaml`
6. 啟動後端（Spring Boot）
7. 進入 `frontend` 執行 `npm install`
8. 執行 `npm run dev`
9. 開啟 `http://localhost:5173`

---

## 安全性處理說明

- 後端透過 JdbcTemplate 參數化呼叫 Stored Procedure，避免 SQL Injection
- 使用 Validation 驗證輸入格式，例如員工編號固定 5 碼
- 使用 HtmlSanitizer 對輸入資料做基本清洗，降低 XSS 風險
- 前端使用 Vue 模板插值顯示資料，未使用 v-html 直接渲染 HTML

---

## 功能展示

https://github.com/user-attachments/assets/f50dcb4b-f7d8-4733-9c10-95b775aa52c2






