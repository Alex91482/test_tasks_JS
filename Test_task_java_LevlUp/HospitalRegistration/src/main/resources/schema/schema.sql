
CREATE TABLE doctor
(
    Id PRIMARY KEY,
    Last_name VARCHAR(128) NOT NULL,
    First_name VARCHAR(128) NOT NULL,
    Specialization VARCHAR(128) NOT NULL,
    FOREIGN KEY (Id_doctor) REFERENCES timetable (Id_doctor)
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