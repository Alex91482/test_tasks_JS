package com.example.hospitalregistration.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.example.hospitalregistration.entity.Doctor;
import com.example.hospitalregistration.mapper.DoctorMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorDAO extends JdbcDaoSupport{

    private static final Logger logger = LogManager.getLogger(DoctorDAO.class);

    @Autowired
    public DoctorDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Doctor> getDoctors() { //метод возвращает список врачей
        String sql = DoctorMapper.BASE_SQL_DOCTOR;

        DoctorMapper mapper = new DoctorMapper();
        List<Doctor> list = this.getJdbcTemplate().query(sql, mapper);
        return list; //возвращает список фамилия имя специализация врача
    }

    public Doctor getDoctorById(Long id) { //метод поиска врача по id
        String sql = DoctorMapper.BASE_SQL_DOCTOR + " WHERE d.Id = ? ";

        DoctorMapper mapper = new DoctorMapper();
        try {
            Doctor doctor = this.getJdbcTemplate().queryForObject(sql, mapper, id);
            return doctor;
        } catch (EmptyResultDataAccessException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    public Map<String, Object> getDoctorByDateAndSpec(Date date, String specialization){ //какой врач данной специализации дежурит в данный день
        //пациент не указал к какому врачу он собирается записатся значит ползем в бд и смотрим кто дежурит по расписанию
        String sql = "SELECT doctor.Id, doctor.Last_name, doctor.First_name, doctor.specialization FROM doctors_timetable dt INNER JOIN doctor ON (dt.doctor_id = doctor.id)" +
                        "WHERE dt.Date = ? and doctor.specialization = ?";
        try{
            Object[] params = {date,specialization};
            Map<String, Object> doctor = this.getJdbcTemplate().queryForMap(sql,params);
            return doctor;
        }catch (Exception e){
            logger.warn(e.getMessage()); //перехватываем ошибку например не верный формат даты
        }
        return null; //метод должен возвращать врача в формате map, полученный врач дежурит в данный день
    }
}
