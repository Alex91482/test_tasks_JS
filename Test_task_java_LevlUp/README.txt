1. Stage one.
	Постановка задачи.
	Написать систему самозаписи в медицинскую клинику. Пациенты записываются на прием к врачам (познее добавить запись на исследования). У врачей будет 
	имется расписание когда они дежурят, врачи имеют разную специализацию. В данной реализации будут присутствовать по 4 врача, на каждую из 3 специализаций.
	Есть конкретный врач, либо общее расписаниеие (для записи на ближайшее число, врач по выбранной специальности ближайший свободный). Талон высылается
	на почту.
	В расписании будут использоватся фиксированное время. Нельзя будет записать двух человек на одно и то же время. В день на каждлго врача может быть 
	записанно не более 20 пациентов. Врачи работают с 9 до 17 часов следовательно (если вычесть час на обед) на каждого пациента выделяется по 20 минут. 
	Предварительно в БД (Регистрация в мед.клинику) будет содержатся три таблицы "Пациент", "Врач", "Расписание".

	CREATE TABLE patient
	(
	ID_patient PRIMARY KEY,
	First_name VARCHAR(128) NOT NULL,
	Last_name VARCHAR(128) NOT NULL,
	Passport_serial INTEGER NOT NULL,
	Date_of_visit DATE NOT NULL,
	To_which_dotor VARCHAR(128),
	Doctor_specialization VARCHAR(128) NOT NULL
	);

	CREATE TABLE doctor
	(
	Id_doctor PRIMARY KEY,
	Last_name VARCHAR(128) NOT NULL,
	First_name VARCHAR(128) NOT NULL,
	Specialization VARCHAR(128) NOT NULL,
	FOREIGN KEY (Id_doctor) REFERENCES timetable (Id_doctor)
	);

	CREATE TABLE timetable
	(
	Number_of_patient PRIMARY KEY,
	Id_doctor BIGINT NOT NULL,
	Date DATE NOT NULL,
	Doctor_last_name VARCHAR(128) NOT NULL,
	FOREIGN KEY (Number_of_patient) REFERENCES patient (ID_patient)
	);
	
	