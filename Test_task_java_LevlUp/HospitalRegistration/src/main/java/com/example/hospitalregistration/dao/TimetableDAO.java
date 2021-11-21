package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.Timetable;
import com.example.hospitalregistration.mapper.TimetableMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TimetableDAO extends JdbcDaoSupport {

    @Autowired
    public TimetableDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final Logger logger = LogManager.getLogger(TimetableDAO.class);

    public List<Timetable> getTimetable() {
        List<Timetable> list = new ArrayList<>();
        try {
            list = getJdbcTemplate().query(TimetableMapper.BASE_SQL_TIMETABLE, new TimetableMapper());
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list;
    }

    public void addTimetable(Timetable timetable){
        long patientId = timetable.getPatientId();
        long doctorId = timetable.getDoctorId();
        Date date = timetable.getDate(); //to string?

        String sqlSave = "INSERT INTO timetable(Patient_id, Doctor_id, Date) VALUES (?,?,?)";
        Object[] params = new Object[]{patientId,doctorId,date};
        //TimetableMapper timetableMapper = new TimetableMapper();
        try {
            this.getJdbcTemplate().update(sqlSave,params);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
    }

    /*public List<Timetable> getTimetableForDay(String docLastName, String date) { //получение всех зарегистрированных пользователей по фамилии врача и конкретному дню
        List<Timetable> list = new ArrayList<>();
        String sql = TimetableMapper.BASE_SQL_TIMETABLE;
        TimetableMapper mapper = new TimetableMapper();
        try {
            //List<Timetable> listAll = getJdbcTemplate().query(sql, mapper);
            //for(){
                //перебираем пока не попадется нужный день
            //}
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list;
    }*/

}
