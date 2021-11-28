package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.DiseaseHistory;
import com.example.hospitalregistration.mapper.DiseaseHistoryMapper;

import com.example.hospitalregistration.mapper.TimetableMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class DiseaseHistoryDAO extends JdbcDaoSupport{

    private static final Logger logger = LogManager.getLogger(DiseaseHistoryDAO.class);

    @Autowired
    public DiseaseHistoryDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void addDiseaseHistory(DiseaseHistory diseaseHistory){ //создать историю болезни
        long id = diseaseHistory.getId();
        long patientId = diseaseHistory.getPatientId();
        long doctorId = diseaseHistory.getDoctorId();
        String diagnosis = diseaseHistory.getDiagnosis();
        String recommendation = diseaseHistory.getRecommendations();

        String sqlSave = "INSERT INTO disease_history(Id, Patient_id, Doctor_id, Diagnosis, Recommendations) values (?,?,?,?,?)";
        Object[] params = new Object[]{id,patientId,doctorId,diagnosis,recommendation};
        DiseaseHistoryMapper diseaseHistoryMapper = new DiseaseHistoryMapper();
        try {
            this.getJdbcTemplate().update(sqlSave,params,diseaseHistoryMapper);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
    }

    public DiseaseHistory findDiseaseHistoryFromPatientId(long id){ //получить историю болезни
        String sql = DiseaseHistoryMapper.BASE_SQL_DISEASE_HISTORY + " WHERE dh.Patient_id = ? ";
        DiseaseHistoryMapper mapper = new DiseaseHistoryMapper();
        try{
            DiseaseHistory diseaseHistory = this.getJdbcTemplate().queryForObject(sql, mapper, id);
            return diseaseHistory;
        }catch (EmptyResultDataAccessException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    public List<Map<String, Object>> getDiseaseHistoryFromPatientLogin(String login){ //получить историю болезни по логину
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String sql = "SELECT disease_history.Diagnosis, disease_history.Recommendations, patient.First_name, patient.Last_name, patient.To_which_doctor  " +
                    "FROM virtual_patient " +
                    "INNER JOIN patient ON (virtual_patient.Patient_id = patient.Id) " +
                    "INNER JOIN disease_history ON (virtual_patient.Patient_id = disease_history.Patient_id) " +
                    "WHERE virtual_patient.Login = ?";
            list = getJdbcTemplate().queryForList(sql, login);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list; //diagnosis, recommendation, first_name, last_name, to_wich_doctor
    }
}

