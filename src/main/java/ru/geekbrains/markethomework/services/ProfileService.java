package ru.geekbrains.markethomework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.dto.ProfileDto;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.repositories.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public Optional<Profile> findProfileByUsername(String username) {
        return profileRepository.findByUsername(username);
    }
}
