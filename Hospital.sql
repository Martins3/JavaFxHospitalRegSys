CREATE TABLE Hospital.Department
(
    num char(6) PRIMARY KEY NOT NULL,
    name char(10) NOT NULL,
    abbr char(8) NOT NULL
);
CREATE UNIQUE INDEX num ON Hospital.Department (num);
INSERT INTO Hospital.Department (num, name, abbr) VALUES ('000001', '外科', 'WK');
INSERT INTO Hospital.Department (num, name, abbr) VALUES ('000002', '内科', 'N');
INSERT INTO Hospital.Department (num, name, abbr) VALUES ('000003', '神经科', 'SJ');
INSERT INTO Hospital.Department (num, name, abbr) VALUES ('000004', '眼科', 'Y');
INSERT INTO Hospital.Department (num, name, abbr) VALUES ('000005', '消化科', 'XH');
INSERT INTO Hospital.Department (num, name, abbr) VALUES ('000006', '放射科', 'FS');
CREATE TABLE Hospital.Doctor
(
    num char(6) PRIMARY KEY NOT NULL,
    Department_num char(6) NOT NULL,
    name char(10) NOT NULL,
    abbr char(4) NOT NULL,
    password char(8) NOT NULL,
    is_expert tinyint(1) NOT NULL,
    sign_up_time datetime,
    CONSTRAINT Doctor_ibfk_1 FOREIGN KEY (Department_num) REFERENCES Department (num)
);
CREATE UNIQUE INDEX num ON Hospital.Doctor (num, Department_num);
CREATE INDEX Department_num ON Hospital.Doctor (Department_num);
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000001', '000001', '艾希', 'AX', '1', 1, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000002', '000002', '金克斯', 'JKS', '1', 1, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000003', '000003', '拉克丝', 'LKS', '1', 1, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000004', '000004', '刀妹', 'DM', '1', 1, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000005', '000005', '狗头', 'DT', '1', 1, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000006', '000006', '猪妹', 'ZM', '1', 1, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000007', '000001', '艾希2', 'AXE', '1', 0, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000008', '000002', '金克斯2', 'JKSE', '1', 0, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000009', '000003', '拉克丝2', 'LKSE', '1', 0, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000010', '000004', '刀妹2', 'DME', '1', 0, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000011', '000005', '狗头2', 'DTE', '1', 0, '2018-05-30 00:00:00');
INSERT INTO Hospital.Doctor (num, Department_num, name, abbr, password, is_expert, sign_up_time) VALUES ('000012', '000006', '猪妹2', 'ZME', '1', 0, '2018-05-30 00:00:00');
CREATE TABLE Hospital.Patient
(
    num char(6) PRIMARY KEY NOT NULL,
    name char(10) NOT NULL,
    password char(8) NOT NULL,
    money decimal(10,2) NOT NULL,
    sign_up_time datetime
);
CREATE UNIQUE INDEX num ON Hospital.Patient (num);
INSERT INTO Hospital.Patient (num, name, password, money, sign_up_time) VALUES ('000001', 'vn', '1', 41.00, '2018-05-30 00:00:00');
INSERT INTO Hospital.Patient (num, name, password, money, sign_up_time) VALUES ('000002', 'vn', '1', 81.00, '2018-05-30 00:00:00');
INSERT INTO Hospital.Patient (num, name, password, money, sign_up_time) VALUES ('000003', 'ez', '1', 81.00, '2018-05-30 00:00:00');
INSERT INTO Hospital.Patient (num, name, password, money, sign_up_time) VALUES ('000004', 'rn', '1', 81.00, '2018-05-30 00:00:00');
INSERT INTO Hospital.Patient (num, name, password, money, sign_up_time) VALUES ('000005', 'kk', '1', 81.00, '2018-05-30 00:00:00');
INSERT INTO Hospital.Patient (num, name, password, money, sign_up_time) VALUES ('000006', 'ss', '1', 81.00, '2018-05-30 00:00:00');
CREATE TABLE Hospital.Registration_Instance
(
    num char(6) PRIMARY KEY NOT NULL,
    Regestration_num char(6) NOT NULL,
    Doctor_num char(6) NOT NULL,
    Patient_num char(6) NOT NULL,
    Patient_amount int(11) NOT NULL,
    is_cancelled tinyint(1) NOT NULL,
    actual_cost decimal(8,2) NOT NULL,
    time datetime NOT NULL,
    CONSTRAINT Registration_Instance_ibfk_1 FOREIGN KEY (Regestration_num) REFERENCES Registration_Type (num),
    CONSTRAINT Registration_Instance_ibfk_2 FOREIGN KEY (Doctor_num) REFERENCES Doctor (num),
    CONSTRAINT Registration_Instance_ibfk_3 FOREIGN KEY (Patient_num) REFERENCES Patient (num)
);
CREATE UNIQUE INDEX num ON Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount);
CREATE INDEX Regestration_num ON Hospital.Registration_Instance (Regestration_num);
CREATE INDEX Doctor_num ON Hospital.Registration_Instance (Doctor_num);
CREATE INDEX Patient_num ON Hospital.Registration_Instance (Patient_num);
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000001', '000001', '000001', '000001', 1, 0, 12.00, '2018-06-01 06:59:18');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000002', '000001', '000001', '000001', 2, 0, 15.00, '2018-06-01 06:59:31');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000003', '000002', '000001', '000001', 1, 0, 16.00, '2018-06-01 07:01:12');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000004', '000004', '000001', '000001', 1, 0, 10.00, '2018-06-01 07:01:50');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000005', '000002', '000001', '000001', 2, 0, 17.00, '2018-06-01 07:03:09');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000006', '000004', '000004', '000001', 2, 0, 17.00, '2018-06-01 07:31:58');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000007', '000002', '000007', '000001', 3, 0, 15.00, '2018-06-02 02:55:19');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000008', '000006', '000009', '000001', 1, 0, 17.00, '2018-06-02 02:55:48');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000009', '000002', '000002', '000001', 4, 0, 13.00, '2018-06-02 02:56:42');
INSERT INTO Hospital.Registration_Instance (num, Regestration_num, Doctor_num, Patient_num, Patient_amount, is_cancelled, actual_cost, time) VALUES ('000010', '000004', '000003', '000001', 3, 0, 11.00, '2018-06-02 02:59:41');
CREATE TABLE Hospital.Registration_Type
(
    num char(6) PRIMARY KEY NOT NULL,
    name char(12) NOT NULL,
    abbr char(4) NOT NULL,
    Department_num char(6) NOT NULL,
    is_expert tinyint(1) NOT NULL,
    num_limitaion int(11) NOT NULL,
    money tinyint(1) NOT NULL,
    CONSTRAINT Registration_Type_ibfk_1 FOREIGN KEY (Department_num) REFERENCES Department (num)
);
CREATE UNIQUE INDEX num ON Hospital.Registration_Type (num, Department_num);
CREATE INDEX Department_num ON Hospital.Registration_Type (Department_num);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000001', '外-科', 'YY', '000001', 0, 2, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000002', '外二科', 'YE', '000001', 1, 100, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000003', '内-科', 'NY', '000002', 0, 2, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000004', '内二科', 'NE', '000002', 1, 100, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000005', '神经-科', 'SJY', '000003', 0, 2, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000006', '神经二科', 'SJE', '000003', 1, 100, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000007', '眼-科', 'YY', '000004', 0, 2, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000008', '眼二科', 'YE', '000004', 1, 100, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000009', '消化-科', 'XHY', '000005', 0, 2, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000010', '消化二科', 'XHE', '000005', 1, 100, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000011', '放射-科', 'FSY', '000006', 0, 2, 10);
INSERT INTO Hospital.Registration_Type (num, name, abbr, Department_num, is_expert, num_limitaion, money) VALUES ('000012', '放射二科', 'FSE', '000006', 1, 100, 10);
