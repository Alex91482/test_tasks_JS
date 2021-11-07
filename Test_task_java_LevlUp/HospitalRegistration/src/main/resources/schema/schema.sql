
CREATE TABLE doctor
(
    Id BIGINT PRIMARY KEY,
    Last_name VARCHAR(128) NOT NULL,
    First_name VARCHAR(128) NOT NULL,
    Specialization VARCHAR(128) NOT NULL
);

CREATE TABLE patient
(
    Id BIGINT PRIMARY KEY,
    First_name VARCHAR(128) NOT NULL,
    Last_name VARCHAR(128) NOT NULL,
    Passport_serial INTEGER NOT NULL,
    Mail VARCHAR(255) NOT NULL,
    Date_of_visit TIMESTAMP NOT NULL,
    Doctor_specialization VARCHAR(128) NOT NULL,
    To_which_doctor VARCHAR(128)
);

CREATE TABLE timetable
(
    Id BIGINT PRIMARY KEY,
    Patient_id BIGINT NOT NULL,
    Doctor_id BIGINT NOT NULL,
    Date TIMESTAMP NOT NULL,
    FOREIGN KEY (Patient_id) REFERENCES patient (Id),
    FOREIGN KEY (Doctor_id) REFERENCES doctor (Id)
);

CREATE TABLE doctors_timetable
(
    Id BIGSERIAL PRIMARY KEY,
    Doctor_id BIGINT NOT NULL,
    Date TIMESTAMP NOT NULL,
    FOREIGN KEY (Doctor_id) REFERENCES doctor (Id)
);

CREATE TABLE disease_history
(
    Id BIGINT PRIMARY KEY,
    Patient_id BIGINT NOT NULL,
    Doctor_id BIGINT NOT NULL,
    Diagnosis TEXT,
    Recommendations TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient (Id),
    FOREIGN KEY (doctor_id) REFERENCES doctor (Id)
);
