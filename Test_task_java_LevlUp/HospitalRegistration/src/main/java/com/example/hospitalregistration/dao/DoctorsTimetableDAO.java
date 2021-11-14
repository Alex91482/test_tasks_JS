package com.example.hospitalregistration.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorsTimetableDAO extends JdbcDaoSupport{

    @Autowired
    public DoctorsTimetableDAO (DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private static final Logger logger = LogManager.getLogger(DoctorsTimetableDAO.class);
    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public List<Map<String, Object>>  getDoctorsTimetableFromMonth(String date){
        //вернуть расписание дежурства врачей на месяц, месяц принемает формат гггг.мм.дд
        List<Map<String, Object>> list = new ArrayList<>();
        try{
            if(date == null || date.equals("")){
                System.out.println("Error date is null" + date);
                return list;
            }
            Date date1 = formater.parse(date); //приводим к нужному формату
            String sql = "SELECT dt.Date, doctor.Last_name, doctor.First_name, doctor.specialization FROM doctors_timetable dt INNER JOIN doctor ON (dt.doctor_id = doctor.id)"
                    + " WHERE dt.Date >= ? ::date and dt.Date < ? ::date + '1 month'::interval";
            list = getDoctorTimetableList(sql,date1);

        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list; //список хранит мапу где ключи String это название колонок из соедененных таблиц врачи и расписание_врачей
    }

    public List<Map<String, Object>> getDoctorsTimetableFromLastName(String doctorLastName){
        String sql = "SELECT * FROM doctors_timetable INNER JOIN doctor ON (doctors_timetable.doctor_id = doctor.id) WHERE doctor.last_name = ?";
        System.out.println(doctorLastName);
        List<Map<String, Object>> list = getDoctorTimetableList(sql,doctorLastName);
        System.out.println(list.size());
        return list; //создаем лист с вложенными листами где каждый вложенный лист хранит одну строку из сделанного запроса sql
    }

    public  List<Map<String, Object>> getDoctorTimetableFromDoctorId(long id){
        //вернуть расписание по id доктора
        String sql = "SELECT * FROM doctors_timetable INNER JOIN doctor ON (doctors_timetable.doctor_id = doctor.id) WHERE doctor.id =?";
        List<Map<String, Object>> list = getDoctorTimetableList(sql,id);
        return list; //аналогично как и с фафилией доктора
    }

    private List<Map<String, Object>> getDoctorTimetableList(String sql, Object o){ //общий метод по запросу к бд с разницей по какому параметру будет запрос id или last name
        List<Map<String, Object>> list = new ArrayList<>(); //в мапе будут содержатся значения сторок (Object) с ключами которые обозначают названия колонок (String)

        try{
            //проверяем что запрашивают
            if(o instanceof Long){ //если по id
                long x = (Long) o;
                list = getJdbcTemplate().queryForList(sql, x);
            } else if (o instanceof String){ //если по фамилии
                String x = (String) o;
                list = getJdbcTemplate().queryForList(sql, x);
            }
            else if (o instanceof Date){ //если по месяцу
                Date x = (Date) o;
                Object[] param = {x,x}; //в запросе sql содержится два параметра который на самом деле являестя одной и той же датой
                list = getJdbcTemplate().queryForList(sql,param);
            }
            else{
                logger.warn("Format error. Object format is not correct. Error in the block of doctors' schedule.");
                return list; //если формат объекта не предусмотрен
            }
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list; //возвращает список с мапами в которых ключи это название колонок из сущностей по которым сделана выборка если не ясно то смотреть маперы для сущностей
    }


}
