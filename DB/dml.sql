USE esun_seat_system;

INSERT INTO seating_chart (floor_no, seat_no) VALUES
(1,1),(1,2),(1,3),(1,4),
(2,1),(2,2),(2,3),(2,4),
(3,1),(3,2),(3,3),(3,4),
(4,1),(4,2),(4,3),(4,4);

INSERT INTO employee (emp_id, name, email, floor_seat_seq) VALUES
('12006', '王小明', '12006@esun.com', 3),
('16142', '陳小華', '16142@esun.com', 7),
('13040', '李大華', '13040@esun.com', 9),
('17081', '林美玲', '17081@esun.com', 10),
('11221', '吳志強', '11221@esun.com', 12),
('16722', '張雅婷', '16722@esun.com', 15),
('10001', '周阿德', '10001@esun.com', NULL),
('10002', '許小雯', '10002@esun.com', NULL);