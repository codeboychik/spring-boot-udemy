package eu.lundegaard.test.task.userapi.exception;

/**
 * File is created by andreychernenko at 04.11.2024
 */


public class AddressNotFoundException extends NullPointerException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
