package com.gdpu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable
{
    private Long id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_@-]{1,16}$")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9@]{6,16}$")
    private String password;
    @Pattern(regexp = "^[^\s]{1,16}$")
    private String nickname;
    @Email
    private String email;
    @URL
    private String userPic;
    private String sex;
    private LocalDateTime updateTime;

}
