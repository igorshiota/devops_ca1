package com.ca1.igorspetitions;


public class Signatory {
    private String name;
    private String email;

    public Signatory(String name, String email) {
        if (!email.matches("[^@]+@[^@]+\\.[^@]+")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
