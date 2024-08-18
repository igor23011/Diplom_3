package org.example;

public class CreateUser {
    private final String email;
    private final String password;
    private final String name;

    public CreateUser(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}