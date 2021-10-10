package com.example.calc.Entity;

import lombok.Getter;
import lombok.Setter;

public class CalcSwitch {

    @Getter
    @Setter
    private int x;

    @Getter @Setter
    private  int y;

    @Getter @Setter
    private String str;

    public CalcSwitch(){}

    public CalcSwitch(int x,int y, String str){
        this.x = x;
        this.y = y;
        this.str = str;
    }

    public int integer(){
        switch (str){
            case "sum":
                return x+y;
            case "subst":
                return x-y;
            case "multipl":
                return x*y;
            case "divis":
                return x/y;
        }
        return -1;
    }
}
