package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.Timetable;
import com.example.hospitalregistration.mapper.TimetableMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class TimetableDAO extends JdbcDaoSupport {

    @Autowired
    public TimetableDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Timetable> getTimetable() {
        String sql = TimetableMapper.BASE_SQL_TIMETABLE;
        Object[] params = new Object[] {};
        TimetableMapper mapper = new TimetableMapper();
        List<Timetable> list = this.getJdbcTemplate().query(sql, params, mapper);

        return list;
    }

    public void addTimetable(Timetable timetable){
        long id =timetable.getId();
        long patientId = timetable.getPatientId();
        long doctorId = timetable.getDoctorId();
        Date date = timetable.getDate(); //to string?

        String sqlSave = "INSERT INTO timetable(Id, Patient_id, Doctor_id, Date) VALUES (?,?,?,?)";
        Object[] params = new Object[]{id,patientId,doctorId,date};
        TimetableMapper timetableMapper = new TimetableMapper();
        try {
            this.getJdbcTemplate().update(sqlSave,params,timetableMapper);
        }catch (Exception e){
            e.getMessage();
        }
    }
}
