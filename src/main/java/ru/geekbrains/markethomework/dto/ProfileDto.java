package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.markethomework.entities.Profile;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;

    @Size(max = 30)
    private String username;

    @Size(max = 30)
    private String firstname;

    @Size(max = 30)
    private String lastname;
    private String birthday;

    @Size(max = 30)
    private String phone;

    @Size(max = 50)
    private String hometown;
    private String gender;

    @Size(min = 3, message = "Please, enter right confirmation password")
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
