package com.example.demo.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateUserDto {
    @Size(min = 8, max = 50)
    private String login;
    @Size(min = 8, max = 100)
    private String password;
    @Size(min = 8, max = 100)
    private String passwordRepeat;
    @Size(max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
    private String about;
    private String address;
}
