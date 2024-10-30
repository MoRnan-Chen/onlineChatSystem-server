package com.gdpu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO
{
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_@-]{1,16}$")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9@]{6,16}$")
    private String password;
    @Pattern(regexp = "^[^\s]{1,16}$")
    private String nickname;
    @Email
    private String email;
    private String userPic;
    private LocalDateTime updateTime;

}
