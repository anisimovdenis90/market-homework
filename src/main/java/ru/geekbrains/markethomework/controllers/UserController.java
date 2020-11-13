package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.JwtResponse;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.exceptions.MarketError;
import ru.geekbrains.markethomework.services.ProfileService;
import ru.geekbrains.markethomework.services.RoleService;
import ru.geekbrains.markethomework.services.UserService;

import java.util.Arrays;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewUser(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String email
    ) {
        if (userService.isPresent(username)) {
            return new ResponseEntity<>(
                    new MarketError(HttpStatus.UNAUTHORIZED.value(),
                    String.format("Username %s already exist", username)),
                    HttpStatus.UNAUTHORIZED);
        }
        Profile profile = new Profile();
        profileService.saveProfile(profile);
        User user = new User(username, passwordEncoder.encode(password), email, Arrays.asList(roleService.findByName("ROLE_USER")), profile);
        userService.saveNewUser(user);
        return ResponseEntity.ok(user);
    }
}
