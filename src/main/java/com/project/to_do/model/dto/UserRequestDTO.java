package com.project.to_do.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {


    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @Email(message = "you should to write @")
    @NotNull(message = "Email must not be null")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    @Pattern(regexp = "[A-Za-z-0-9@#$]{8,}",
                message = "At least one capital letter and accepted symbol @#$")
    private String password;


}
