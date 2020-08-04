package com.ibabanking.login.dto;

public class UserDto {

    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
