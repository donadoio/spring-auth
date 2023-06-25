package io.donado.site.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotEmpty
    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",
            message="Invalid username, must start with a alphanumerical. " +
                    "May only include [-, _, .] special characters and must be 3-18 characters long."
    )
    private String username;

    @Size(min = 7, max = 25, message = "The length of password must be between 7 and 25 characters.")
    @NotEmpty(message = "Password is required.")
    private String password;
}
