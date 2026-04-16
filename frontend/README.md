# 員工座位安排系統

本專案為玉山銀行 Java 後端工程師實作題，實作人資部門使用的員工座位安排系統。系統可顯示各樓層座位、安排員工座位、清除已佔用座位，並透過 Stored Procedure 存取資料庫。題目要求包含 Vue.js 前端、Spring Boot 後端、RESTful API、Maven/Gradle、Stored Procedure、Transaction、DDL/DML 放置於 DB 資料夾，並考慮 SQL Injection 與 XSS。

## 功能說明

- 顯示各樓層座位資訊
- 使用不同顏色區分空位、已佔用座位、待選擇座位
- 已佔用座位顯示員工編號
- 使用下拉選單選擇員工
- 每位員工只能佔用一個座位
- 可將已佔用座位清除為空位
- 按送出後將座位資訊寫回資料庫
- 透過 Stored Procedure 查詢與更新資料

## 技術棧

### Frontend
- Vue 3
- Vite
- Axios

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Maven

### Database
- MySQL 8
- Stored Procedure

## 專案結構

```text
seat-system/
├─ DB/
│  ├─ ddl.sql
│  ├─ dml.sql
│  └─ sp.sql
├─ src/
│  └─ main/
│     ├─ java/com/esun/seatsystem/
│     │  ├─ config/
│     │  ├─ controller/
│     │  ├─ dto/
│     │  ├─ entity/
│     │  ├─ exception/
│     │  ├─ repository/
│     │  ├─ service/
│     │  ├─ util/
│     │  └─ SeatSystemApplication.java
│     └─ resources/
│        └─ application.yaml
├─ frontend/
│  ├─ src/
│  │  ├─ api/
│  │  ├─ components/
│  │  ├─ App.vue
│  │  ├─ main.js
│  │  └─ style.css
│  ├─ package.json
│  └─ vite.config.js
├─ pom.xml
└─ README.md