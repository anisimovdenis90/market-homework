package ru.geekbrains.markethomework.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.markethomework.entities.Profile;
import ru.geekbrains.markethomework.entities.User;
import ru.geekbrains.markethomework.repositories.ProfileRepository;

@DataJpaTest
@ActiveProfiles("test")
public class ProfileRepositoryTest {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void profileRepositoryTest() {
        User user = new User();
        user.setUsername("User");
        user.setPassword("100");
        user.setEmail("user@email.com");
        Profile profile = new Profile();
        profile.setUser(user);
        user.setProfile(profile);

        entityManager.persist(profile);
        entityManager.persist(user);
        entityManager.flush();

        Profile profileFromDb = profileRepository.findByUsername(user.getUsername()).get();

        Assertions.assertNotNull(profileFromDb);
        Assertions.assertEquals(profile, profileFromDb);
    }
}
