package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.entity.DoctorsTimetable;
import com.example.hospitalregistration.mapper.DoctorsTimetableMapper;

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

    private static final Logger logger = LogManager.getLogger(PatientDAO.class);
    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public List<ArrayList<String>> getDoctorsTimetableFromMonth(String date){
        //вернуть расписание дежурства врачей на месяц, месяц принемает формат гггг.мм.дд
        List<ArrayList<String>> list = new ArrayList<>();
        try{
            Date date1 = formater.parse(date); //приводим к нужному формату
            String sql = DoctorsTimetableMapper.BASE_SQL_DOCTORS_TIMETABLE + " WHERE dt.Date >= ?::date and dt.Date < ?::date + '1 month'::interval";
            list = getDoctorTimetableList(sql,date1);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list; //список хранит объекты DoctorsTimetable в которых содержатся данные за месяц
    }

    public List<ArrayList<String>> getDoctorsTimetableFromLastName(String doctorLastName){
        String sql = "SELECT * FROM doctors_timetable INNER JOIN doctor ON (doctors_timetable.doctor_id = doctor.id) WHERE doctor.last_name = ?";
        List<ArrayList<String>> list = getDoctorTimetableList(sql,doctorLastName);
        return list; //создаем лист с вложенными листами где каждый вложенный лист хранит одну строку из сделанного запроса sql
    }

    public  List<ArrayList<String>> getDoctorTimetableFromDoctorId(long id){
        //вернуть расписание по id доктора
        String sql = "SELECT * FROM doctors_timetable INNER JOIN doctor ON (doctors_timetable.doctor_id = doctor.id) WHERE doctor.id =?";
        List<ArrayList<String>> list = getDoctorTimetableList(sql,id);
        return list; //аналогично как и с фафилией доктора
    }

    private List<ArrayList<String>> getDoctorTimetableList(String sql, Object o){ //общий метод по запросу к бд с разницей по какому параметру будет запрос id или last name
        List<ArrayList<String>> list = new ArrayList<>();
        try{
            List<Map<String, Object>> strong = new ArrayList<>(); //в мапе будут содержатся значения сторок (Object) с ключами которые обозначают названия колонок (String)
            //проверяем что запрашивают
            if(o instanceof Long){ //если по id
                long x = (Long) o;
                strong = getJdbcTemplate().queryForList(sql, x);
            } else if (o instanceof String){ //если по фамилии
                String x = (String) o;
                strong = getJdbcTemplate().queryForList(sql, x);
            }
            else if (o instanceof Date){
                Date x = formater.parse((String) o); //было Date x = (Date) o;
                strong = getJdbcTemplate().queryForList(sql,new DoctorsTimetableMapper(),x,x); //getJdbcTemplate().query(sql, new DoctorsTimetableMapper(), date1, date1);
            }
            else{
                logger.warn("Format error. Object format is not correct. Error in the block of doctors' schedule.");
                return list; //если формат объекта не предусмотрен
            }
            for (Map<String, Object> map : strong) { //получаем Map где ключи это название колонок
                ArrayList<String> nestedLoop =new ArrayList<>();
                //приводим object к Date потом форматируем по нужному образцу по умолчанию получаем string на выходе поэтому явное приведение в первой строке не нужно
                nestedLoop.add(formater.format((Date)map.get("date")));
                nestedLoop.add((String)map.get("last_name")); //по ключу (название колонки) получаем значение и явно приводим к формату String
                nestedLoop.add((String)map.get("first_name"));
                nestedLoop.add((String)map.get("specialization"));
                list.add(nestedLoop); //список состоящий из строки выборки готов добавляем его в list
            }
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list; //возвращает список со списками который содержит строки, строки это одна строка из выборки
    }


}
