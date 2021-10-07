package com.example.antipatterns.service;

import com.example.antipatterns.entity.Patient;

public class GetFullNamePatient implements PrintInterface{   //данный класс это решение проблемы единственной ответственности для класса Patient

    public String getFullNamePatient(Patient patient) {    //например нужно что бы строка выводилась в другой кодировке
        String str1=patient.getFirstName(); //возможно пример не самый удачный для OCP, но решением данной проблемы
        String str2=patient.getLastName();  //будет наследование и переопределение, как пример класс GetFullNamePatientASCII
        return (str1 + " " + str2);
    }


    public String getFullNamePatient(Patient patient, boolean flag) {   //так может выглядеть "модифицированная" функция
        if (flag == true) {   //return encoding UTF-8
            String str3 = patient.getFirstName() + " " + patient.getLastName(); //функция изменена, в нее добавлен новый параметр flag и если где то использовалась данная функция то теперь там нужно дописывать флаг (при том условии что старую функцию удалили)
            return str3;
        }
        else {               //return encoding ASCII
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(patient.getFirstName() + " " + patient.getLastName());
                String data = sb.toString();
                byte[] array = data.getBytes("ASCII");
                String data1 = new String(array, "ASCII");
                return data1;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "Oops...";
    }

    public void stringToAscii(){ //в данном случе имеем нарушение принципа разделения интерфейса ISP
        //этот метод тут не нужен но поскольку он есть в интерфейсе то требуется его "реализация"
    }
}
