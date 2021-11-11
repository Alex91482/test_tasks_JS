package com.example.hospitalregistration.mapper;

import com.example.hospitalregistration.entity.VirtualPatient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;


public class VirtualPatientMapper implements RowMapper<VirtualPatient> {

    private static final Logger logger = LogManager.getLogger(VirtualPatientMapper.class);
    public static final String BASE_SQL_VIRTUAL_PATIENT ="SELECT vp.Id, vp.Patient_id, vp.Encryted_password, vp.Login, vp.Role FROM virtual_patient vp";

    @Override
    public VirtualPatient mapRow(ResultSet rs, int rowNum) /*throws SQLException*/ {

        try {
            Long id = rs.getLong("Id");
            Long patientId = rs.getLong("Patient_id");
            String encrytedPassword = rs.getString("Encryted_password");
            String login = rs.getString("Login");
            String role = rs.getString("Role");

            return new VirtualPatient(id, patientId, encrytedPassword, login, role);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return null; //переделать
    }
}
