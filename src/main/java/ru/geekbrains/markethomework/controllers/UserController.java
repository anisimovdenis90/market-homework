package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.services.RoleService;
import ru.geekbrains.markethomework.services.UserService;

import java.util.Arrays;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public void saveNewUser(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String email
    ) {
        User user = new User(username, passwordEncoder.encode(password), email, Arrays.asList(roleService.findByName("ROLE_USER")));
        userService.saveNewUser(user);
    }
}
