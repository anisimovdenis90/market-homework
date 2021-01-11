package ru.geekbrains.markethomework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.markethomework.dto.ProfileDto;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.exceptions.InputDataError;
import ru.geekbrains.markethomework.exceptions.MarketError;
import ru.geekbrains.markethomework.exceptions.ResourceNotFoundException;
import ru.geekbrains.markethomework.services.ProfileService;
import ru.geekbrains.markethomework.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/v1/profile", produces = "application/json", consumes = "application/json")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ProfileDto getUserProfile(Principal principal) {
        return new ProfileDto(profileService.findProfileByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user")));
    }

    @PutMapping
    public ResponseEntity<?> saveUserProfile(Principal principal, @RequestBody @Validated ProfileDto profileDto, BindingResult bindingResult) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new InputDataError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        if (profileDto.getConfirmationPassword() == null || !passwordEncoder.matches(profileDto.getConfirmationPassword(), user.getPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }
        Profile profile = profileService.findProfileByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        profile.setProfileForCurrentUserFromProfileDto(profileDto);
        profileService.saveProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
