package com.example.hospitalregistration.security.jsypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JasyptUtil {

    //@Value("${encrypted.property}")
    private static final String password = "ENC(uTSqb9grs1+vUv3iN8lItC0kl65lMG+8)";

    /*
     * Jasypt генерирует зашифрованные результаты
     * @param password Пароль шифрования, установленный в файле конфигурации
     * @param value зашифрованное значение
     * @return
     */
    public String encyptPwd(String value){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.encrypt(value);
        return result;
    }

    /*
     * Расшифровать
     * @param password Зашифрованный пароль, установленный в файле конфигурации
     * @param value расшифровывает зашифрованный текст
     * @return
     */
    public String decyptPwd(String password,String value){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.decrypt(value);
        return result;
    }

    private static SimpleStringPBEConfig cryptor(String password){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    /*public static void main(String[] args){

        // шифрование
        System.out.println(encyptPwd("vUv3iN8lItC0kl65lMG","123456"));
        // Расшифровать
        System.out.println(decyptPwd("vUv3iN8lItC0kl65lMG","ttJNWJTL8CsNdsAMnTEd3Q=="));
    }*/
}

