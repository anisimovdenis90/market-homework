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

import java.util.Arrays;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> saveNewUser(@RequestBody @Validated SystemUserDto systemUserDto, BindingResult bindingResult) {

        if (userService.isPresent(systemUserDto.getUsername())) {
            return new ResponseEntity<>(new InputDataError(String.format("Username %s already exist", systemUserDto.getUsername())),
                    HttpStatus.BAD_REQUEST);
        }

        if (!systemUserDto.getPassword().equals(systemUserDto.getConfirmationPassword())) {
            return new ResponseEntity<>(new InputDataError("Password and confirmation password isn't equal"),
                    HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new InputDataError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
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
