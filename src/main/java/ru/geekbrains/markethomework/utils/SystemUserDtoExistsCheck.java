package ru.geekbrains.markethomework.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.markethomework.dto.SystemUserDto;
import ru.geekbrains.markethomework.exceptions.InputDataError;
import ru.geekbrains.markethomework.services.UserService;

@RequiredArgsConstructor
public class SystemUserDtoExistsCheck extends SystemUserDtoCheck {
    private final UserService userService;

    @Override
    public ResponseEntity<?> check(SystemUserDto userDto) {
        final String username = userDto.getUsername();
        if (username == null || username.isBlank()) {
            return new ResponseEntity<>(new InputDataError("Incorrect username"), HttpStatus.BAD_REQUEST);
        }
        if (userService.isPresent(username)) {
            return new ResponseEntity<>(new InputDataError(String.format("Username %s already exist", username)), HttpStatus.BAD_REQUEST);
        }
        return checkNext(userDto);
    }
}
