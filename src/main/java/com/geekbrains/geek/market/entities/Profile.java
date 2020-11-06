package com.geekbrains.geek.market.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birthyear")
    private Integer birthyear;

    @Column(name = "gender")
    private String gender;

    @Column(name = "city")
    private String city;

    public Profile() {
    }

    public Profile(String firstname, String lastname, String phone, String email, Integer birthyear, String gender, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.birthyear = birthyear;
        this.gender = gender;
        this.city = city;
    }
}