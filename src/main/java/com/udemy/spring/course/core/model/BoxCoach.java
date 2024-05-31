package com.udemy.spring.course.core.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BoxCoach implements Coach {

    BoxCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout(){ return "Box coach workout"; }
    @PostConstruct
    private void postConstruct() {
        System.out.println("PostConstruct: " + getClass().getSimpleName());
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("PreDestroy: " + getClass().getSimpleName());
    }

}
