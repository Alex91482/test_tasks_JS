package com.example.hospitalregistration.mapper;

import com.example.hospitalregistration.entity.Timetable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TimetableMapper implements RowMapper<Timetable> {

    public static final String BASE_SQL ="SELECT ti.Id, ti.Patient_id, ti.Doctor_id, ti.Date FROM timetable ti";

    @Override
    public Timetable mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("Id");
        Long patientId = rs.getLong("Patient_id");
        Long doctorId = rs.getLong("Doctor_id");
        Date date = rs.getDate("Date");

        return new Timetable(id, patientId, doctorId, date);
    }
}

