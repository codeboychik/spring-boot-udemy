package eu.lundegaard.test.task.userapi.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * File is created by andreychernenko at 03.11.2024
 */


public class UserDto {

    @NotBlank(message = "Username shouldn't be blank")
    @NotNull(message = "Username shouldn't be null")
    private String username;

    @Email
    @NotBlank(message = "Username shouldn't be blank")
    private String email;

}
