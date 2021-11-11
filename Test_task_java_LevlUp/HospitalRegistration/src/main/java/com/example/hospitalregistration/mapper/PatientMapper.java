package com.example.hospitalregistration.mapper;

import com.example.hospitalregistration.entity.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientMapper implements RowMapper<Patient> {

    private final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final Logger logger = LogManager.getLogger(PatientMapper.class);
    public static final String BASE_SQL_PATIENT ="SELECT pa.Id, pa.First_Name, pa.Last_Name, pa.Passport_serial, pa.Mail, pa.Date_of_visit, pa.Doctor_specialization, pa.To_which_doctor FROM patient pa";

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) /*throws SQLException*/ {

        try {
            Long id = rs.getLong("Id");
            String firstName = rs.getString("First_Name");
            String lastName = rs.getString("Last_Name");
            Integer passportSerial = rs.getInt("Passport_serial");
            String mail = rs.getString("Mail");
            Date dateOfVisit = formater.parse(rs.getString("Date_of_visit")); //без костылей неправильно отображает дату
            String doctorSpecialization = rs.getString("Doctor_specialization");
            String toWhichDoctor = rs.getString("To_which_doctor");

            System.out.println(dateOfVisit);

            return new Patient(id, firstName, lastName, passportSerial, mail, dateOfVisit, doctorSpecialization, toWhichDoctor);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return null; //переделать
    }
}

