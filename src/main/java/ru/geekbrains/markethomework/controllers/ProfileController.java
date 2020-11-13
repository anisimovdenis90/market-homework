package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.ProfileDto;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.exceptions.MarketError;
import ru.geekbrains.markethomework.exceptions.ResourceNotFoundException;
import ru.geekbrains.markethomework.services.ProfileService;
import ru.geekbrains.markethomework.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping(produces = "application/json")
    public ProfileDto getUserProfile(Principal principal) {
        return new ProfileDto(profileService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user")));
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> saveUserProfile(Principal principal, @RequestBody ProfileDto profileDto) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        if (profileDto.getConfirmationPassword() == null || !passwordEncoder.matches(profileDto.getConfirmationPassword(), user.getPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect password"), HttpStatus.UNAUTHORIZED);
        }
        Profile profile = profileService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        profile.setProfileForCurrentUserFromProfileDto(profileDto);
        profileService.saveProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}