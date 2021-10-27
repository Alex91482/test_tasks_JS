
CREATE TABLE doctor
(
    Id PRIMARY KEY,
    Last_name VARCHAR(128) NOT NULL,
    First_name VARCHAR(128) NOT NULL,
    Specialization VARCHAR(128) NOT NULL
);

Insert into doctor(Id, Last_name, First_name, Specialization) values (1, 'House', 'Gregory', 'Therapist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (2, 'Hannibal', 'Lecter', 'Therapist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (3, 'Philipovich', 'Philip', 'Therapist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (4, 'Stone', 'Benjamin', 'Optometrist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (5, 'Frankenstein', 'Victor', 'Optometrist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (6, 'Bormental', 'Ivan', 'Optometrist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (7, 'Ledgard', 'Robert', 'Otolaryngologist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (8, 'Kevorkian', 'Jack', 'Otolaryngologist');
Insert into doctor(Id, Last_name, First_name, Specialization) values (9, 'Thackery', 'John', 'Otolaryngologist');

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

Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (1,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (2,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (3,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (4,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (5,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (6,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (7,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (8,);
Insert into patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit, Doctor_specialization, To_which_doctor) values (9,);

CREATE TABLE timetable
(
    Id PRIMARY KEY,
    Patient_id BIGINT NOT NULL,
    Doctor_id BIGINT NOT NULL,
    Date DATE NOT NULL,
    FOREIGN KEY (Patient_id) REFERENCES patient (Id),
    FOREIGN KEY (Doctor_id) REFERENCES doctor (Id)
);

Insert into timetable(Id, Patient_id, Doctor_id, Date) values (1, 1, 1, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (2, 2, 1, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (3, 3, 1, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (4, 4, 2, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (5, 5, 2, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (6, 6, 2, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (7, 7, 5, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (8, 8, 5, );
Insert into timetable(Id, Patient_id, Doctor_id, Date) values (9, 9, 5, );

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

Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (1, 1, 1, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (2, 2, 1, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (3, 3, 1, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (4, 4, 2, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (5, 5, 2, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (6, 6, 2, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (7, 7, 5, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (8, 8, 5, );
Insert into disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (9, 9, 5, );