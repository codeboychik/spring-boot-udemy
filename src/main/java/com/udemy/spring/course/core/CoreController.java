package com.udemy.spring.course.core;

import com.udemy.spring.course.core.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
public class CoreController {

    private Coach coach;

    @Autowired
    public CoreController(
        @Qualifier("rifleCoach") Coach coach
    ) {
        this.coach = coach;
    }

    @GetMapping("/get-training")
    public String getTraining() {
        return this.coach.getDailyWorkout();
    }

}
