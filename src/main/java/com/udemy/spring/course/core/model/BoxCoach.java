package com.udemy.spring.course.core.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BoxCoach implements Coach {

    @Override
    public String getDailyWorkout(){ return "Box coach workout"; }


}
