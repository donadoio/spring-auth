package io.donado.site.endpoints.auth;

import io.donado.site.config.JwtService;
import io.donado.site.user.Role;
import io.donado.site.user.UserRepository;
import io.donado.site.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository        userRepository;
    private final PasswordEncoder       passwordEncoder;
    private final JwtService            jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest req) {
        var user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user, user.getRole());
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public AuthenticationResponse auth(AuthenticationRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword())
        );

        var user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(); // Throw a good one

        var jwtToken = jwtService.generateToken(user, user.getRole());

        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

}
