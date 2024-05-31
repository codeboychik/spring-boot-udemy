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
    private Coach secondaryCoach;

    @Autowired
    public ConstructorController(
            @Qualifier("boxCoach") Coach coach,
            @Qualifier("boxCoach") Coach secondaryCoach
    ) {
        this.coach = coach;
        this.secondaryCoach = secondaryCoach;
    }

    @GetMapping("/get-training")
    public String getTraining() {
        return this.coach.getDailyWorkout();
    }

    @GetMapping("/check")
    public Boolean check() {
        return this.coach == secondaryCoach;
    }

}
