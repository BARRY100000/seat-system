USE esun_seat_system;

DROP PROCEDURE IF EXISTS sp_get_all_seats;
DROP PROCEDURE IF EXISTS sp_get_all_employees;
DROP PROCEDURE IF EXISTS sp_assign_seat;
DROP PROCEDURE IF EXISTS sp_clear_seat;

DELIMITER $$

CREATE PROCEDURE sp_get_all_seats()
BEGIN
SELECT
    s.floor_seat_seq,
    s.floor_no,
    s.seat_no,
    e.emp_id,
    e.name
FROM seating_chart s
         LEFT JOIN employee e
                   ON s.floor_seat_seq = e.floor_seat_seq
ORDER BY s.floor_no, s.seat_no;
END $$

CREATE PROCEDURE sp_get_all_employees()
BEGIN
SELECT
    emp_id,
    name,
    email,
    floor_seat_seq
FROM employee
ORDER BY emp_id;
END $$

CREATE PROCEDURE sp_assign_seat(
    IN p_emp_id CHAR(5),
    IN p_floor_seat_seq BIGINT
)
BEGIN
    DECLARE v_existing_emp CHAR(5);

SELECT emp_id INTO v_existing_emp
FROM employee
WHERE floor_seat_seq = p_floor_seat_seq
    LIMIT 1;

IF v_existing_emp IS NOT NULL AND v_existing_emp <> p_emp_id THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = '該座位已被其他員工佔用';
END IF;

UPDATE employee
SET floor_seat_seq = NULL
WHERE emp_id = p_emp_id;

UPDATE employee
SET floor_seat_seq = p_floor_seat_seq
WHERE emp_id = p_emp_id;
END $$

CREATE PROCEDURE sp_clear_seat(
    IN p_floor_seat_seq BIGINT
)
BEGIN
UPDATE employee
SET floor_seat_seq = NULL
WHERE floor_seat_seq = p_floor_seat_seq;
END $$

DELIMITER ;