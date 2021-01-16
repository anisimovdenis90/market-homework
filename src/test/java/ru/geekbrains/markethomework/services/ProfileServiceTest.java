package ru.geekbrains.markethomework.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.repositories.ProfileRepository;

import java.util.Optional;

@SpringBootTest(classes = ProfileService.class)
public class ProfileServiceTest {
    @Autowired
    ProfileService profileService;

    @MockBean
    ProfileRepository profileRepository;

    @Test
    public void saveProfileTest() {
        Profile newProfile = new Profile();
        newProfile.setFirstname("John");
        newProfile.setLastname("Kane");

        profileService.saveProfile(newProfile);
        Mockito.verify(profileRepository).save(newProfile);
    }

    @Test
    public void findProfileByUsername() {
        Profile profileInDB = new Profile();
        profileInDB.setFirstname("John");
        profileInDB.setLastname("Kane");

        Mockito.doReturn(Optional.of(profileInDB))
                .when(profileRepository)
                .findByUsername("John");

        Profile profile = profileService.findProfileByUsername("John").get();
        Assertions.assertNotNull(profile);
        Assertions.assertEquals(profileInDB.getFirstname(), profile.getFirstname());
        Assertions.assertEquals(profileInDB.getLastname(), profile.getLastname());
        Mockito.verify(profileRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
    }
}
