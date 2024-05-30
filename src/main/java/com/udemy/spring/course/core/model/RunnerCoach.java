package com.udemy.spring.course.core.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class RunnerCoach implements Coach {

    @Override
    public String getDailyWorkout(){ return "Runner coach workout"; }

}
