CREATE DATABASE IF NOT EXISTS esun_seat_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE esun_seat_system;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS seating_chart;

CREATE TABLE seating_chart (
    floor_seat_seq BIGINT PRIMARY KEY AUTO_INCREMENT,
    floor_no INT NOT NULL,
    seat_no INT NOT NULL,
    CONSTRAINT uk_floor_seat UNIQUE (floor_no, seat_no)
);

CREATE TABLE employee (
    emp_id CHAR(5) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    floor_seat_seq BIGINT NULL,
    CONSTRAINT fk_employee_seat
      FOREIGN KEY (floor_seat_seq) REFERENCES seating_chart(floor_seat_seq)
);