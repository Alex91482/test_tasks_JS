package com.example.hospitalregistration.controller;


import com.example.hospitalregistration.dao.DoctorDAO;
import com.example.hospitalregistration.dao.PatientDAO;
import com.example.hospitalregistration.dao.VirtualPatientDAO;
import com.example.hospitalregistration.dao.TimetableDAO;
import com.example.hospitalregistration.entity.Patient;
import com.example.hospitalregistration.entity.Timetable;
import com.example.hospitalregistration.entity.VirtualPatient;
import com.example.hospitalregistration.security.RandomGenerated;
import com.example.hospitalregistration.security.bcrypt.EncrytedPasswordUtils;
import com.example.hospitalregistration.service.mail.EmailService;
import com.example.hospitalregistration.service.patientservice.PatientForm;
import com.example.hospitalregistration.service.patientservice.PatientService;

import com.example.hospitalregistration.service.serializable.SavePatientSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PatientServiceController {

    @Value("${error.message}")
    private String errorMessage;

    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //Date d1 = formater.parse("2021-10-19 16:20");

    private final PatientService patientService;

    @Autowired
    public PatientServiceController (PatientService patientService){
        this.patientService = patientService;
    }

    @Autowired
    public PatientDAO patientDAO;
    @Autowired
    public JavaMailSender emailSender; //отправляет письма
    @Autowired
    EncrytedPasswordUtils bCryptPasswordEncoder;
    @Autowired
    public SavePatientSerImpl savePatientSerImpl;
    @Autowired
    public RandomGenerated randomGenerated; //генерирует логин и пароль
    @Autowired
    public VirtualPatientDAO virtualPatientDAO;
    @Autowired
    public DoctorDAO doctorDAO;
    @Autowired
    public TimetableDAO timetableDAO;

    @CrossOrigin
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String savePatient(Model model, @ModelAttribute("patientForm") PatientForm patientForm){ //метод по регистрации пациента
        String firstName = patientForm.getFirstName();
        String lastName = patientForm.getLastName();
        int passportSerial = patientForm.getPassportSerial();
        String mail = patientForm.getMail();
        String doctorSpecialisation = patientForm.getDoctorSpecialisation();
        String toWhichDoctor = patientForm.getToWhichDoctor();
        Date dateOfVisit = patientForm.getDateOfVisit();

        //метод достаточно перегружен и нужно будет распоралелить запись в таблицы и репозитории и если запись в бд
        //прошла успешно то отправлять письмо



        boolean doesEntry = patientService.read(dateOfVisit,doctorSpecialisation); //используется для проверки есть ли в репозитории такая запись

        if(dateOfVisit != null && doctorSpecialisation != null && !doesEntry){  //проверяем валидность данных

            //если пациент не указал врача то ищем в базе кто дежурит в данный день
            Long doctorId = 0L;
            if (toWhichDoctor.equals("")){
                Map<String, Object> doctor = doctorDAO.getDoctorByDateAndSpec(patientForm.getDateOfVisitFormat_YYYY_MM_DD(),doctorSpecialisation);
                toWhichDoctor = doctor.get("last_name") + " " + doctor.get("first_name");
                doctorId =(Long) doctor.get("id");
            }

            //эта часть отвечает за создание записи о пациенте в таблице patient
            Patient patient = new Patient(firstName,lastName,passportSerial,mail,doctorSpecialisation,toWhichDoctor,dateOfVisit);
            patientDAO.savePatient(patient);                                  //сохраняем в бд

            //эта часть отвечает за создание записи в таблице virtual_patient так же за генерацию логина и пароля
            String pass = randomGenerated.genPass(); //генерируем логин и пароль
            String login = randomGenerated.genLog(); //пароль нужно зашифровать и сохранить зашифрованный в бд
            virtualPatientDAO.saveVirtualPatient(new VirtualPatient(patient.getId(), bCryptPasswordEncoder.encrytePassword(pass), login)); //создание виртуального пациента

            //эта часть отвечает за создание записи в таблице timetable
            //по всей видимости придется передавать не имя фамилию врача а всю мапу целиком обзывать ее врачем и разбирать на запчасти
            timetableDAO.addTimetable(new Timetable(patient.getId(), doctorId, dateOfVisit));


            //эта часть отвечает за резервирование времени в репозитории
            patientService.createRecord(dateOfVisit,doctorSpecialisation);      //сохраняем запись в репозитории чтобы на нее больше нельзя было оформить прием
            savePatientSerImpl.serPatientSerImpl();                             //сериализуем репозиторий

            //отправляем письмо пациенту с данными регистрации
            emailSender.send(new EmailService().sendMail(mail,dateOfVisit,pass,login));    //оживляем почтовый сервис

            //выводим на экран что все удачно
            return "redirect:/pageMailMessage";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "page";
    }

    @CrossOrigin
    @RequestMapping(value = "/getRecord/{specFromJs}/{dateFromJs}",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testGetRecord(@PathVariable(name = "specFromJs") String spec,@PathVariable(name = "dateFromJs") String dat){
        List<Date> list = patientService.getRecord(dat,spec); //запросы к репозиторию разрешить всем т.к. в репозитории нет ни каких данных
        return new ResponseEntity<>(list, HttpStatus.OK); //метод возвращает список в котором ({dateFromJs} будет переданно как "yyyy-MM-dd") будут возвращенны совпавшие даты со временем которые зарезервированны
    }

    @RequestMapping(value = "/allRecord",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> returnRecord(){
        List<String> list = patientService.allRecord(); //запрос на список дат все которые есть в репозитории
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value="/patientList", method = RequestMethod.GET)
    public String patientList(){
        return "patientList"; //список зарезервированных специализация/дата/время
    }
    @RequestMapping(value="/pageMailMessage", method = RequestMethod.GET)
    public String patientPageMail(){
        return "pageMailMessage"; //регистрация прошла успешно
    }

    @CrossOrigin
    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String patientFrom(Model model){
        PatientForm patientForm = new PatientForm();
        model.addAttribute("patientForm",patientForm);
        return "page"; //форма для регистрации
    }

}
