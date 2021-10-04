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
	Id PRIMARY KEY,
	First_name VARCHAR(128) NOT NULL,
	Last_name VARCHAR(128) NOT NULL,
	Passport_serial INTEGER NOT NULL,
	Mail VARCHAR(255) NOT NULL,
	Date_of_visit DATE NOT NULL,
	To_which_dotor VARCHAR(128),
	Doctor_specialization VARCHAR(128) NOT NULL
	);

	CREATE TABLE doctor
	(
	Id PRIMARY KEY,
	Last_name VARCHAR(128) NOT NULL,
	First_name VARCHAR(128) NOT NULL,
	Specialization VARCHAR(128) NOT NULL,
	FOREIGN KEY (Id_doctor) REFERENCES timetable (Id_doctor)
	);

	CREATE TABLE timetable
	(
	Id PRIMARY KEY,
	Patient_id,				
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
	Diagnosis VARCHAR(2550),
	Recommendations TEXT,
	FOREIGN KEY (patient_id) REFERENCES patient (Id),
	FOREIGN KEY (doctor_id) REFERENCES doctor (Id)
	);
	
	Структура для хранеия свободных и записаных дней (требуется сериализовать)
	
	boolean [][][] month = new boolean [12][][];
		month[0] = new boolean[31][20]; //jn
		month[1] = new boolean[28][20]; //fb
		month[2] = new boolean[31][20]; //mr
		month[3] = new boolean[30][20]; //ap
		month[4] = new boolean[31][20]; //mi
		month[5] = new boolean[30][20]; //ij
		month[6] = new boolean[31][20]; //ij
		month[7] = new boolean[31][20]; //aw
		month[8] = new boolean[30][20]; //se
		month[9] = new boolean[31][20]; //oc
		month[10] = new boolean[30][20]; //no
		month[11] = new boolean[31][20]; //de