package com.example.hospitalregistration.mapper;

import com.example.hospitalregistration.entity.DoctorsTimetable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DoctorsTimetableMapper implements RowMapper<DoctorsTimetable> {

    public static final String BASE_SQL_DOCTORS_TIMETABLE = "SELECT dt.Id, dt.Doctor_id, dt.Date FROM doctors_timetable dt";

    @Override
    public DoctorsTimetable  mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("Id");;
        long doctorId = rs.getLong("Doctor_id");
        Date date = rs.getDate("Date");

        return new DoctorsTimetable(id,doctorId,date);
    }
}