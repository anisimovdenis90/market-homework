package ru.geekbrains.markethomework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.markethomework.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
