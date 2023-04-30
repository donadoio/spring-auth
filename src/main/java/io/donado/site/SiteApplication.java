package io.donado.site;

import io.donado.site.config.RsaKeyProperties;
import io.donado.site.model.Role;
import io.donado.site.model.SecurityUser;
import io.donado.site.repository.RoleRepository;
import io.donado.site.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) { return; }
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			SecurityUser admin = new SecurityUser(1, "admin", passwordEncoder.encode("Password"), roles);
			userRepository.save(admin);
		};
	}

}
