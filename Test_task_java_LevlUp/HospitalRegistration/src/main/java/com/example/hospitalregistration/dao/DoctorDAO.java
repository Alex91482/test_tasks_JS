package com.example.hospitalregistration.dao;

import java.util.List;

import javax.sql.DataSource;

import com.example.hospitalregistration.entity.Doctor;
import com.example.hospitalregistration.mapper.DoctorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorDAO extends JdbcDaoSupport{

    @Autowired
    public DoctorDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Doctor> getDoctors() {
        String sql = DoctorMapper.BASE_SQL_DOCTOR;

        Object[] params = new Object[] {};
        DoctorMapper mapper = new DoctorMapper();
        List<Doctor> list = this.getJdbcTemplate().query(sql, params, mapper);

        return list;
    }

    public Doctor findDoctor(Long id) { //создать метод для поиска по фамилии
        String sql = DoctorMapper.BASE_SQL_DOCTOR + " WHERE d.Id = ? ";

        Object[] params = new Object[] { id };
        DoctorMapper mapper = new DoctorMapper();
        try {
            Doctor doctor = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return doctor;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
