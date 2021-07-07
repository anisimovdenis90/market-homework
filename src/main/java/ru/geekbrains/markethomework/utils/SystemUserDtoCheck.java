package ru.geekbrains.markethomework.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.markethomework.dto.SystemUserDto;

public abstract class SystemUserDtoCheck {
    private SystemUserDtoCheck next;

    public abstract ResponseEntity<?> check(SystemUserDto userDto);

    protected ResponseEntity<?> checkNext(SystemUserDto userDto) {
        if (next == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return next.check(userDto);
    }

    public SystemUserDtoCheck link(SystemUserDtoCheck userDtoCheck) {
        this.next = userDtoCheck;
        return userDtoCheck;
    }
}
