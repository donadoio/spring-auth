package io.donado.site.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RegistrationDTO {
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    public String toString() {
        return "Register info: username: " + this.username + "] password: [" + this.password + ']';
    }
}
