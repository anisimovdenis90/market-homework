package ru.geekbrains.markethomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.repositories.RoleRepository;
import ru.geekbrains.markethomework.services.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/signup")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping
    public String saveNewUser(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password,
                              @RequestParam(name = "email") String email) {
        User newUser = new User(username, passwordEncoder.encode(password), email, Arrays.asList(roleRepository.findById(1L).get()));
        userService.saveNewUser(newUser);
        return "redirect:/products";
    }
}
