package com.udemy.spring.course.core.config;

import com.udemy.spring.course.core.model.Coach;
import com.udemy.spring.course.core.model.RifleCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoachConfig {

    @Bean
    public Coach rifleCoach() {
        return new RifleCoach();
    }

}
