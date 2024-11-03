package eu.lundegaard.test.task.userapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 
 * File is created by andreychernenko at 03.11.2024
 * 
 * 
 */


@AllArgsConstructor
@Getter
public class ErrorMessage {

    String message;
    List<String> errors;

}
