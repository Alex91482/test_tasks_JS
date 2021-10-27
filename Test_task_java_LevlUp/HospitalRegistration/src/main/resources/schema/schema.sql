
CREATE TABLE doctor
(
    Id PRIMARY KEY,
    Last_name VARCHAR(128) NOT NULL,
    First_name VARCHAR(128) NOT NULL,
    Specialization VARCHAR(128) NOT NULL
);

CREATE TABLE patient
(
    Id PRIMARY KEY,
    First_name VARCHAR(128) NOT NULL,
    Last_name VARCHAR(128) NOT NULL,
    Passport_serial INTEGER NOT NULL,
    Mail VARCHAR(255) NOT NULL,
    Date_of_visit DATE NOT NULL,
    Doctor_specialization VARCHAR(128) NOT NULL,
    To_which_doctor VARCHAR(128)
);

CREATE TABLE timetable
(
    Id PRIMARY KEY,
    Patient_id BIGINT NOT NULL,
    Doctor_id BIGINT NOT NULL,
    Date DATE NOT NULL,
    FOREIGN KEY (Patient_id) REFERENCES patient (Id),
    FOREIGN KEY (Doctor_id) REFERENCES doctor (Id)
);

CREATE TABLE disease_history
(
    Id PRIMARY KEY,
    Patient_id BIGINT NOT NULL,
    Doctor_id BIGINT NOT NULL,
    Diagnosis TEXT,
    Recommendations TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient (Id),
    FOREIGN KEY (doctor_id) REFERENCES doctor (Id)
);

Insert into doctor(Id, Last_name, First_name, Specialization) values (1, 'House', 'Gregory', 'THERAPIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (2, 'Hannibal', 'Lecter', 'THERAPIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (3, 'Philipovich', 'Philip', 'THERAPIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (4, 'Stone', 'Benjamin', 'OPTOMETRIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (5, 'Frankenstein', 'Victor', 'OPTOMETRIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (6, 'Bormental', 'Ivan', 'OPTOMETRIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (7, 'Ledgard', 'Robert', 'OTOLARYNGOLOGIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (8, 'Kevorkian', 'Jack', 'OTOLARYNGOLOGIST');
Insert into doctor(Id, Last_name, First_name, Specialization) values (9, 'Thackery', 'John', 'OTOLARYNGOLOGIST');

Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (1,'Stan','Smith',0000000007,'stan.smith@americanded.com','2021-10-31 9:00','THERAPIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (2,'Spongebob','Square Pants',0000000123,'spnge.bob@bikinibottom.com','2021-10-31 9:20','THERAPIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (3,'Patrick','Star',0000234517,'patrick.star@bikinibottom.com','2021-10-31 9:40','THERAPIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (4,'Sandy','Chicks',0000345213,'sandy.chicks@bikinibottom.com','2021-10-31 9:00','OPTOMETRIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (5,'Squidward','Tentacle',0000783421,'squidward.tentacle@bikinibottom.com','2021-10-31 9:20','OPTOMETRIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (6,'Lila','Turanga',0000214325,'homer.simpson@springfield.com','2021-10-31 9:40','OPTOMETRIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (7,'Eric','Cartman',0000443214,'eric.cartman@southpark.com','2021-10-31 9:00','OTOLARYNGOLOGIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (8,'Homer','Simpson',0000112233,'lila.turanga@futurama.com','2021-10-31 9:20','OTOLARYNGOLOGIST','');
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (9,'Bender','Rodrigues',0000763490,'bender.rodrigues@futurama.com','2021-10-31 9:40','OTOLARYNGOLOGIST','');

Insert into timetable(Id, Patient_id, Doctor_id, Date) values (1, 1, 1, '2021-10-31 9:00');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (2, 2, 1, '2021-10-31 9:20');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (3, 3, 1, '2021-10-31 9:40');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (4, 4, 2, '2021-10-31 9:00');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (5, 5, 2, '2021-10-31 9:20');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (6, 6, 2, '2021-10-31 9:40');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (7, 7, 5, '2021-10-31 9:00');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (8, 8, 5, '2021-10-31 9:20');
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (9, 9, 5, '2021-10-31 9:40');

Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (1, 1, 1,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (2, 2, 1,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (3, 3, 1,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (4, 4, 2,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (5, 5, 2,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (6, 6, 2,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (7, 7, 5,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (8, 8, 5,'','');
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (9, 9, 5,'','');