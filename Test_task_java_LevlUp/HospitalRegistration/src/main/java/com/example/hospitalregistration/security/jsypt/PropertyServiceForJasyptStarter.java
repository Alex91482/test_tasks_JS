package com.example.hospitalregistration.security.jsypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

//@Service
public class PropertyServiceForJasyptStarter {

    //@Value("${encrypted.property}")
    private String property = "ENC(uTSqb9grs1+vUv3iN8lItC0kl65lMG+8)";

    public String getProperty() {
        return property;
    }

    public String getPasswordUsingEnvironment(Environment environment) {
        return environment.getProperty("encrypted.property");
    }
}
