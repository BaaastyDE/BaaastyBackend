package de.baaasty.baaastybackend.discord.repository;

import de.baaasty.baaastybackend.discord.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {
}
