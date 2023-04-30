package io.donado.site.service;

import io.donado.site.dto.LoginResponseDTO;
import io.donado.site.model.Role;
import io.donado.site.model.SecurityUser;
import io.donado.site.repository.RoleRepository;
import io.donado.site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public SecurityUser registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new SecurityUser(0, username, encodedPassword, authorities));
    }

    public LoginResponseDTO login(String username, String password) {
        try {
            System.out.println("TRyout");
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("afyer auth mananger");
            String token = jwtService.generateToken(auth);
            System.out.println("After gen token");
            return new LoginResponseDTO(token);
        }
        catch (AuthenticationException e) {
            return new LoginResponseDTO("");
        }
    }
}
