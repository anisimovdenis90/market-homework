package ru.geekbrains.markethomework.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.markethomework.dto.SystemUserDto;
import ru.geekbrains.markethomework.exceptions.InputDataError;

@RequiredArgsConstructor
public class SystemUserDtoPasswordCheck extends SystemUserDtoCheck {

    @Override
    public ResponseEntity<?> check(SystemUserDto userDto) {
        final String password = userDto.getPassword();
        final String confirmationPassword = userDto.getConfirmationPassword();
        if (password == null || confirmationPassword == null || password.isBlank() || confirmationPassword.isBlank()) {
            return new ResponseEntity<>(new InputDataError("Incorrect passwords"), HttpStatus.BAD_REQUEST);
        }
        if (!password.equals(confirmationPassword)) {
            return new ResponseEntity<>(new InputDataError("Password and confirmation password isn't equal"),
                    HttpStatus.BAD_REQUEST);
        }
        return checkNext(userDto);
    }
}
