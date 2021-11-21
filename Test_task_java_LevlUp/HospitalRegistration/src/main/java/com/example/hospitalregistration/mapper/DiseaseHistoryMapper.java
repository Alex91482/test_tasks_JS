package com.example.hospitalregistration.mapper;

import com.example.hospitalregistration.entity.DiseaseHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiseaseHistoryMapper implements RowMapper<DiseaseHistory> {

    public static final String BASE_SQL_DISEASE_HISTORY ="SELECT dh.Id, dh.Patient_id, dh.Doctor_id, dh.Diagnosis, dh.Recommendations FROM disease_history dh";

    @Override
    public DiseaseHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Long id = rs.getLong("Id");
        Long patientId = rs.getLong("Patient_id");
        Long doctorId = rs.getLong("Doctor_id");
        String diagnosis = rs.getString("Diagnosis");
        String recommendations = rs.getString("Recommendations");

        return new DiseaseHistory(patientId,doctorId,diagnosis,recommendations);
    }
}
