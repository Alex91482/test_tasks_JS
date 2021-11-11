package com.example.hospitalregistration.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class GeneratorRandomSequence implements RandomGenerated{

    @Override //генерируем рандомный пароль из набора символов
    public String genPass(){
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @Override //генерация рандомного логина пользователя состоящего из цифр
    public String genLog(){
        return RandomStringUtils.randomNumeric(8);
    }
}
