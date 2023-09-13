package de.baaasty.baaastybackend.user.repository;

import de.baaasty.baaastybackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
