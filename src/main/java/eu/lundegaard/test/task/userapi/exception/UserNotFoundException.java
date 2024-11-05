package eu.lundegaard.test.task.userapi.exception;

/**
 * File is created by andreychernenko at 04.11.2024
 */


public class UserNotFoundException extends NullPointerException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
