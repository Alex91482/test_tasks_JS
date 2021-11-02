package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.Patient;
import com.example.hospitalregistration.mapper.PatientMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class PatientDAO extends JdbcDaoSupport {


    @Autowired
    public PatientDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    } //разобратся с этим

    private static final Logger logger = LogManager.getLogger(PatientDAO.class);

    public List<Patient> getPatient() { //получить всех пациентов
        String sql = PatientMapper.BASE_SQL_PATIENT;
        Object[] params = new Object[] {};
        PatientMapper mapper = new PatientMapper();
        List<Patient> list = this.getJdbcTemplate().query(sql, params, mapper);

        return list;
    }

    public Patient findPatient(Long id) { //получить пациента по id
        String sql = PatientMapper.BASE_SQL_PATIENT + " WHERE pa.Id = ? ";
        Object[] params = new Object[] { id };
        PatientMapper mapper = new PatientMapper();
        try {
            Patient patient = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return patient;
        } catch (EmptyResultDataAccessException e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public void savePatient(Patient patient){   //добавить пациента в базу
                                                //добавить автоматическое создание id?
        String id = patient.getId().toString();
        String firstName= patient.getFirstName();
        String lastName= patient.getLastName();
        int passportSerial= patient.getPassportSerial();
        String mail= patient.getMail();
        String dateOfVisit= patient.getDateOfVisit().toString();
        String doctorSpecialization= patient.getDoctorSpecialization();
        String toWhichDoctor= patient.getToWhichDoctor();

        String sqlSave = "INSERT INTO patient(Id, First_name, Last_name, Passport_serial, Mail, Date_of_visit," +
                " Doctor_specialization, To_which_doctor) VALUES (?,?,?,?,?,?,?,?)";
        Object[] params = new Object[]{id,firstName,lastName,passportSerial,mail,dateOfVisit,doctorSpecialization,toWhichDoctor};
        PatientMapper patientMapper = new PatientMapper();
        try {
            this.getJdbcTemplate().update(sqlSave,params,patientMapper);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
    }

    public boolean deletePatient(long id){ //удалить пациента из базы
        String sqlDelete = "DELETE FROM patient WHERE Id = ?";
        Patient patient = findPatient(id);
        Object[] params = new Object[]{id};
        PatientMapper mapper = new PatientMapper();
        if(patient == null){
            return false; //добавить вывод исключения
        }
        try {
            this.getJdbcTemplate().update(sqlDelete,params,mapper);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return true;
    }

    /*
    public boolean updatePatient(long id){ //обновить пациента в базе
        String sqlUpdate = "";
        Patient patient = findPatient(id);
        if(patient == null){
            return false; //добавить вывод исключения
        }
        //this.getJdbcTemplate().update();
        return true;
    }
    */
}
