package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.VirtualPatient;
import com.example.hospitalregistration.mapper.VirtualPatientMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class VirtualPatientDAO extends JdbcDaoSupport {

    @Autowired
    public VirtualPatientDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final Logger logger = LogManager.getLogger(VirtualPatientDAO.class);


    public VirtualPatient findVirtualPatientByLogin(String login) { //получить пациента по логину
        String sql = VirtualPatientMapper.BASE_SQL_VIRTUAL_PATIENT + " WHERE vp.Login = ? ";

        VirtualPatientMapper mapper = new VirtualPatientMapper();
        try {
            VirtualPatient virtualPatient = this.getJdbcTemplate().queryForObject(sql, mapper, login);
            return virtualPatient;
        } catch (EmptyResultDataAccessException e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public void saveVirtualPatient(VirtualPatient virtualPatient){   //добавить пациента в базу

        Long patientId = virtualPatient.getPatientId();
        String encrytedPassword = virtualPatient.getEncrytedPassword();
        String login = virtualPatient.getLogin();
        String role = virtualPatient.getRole();

        String sqlSave = "INSERT INTO virtual_patient(Patient_id, Encryted_password, Login, Role) VALUES (?,?,?,?)";
        Object[] params = new Object[]{patientId,encrytedPassword,login,role};
        //VirtualPatientMapper mapper = new VirtualPatientMapper();
        try {
            this.getJdbcTemplate().update(sqlSave,params);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
    }

    public boolean deleteVirtualPatient(long id){ //удалить пациента из базы
        String sqlDelete = "DELETE FROM virtual_patient WHERE Id = ?";
        Object[] params = new Object[]{id};
        VirtualPatientMapper mapper = new VirtualPatientMapper();
        try {
            this.getJdbcTemplate().update(sqlDelete,params,mapper);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return true;
    }

    /*
    public boolean updateVirtualPatient(long id){ //обновить пациента в базе
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
