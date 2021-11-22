package com.example.hospitalregistration.security.bcrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncrytedPasswordUtils {
    // Encryte Password with BCryptPasswordEncoder
    public String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /*
    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encrytePassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);
    }
    */
}
