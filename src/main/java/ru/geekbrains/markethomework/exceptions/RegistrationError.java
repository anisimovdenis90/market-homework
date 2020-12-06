package ru.geekbrains.markethomework.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegistrationError {
    private int status;
    private List<String> message;
    private Date timestamp;

    public RegistrationError(String message) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = new ArrayList<>(Arrays.asList(message));
        this.timestamp = new Date();
    }

    public RegistrationError(List<ObjectError> errors) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = errors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        this.timestamp = new Date();
    }
}
