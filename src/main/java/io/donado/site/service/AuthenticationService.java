package io.donado.site.service;

import io.donado.site.dto.AuthenticateResponseDTO;
import io.donado.site.model.Role;
import io.donado.site.model.SecurityUser;
import io.donado.site.model.Session;
import io.donado.site.repository.RoleRepository;
import io.donado.site.repository.SessionRepository;
import io.donado.site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final SessionRepository sessionRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final SessionService sessionService;


    // todo create session and add to user on creation, example below.
//    Role adminRole = roleRepository.save(new Role("ADMIN"));
//			roleRepository.save(new Role("USER"));
//
//    Set<Role> roles = new HashSet<>();
//			roles.add(adminRole);
//
//    SecurityUser admin = new SecurityUser(1, "isaac@donado.io", "admin", passwordEncoder.encode("password"), roles);
//			userRepository.save(admin);
    public AuthenticateResponseDTO registerUser(String email, String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        Set<Session> sessions = new HashSet<>();
        authorities.add(userRole);

        SecurityUser newUser = new SecurityUser(0, email, username, encodedPassword, "local", 0, authorities, sessions);
        // Pre saving in order to get the auto generated ID
        SecurityUser savedUser = userRepository.save(newUser);

        savedUser.setAuthTypeId(savedUser.getUserId());
        userRepository.save(savedUser);

        return new AuthenticateResponseDTO("");
    }


    public AuthenticateResponseDTO login(String username, String password) {
        try {
            System.out.println("Before auth");
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Before making token");
            String token = sessionService.generateToken(auth);
            String encodedToken = passwordEncoder.encode(token);

            System.out.println("Trying to find user");
            Optional<SecurityUser> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                System.out.println("Making timestamp");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("Putting timestamp inn session");
                Session session = new Session(encodedToken, timestamp, "Hello");
                System.out.println("trynna save da sessionn");
                sessionRepository.saveAndFlush(session);

                System.out.println("getting user");
                SecurityUser temp = user.get();


                System.out.println("getting user sessions");
                Set<Session> sessions = temp.getSessions();

                Iterator<Session> it2 = sessions.iterator();

                System.out.println(
                        "Iterate HashSet using iterator : ");

                // Iterate HashSet with the help of hasNext() and
                // next() method
                while (it2.hasNext()) {

                    // Print HashSet values
                    System.out.print(it2.next().getSessionId() + it2.next().getLocation() + " ");
                }

                System.out.println("adding the session to user's sessions");
                sessions.add(session);

                System.out.println("adding modified sessions to user");
                temp.setSessions(sessions);

                // Create a iterator of type integer to iterate
                // HashSet
                Iterator<Session> it = temp.getSessions().iterator();

                System.out.println(
                        "Iterate HashSet using iterator : ");

                // Iterate HashSet with the help of hasNext() and
                // next() method
//                while (it.hasNext()) {
//
//                    // Print HashSet values
//                    System.out.print(it.next().getSessionId() + it.next().getLocation() + " ");
//                }
                System.out.println("saving user");
                userRepository.save(temp);

                return new AuthenticateResponseDTO(token);
            } else {
                return new AuthenticateResponseDTO("");
            }
        }
        catch (AuthenticationException e) {
            return new AuthenticateResponseDTO("");
        }
    }

    public AuthenticateResponseDTO refresh(String token) {
        try {
            String subject = sessionService.extractClaim(token, "subj");
            String scope = sessionService.extractClaim(token, "roles");

            if (subject == null || scope == null) {
                return new AuthenticateResponseDTO("");
            }

            String newToken = sessionService.refreshToken(subject, scope);
            return new AuthenticateResponseDTO(newToken);
        }
        catch (AuthenticationException e) {
            return new AuthenticateResponseDTO("");
        }
    }

}
