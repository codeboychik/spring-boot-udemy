package com.udemy.spring.course.core.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
// different instance for each bean of same class
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// same instance for each bean of same class
// @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BoxCoach implements Coach {

    BoxCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout(){ return "Box coach workout"; }


}
