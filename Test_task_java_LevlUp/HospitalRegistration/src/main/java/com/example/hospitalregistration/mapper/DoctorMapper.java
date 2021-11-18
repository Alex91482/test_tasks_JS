package com.example.hospitalregistration.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.hospitalregistration.entity.Doctor;
import org.springframework.jdbc.core.RowMapper;

public class DoctorMapper implements RowMapper<Doctor>{

    public static final String BASE_SQL_DOCTOR ="SELECT d.Id, d.Last_Name, d.First_Name, d.Specialization FROM doctor AS d ";

    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("Id"); //id должно присваиватся автоматически
        String lastName = rs.getString("Last_Name");
        String firstName = rs.getString("First_Name");
        String specialization = rs.getString("Specialization");

        return new Doctor(lastName, firstName, specialization);
    }
}
