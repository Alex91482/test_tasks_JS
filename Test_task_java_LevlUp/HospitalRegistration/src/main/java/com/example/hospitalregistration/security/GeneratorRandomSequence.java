package com.example.hospitalregistration.security;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorRandomSequence implements RandomGenerated{

    @Override //генерируем рандомный пароль
    public String genPass(){
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @Override //генерация рандомного логина пользователя
    public int genLog(){
        return Integer.parseInt(RandomStringUtils.randomNumeric(8));
    }
}
