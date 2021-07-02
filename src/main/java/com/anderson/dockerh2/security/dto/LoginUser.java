package com.anderson.dockerh2.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    @NotBlank
    private String nameUser;

    @NotBlank
    private String password;

}
