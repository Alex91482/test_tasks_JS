package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.DiseaseHistory;
import com.example.hospitalregistration.mapper.DiseaseHistoryMapper;

import com.example.hospitalregistration.mapper.TimetableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class DiseaseHistoryDAO extends JdbcDaoSupport{

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
            e.getMessage();
        }
    }

    public DiseaseHistory findDiseaseHistory(long id){ //получить историю болезни
        String sql = DiseaseHistoryMapper.BASE_SQL_DISEASE_HISTORY + " WHERE dh.Id = ? ";
        Object[] params = new Object[] { id };
        DiseaseHistoryMapper mapper = new DiseaseHistoryMapper();
        try{
            DiseaseHistory diseaseHistory = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return diseaseHistory;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

