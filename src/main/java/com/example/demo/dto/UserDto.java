package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private String login;
    private String firstName;
    private String lastName;
    private String about;
    private String address;
}
