package com.gcu.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginModel {

	@NotNull(message = "Username is required")
	@Size(min = 2, message = "Username must be at least 2 characters")
	private String username;

	@NotNull(message = "Password is required")
	@Size(min = 2, message = "Password must be at least 2 characters")
	private String password;

    // Constructor (optional)
    public LoginModel() {}

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}