package ru.geekbrains.markethomework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "gender")
    private String gender;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;

    public Profile(String firstname, String lastname, String birthday, String phone, String hometown, String gender, User user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.phone = phone;
        this.hometown = hometown;
        this.gender = gender;
        this.user = user;
    }
}
