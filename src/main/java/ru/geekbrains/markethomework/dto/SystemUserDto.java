package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SystemUserDto {

    @Size(min = 3, message = "Username must contains at least 4 symbols")
    private String username;

    @Email(message = "Wrong format of email")
    private String email;

    @Size(min = 3, message = "Password length must contains at least 3 symbols")
    private String password;

    @Size(min = 3, message = "Password length must contains at least 3 symbols")
    private String confirmationPassword;
}
