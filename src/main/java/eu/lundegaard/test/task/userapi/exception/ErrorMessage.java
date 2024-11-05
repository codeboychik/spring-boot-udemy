package eu.lundegaard.test.task.userapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * 
 * File is created by andreychernenko at 03.11.2024
 * 
 * 
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    String message;
    String error;
    List<String> errors;

}
