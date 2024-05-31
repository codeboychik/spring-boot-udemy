package com.udemy.spring.course.core.model;

import org.springframework.stereotype.Component;

@Component
public class RunnerCoach implements Coach {

    RunnerCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout(){ return "Runner coach workout"; }

}
