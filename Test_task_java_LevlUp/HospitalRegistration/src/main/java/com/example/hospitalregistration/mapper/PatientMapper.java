package com.example.hospitalregistration.mapper;

import com.example.hospitalregistration.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PatientMapper implements RowMapper<Patient> {

    public static final String BASE_SQL ="SELECT pa.Id, pa.First_Name, pa.Last_Name, pa.Passport_serial, pa.Mail, pa.Date_of_visit, pa.Doctor_specialization, pa.To_which_doctor FROM patient pa";

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("Id");
        String firstName = rs.getString("First_Name");
        String lastName = rs.getString("Last_Name");
        Integer passportSerial= rs.getInt("Passport_serial");
        String mail= rs.getString("Mail");
        Date dateOfVisit= rs.getDate("Date_of_visit");
        String doctorSpecialization= rs.getString("Doctor_specialization");
        String toWhichDoctor= rs.getString("To_which_doctor");

        return new Patient(id,firstName,lastName,passportSerial,mail,dateOfVisit,doctorSpecialization,toWhichDoctor);
    }
}

