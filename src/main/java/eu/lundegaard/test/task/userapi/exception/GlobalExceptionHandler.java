package eu.lundegaard.test.task.userapi.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class, UserNotFoundException.class, AddressNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundExceptions(Exception ex) {
        String message = switch (ex) {
            case NoSuchElementException ignored -> "No such element";
            case UserNotFoundException ignored -> "No such user";
            case AddressNotFoundException ignored -> "No such address";
            case null, default -> "Resource not found";
        };

        return ErrorMessage.builder()
                .message(message)
                .error(ex.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ErrorMessage.builder()
                .message("Validation error")
                .errors(errors)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleConstrainViolationExceptions(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations();
        return ErrorMessage.builder()
                .message("Validation error")
                .errors(errors.stream().map(ConstraintViolation::getMessage).toList())
                .build();
    }
}
