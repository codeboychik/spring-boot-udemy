package com.udemy.spring.course.core.injections.constructor;

import com.udemy.spring.course.core.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constructor")
public class ConstructorController {

    private Coach coach;

    @Autowired
    public ConstructorController(@Qualifier("boxCoach") Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/get-training")
    public String getTraining() {
        return this.coach.getDailyWorkout();
    }

}
