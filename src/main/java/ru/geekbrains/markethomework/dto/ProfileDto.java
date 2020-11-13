package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.markethomework.entities.Profile;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String birthday;
    private String phone;
    private String hometown;
    private String gender;
    private String confirmationPassword;

    public ProfileDto(Profile p) {
        this.id = p.getId();
        this.username = p.getUser().getUsername();
        this.firstname = p.getFirstname();
        this.lastname = p.getLastname();
        this.birthday = p.getBirthday();
        this.phone = p.getPhone();
        this.hometown = p.getHometown();
        this.gender = p.getGender();
    }
}
