package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.services.ProfileService;
import ru.geekbrains.markethomework.services.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping()
    public Profile getUserProfile(Principal principal) {
        return userService.findByUsername(principal.getName()).getProfile();
    }

    @PutMapping()
    public void saveUserProfile(Principal principal,
                                @RequestParam(name = "password") String password,
                                @RequestParam Map<String, String> params
    ) {
        User user = userService.findByUsername(principal.getName());
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return;
        }
        Profile profile = user.getProfile();
        String value;
        if ((value = params.get("firstname")) != null && !value.isBlank()) {
            profile.setFirstname(value);
        }
        if ((value = params.get("lastname")) != null && !value.isBlank()) {
            profile.setLastname(value);
        }
        if ((value = params.get("birthday")) != null && !value.isBlank()) {
            profile.setBirthday(value);
        }
        if ((value = params.get("phone")) != null && !value.isBlank()) {
            profile.setPhone(value);
        }
        if ((value = params.get("gender")) != null && !value.isBlank()) {
            profile.setGender(value);
        }
        if ((value = params.get("hometown")) != null && !value.isBlank()) {
            profile.setHometown(value);
        }
        profileService.saveProfile(profile);
    }
}
