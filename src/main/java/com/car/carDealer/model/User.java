package com.car.carDealer.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {


    @NotEmpty(message = "Campul nu poate fi lasat gol")
    @Size(min = 4, max = 16,message = "Dimensiunea trebuie sa fie intre 4 si 16 caractere")
    private String firstName;

    @NotEmpty(message = "Campul nu poate fi lasat gol")
    @Size(min = 4, max = 16,message = "Dimensiunea trebuie sa fie intre 4 si 16 caractere")
    private String lastName;

    @Id
    @NotEmpty(message = "Campul nu poate fi gol")
    @Email(message = "Email-ul trebuie sa fie valid")
    private String email;

    @NotEmpty(message = "Campul nu poate fi gol")
    @Size(min = 4, max = 10, message = "Parola trebuie sa contina minim 4 caractere")
    private String password;


    public User() {
    }

    ;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }


}
