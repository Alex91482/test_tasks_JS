package com.example.calc.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.calc.Entity.CalcSwitch;

@RestController
public class CalcController {

    @RequestMapping("calculator/{calc}/{x}/{y}")
    public int index(@PathVariable(name = "x")int x, @PathVariable(name = "y") int y, @PathVariable(name = "calc") String str){
        return new CalcSwitch(x,y,str).integer();
    }

}
