package ru.geekbrains.markethomework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.repositories.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }
}
