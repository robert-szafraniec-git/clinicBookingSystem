INSERT INTO user_roles (id, role) VALUES (1, 'ROLE_USER');
INSERT INTO users (id, login, password) VALUES (1, 'user', '$2a$10$iNWL3dCpWChiBQGU6HAUxOs6iq6ev2nOk6TqlG1T24rX7DFdDvpi.');
INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1);

INSERT INTO user_roles (id, role) VALUES (2, 'ROLE_ADMIN');
INSERT INTO users (id, login, password) VALUES (2, 'admin', '$2a$10$iNWL3dCpWChiBQGU6HAUxOs6iq6ev2nOk6TqlG1T24rX7DFdDvpi.');
INSERT INTO users_roles (user_id, roles_id) VALUES (2, 2);

INSERT INTO user_roles (id, role) VALUES (3, 'ROLE_DOCTOR');
INSERT INTO users (id, login, password) VALUES (3, 'doctor', '$2a$10$iNWL3dCpWChiBQGU6HAUxOs6iq6ev2nOk6TqlG1T24rX7DFdDvpi.');
INSERT INTO users_roles (user_id, roles_id) VALUES (3, 3);


INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (46,'2019-06-29 23:12:25',15,'okulista',1,0,'2019-06-29 22:41:00',1,4,'2019-06-29 22:56:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (45,'2019-06-29 22:50:09',15,'okulista',3,0,'2019-06-30 21:40:00',1,2,'2019-06-30 21:55:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (44,'2019-06-29 22:44:23',15,'okulista',0,0,'2019-06-29 21:38:00',1,3,'2019-06-29 21:53:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (26,'2019-06-29 22:45:21',15,'okulista',0,0,'2019-06-27 23:01:00',1,3,'2019-06-27 23:16:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (24,'2019-06-29 22:45:28',15,'okulista',0,0,'2019-06-27 22:00:00',1,3,'2019-06-27 22:15:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (31,'2019-06-29 22:45:13',15,'internista',0,0,'2015-12-31 00:01:00',4,2,'2015-12-31 00:16:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (32,'2019-06-29 22:39:11',15,'okulista',2,0,'2018-12-31 23:00:00',1,1,'2018-12-31 23:15:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (33,'2019-06-29 22:36:41',15,'internista',3,0,'2019-06-01 00:00:00',2,4,'2019-06-01 00:15:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (34,'2019-06-29 23:12:15',15,'internista',3,0,'2019-06-01 00:00:00',2,2,'2019-06-01 00:15:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (35,'2019-06-29 22:38:23',15,'internista',1,0,'2019-06-01 00:00:00',2,3,'2019-06-01 00:15:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (36,'2019-06-29 22:37:09',15,'okulista',2,0,'2019-06-01 00:00:00',1,28,'2019-06-01 00:15:00');
INSERT INTO `visit` (`id`,`created_on`,`plan_length`,`specialization`,`status`,`created_by`,`visit_date`,`doctor_id`,`patient_id`,`visit_end_date`) VALUES (37,'2019-06-29 22:38:35',15,'okulista',0,0,'2019-06-01 00:15:00',1,3,'2019-06-01 00:30:00');

INSERT INTO `` (`id`,`first_name`,`last_name`,`pesel`) VALUES (1,'Tomasz','Karolak',77112301112);
INSERT INTO `` (`id`,`first_name`,`last_name`,`pesel`) VALUES (2,'Wojciech','Szczęsny',72121775839);
INSERT INTO `` (`id`,`first_name`,`last_name`,`pesel`) VALUES (3,'Zbigniew','Boniek',280527668);
INSERT INTO `` (`id`,`first_name`,`last_name`,`pesel`) VALUES (4,'Ryszard','Nawałka',89042586613);
INSERT INTO `` (`id`,`first_name`,`last_name`,`pesel`) VALUES (28,'Ryszard','Nawałka',70112243776);
INSERT INTO `` (`id`,`first_name`,`last_name`,`pesel`) VALUES (43,'Ryszard','Nawałka',3280759817);


INSERT INTO `doctor` (`id`,`first_name`,`last_name`,`specialization`) VALUES (2,'Ryszard','Nawałka','internista');
INSERT INTO `doctor` (`id`,`first_name`,`last_name`,`specialization`) VALUES (1,'Maciej','Zębaty','okulista');
INSERT INTO `doctor` (`id`,`first_name`,`last_name`,`specialization`) VALUES (3,'Ryszard','Ochódzki','okulista');
INSERT INTO `doctor` (`id`,`first_name`,`last_name`,`specialization`) VALUES (4,'Karol','Witkowski','internista');
