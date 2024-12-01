package com.gdpu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO implements Serializable
{
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9@]{6,16}$")
    private String oldPassword;
    @Pattern(regexp = "^[a-zA-Z0-9@]{6,16}$")
    private String newPassword;
    @Pattern(regexp = "^[a-zA-Z0-9@]{6,16}$")
    private String confirmPassword;
    private LocalDateTime updateTime;
}
