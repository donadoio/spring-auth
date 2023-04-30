package io.donado.site.repository;

import io.donado.site.model.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SecurityUser, Integer> {
    Optional<SecurityUser> findByUsername(String username);
}