package com.solvd.socialnetwork.builders;

import java.time.LocalDate;

import com.solvd.socialnetwork.models.User;

public class UserBuilder {
    private final User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder login(String login) {
        this.user.setLogin(login);
        return this;
    }

    public UserBuilder email(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.user.setFirstName(firstName);
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.user.setLastName(lastName);
        return this;
    }

    public UserBuilder birthDate(LocalDate birthDate) {
        this.user.setBirthDate(birthDate);
        return this;
    }

    public User build() {
        return this.user;
    }

}
