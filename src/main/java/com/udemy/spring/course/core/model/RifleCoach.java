package com.udemy.spring.course.core.model;

public class RifleCoach implements Coach {

    public RifleCoach() {
        System.out.println("In constructor: "  + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Rifle coach daily workout";
    }
}
