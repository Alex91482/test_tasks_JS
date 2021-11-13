package com.example.hospitalregistration.dao;

import com.example.hospitalregistration.mapper.DoctorsTimetableMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
            if(date == null | date.equals("")){
                System.out.println("Error date is null" + date);
                return list;
            }
            Date date1 = formater.parse(date); //приводим к нужному формату
            String sql = "SELECT dt.Date, doctor.Last_name, doctor.First_name, doctor.specialization FROM doctors_timetable dt INNER JOIN doctor ON (dt.doctor_id = doctor.id)"
                    + " WHERE dt.Date >= ? ::date and dt.Date < ? ::date + '1 month'::interval";

            Object[] param = {date1,date1};
            list = getJdbcTemplate().queryForList(sql,param); //получаем Map где ключи это название колонок

        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return list; //список хранит мапу где ключи String это название колонок из соедененных таблиц врачи и расписание_врачей
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
                System.out.println("stage instance");
                Date x = (Date) o;
                //Date x =formater.format(one.getTime());
                //Date x = (Date) o;
                //String x =formater.format(one.getTime());
                System.out.println("instance compelite " + x);
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
