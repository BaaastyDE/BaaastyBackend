package de.baaasty.baaastybackend.discord.service;

import de.baaasty.baaastybackend.discord.entity.DiscordUser;
import de.baaasty.baaastybackend.discord.exception.DiscordUserCreateException;
import de.baaasty.baaastybackend.discord.exception.DiscordUserNotFoundException;
import de.baaasty.baaastybackend.discord.repository.DiscordUserRepository;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "discordUsers")
public class DiscordUserService {

    private final DiscordUserRepository discordUserRepository;

    public DiscordUserService(DiscordUserRepository discordUserRepository) {
        this.discordUserRepository = discordUserRepository;
    }

    public List<DiscordUser> getAllDiscordUsers() {
        return discordUserRepository.findAll();
    }

    @Cacheable(key = "#id")
    public DiscordUser getDiscordUserById(long id) {
        return discordUserRepository.findById(id).orElseThrow(() -> new DiscordUserNotFoundException(id));
    }

    @CachePut(key = "#discordUser.id")
    @CacheEvict(value = "users", allEntries = true)
    public DiscordUser createDiscordUser(DiscordUser discordUser) {
        if (discordUser.getId() == null) throw new DiscordUserCreateException();
        return discordUserRepository.save(discordUser);
    }

    @CachePut(key = "#discordUser.id")
    @CacheEvict(value = "users", allEntries = true)
    public DiscordUser updateDiscordUser(DiscordUser discordUser) {
        if (!discordUserRepository.existsById(discordUser.getId()))
            throw new DiscordUserNotFoundException(discordUser.getId());

        return discordUserRepository.save(discordUser);
    }

    @Caching(evict = {@CacheEvict(key = "#id"), @CacheEvict(value = "users", allEntries = true)})
    public void deleteDiscordUser(long id) {
        if (!discordUserRepository.existsById(id)) throw new DiscordUserNotFoundException(id);

        discordUserRepository.deleteById(id);
    }

}
