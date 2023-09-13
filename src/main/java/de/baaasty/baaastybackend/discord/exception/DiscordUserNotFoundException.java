package de.baaasty.baaastybackend.discord.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class DiscordUserNotFoundException extends ResponseStatusException {

    public DiscordUserNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, "The discord user with the specified ID '%s' was not found.".formatted(id));
    }

}