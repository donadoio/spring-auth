package io.donado.site.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO {
    private String username;

    private String password;
}
