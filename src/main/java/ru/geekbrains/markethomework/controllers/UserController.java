package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.SystemUserDto;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.exceptions.InputDataError;
import ru.geekbrains.markethomework.services.ProfileService;
import ru.geekbrains.markethomework.services.RoleService;
import ru.geekbrains.markethomework.services.UserService;
import ru.geekbrains.markethomework.utils.SystemUserDtoCheck;
import ru.geekbrains.markethomework.utils.SystemUserDtoExistsCheck;
import ru.geekbrains.markethomework.utils.SystemUserDtoPasswordCheck;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RestController
@RequestMapping(consumes = "application/json")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private SystemUserDtoCheck systemUserDtoCheck;

    @PostConstruct
    public void init() {
        systemUserDtoCheck = new SystemUserDtoExistsCheck(userService);
        systemUserDtoCheck.link(new SystemUserDtoPasswordCheck());
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveNewUser(@RequestBody @Validated SystemUserDto systemUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new InputDataError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        final ResponseEntity<?> responseEntity = systemUserDtoCheck.check(systemUserDto);
        if (responseEntity.hasBody()) {
            return responseEntity;
        }

        Profile profile = new Profile();
        profileService.saveProfile(profile);

        User user = new User(
                systemUserDto.getUsername(),
                passwordEncoder.encode(systemUserDto.getPassword()),
                systemUserDto.getEmail(),
                Arrays.asList(roleService.findUserRole()),
                profile);

        userService.saveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
