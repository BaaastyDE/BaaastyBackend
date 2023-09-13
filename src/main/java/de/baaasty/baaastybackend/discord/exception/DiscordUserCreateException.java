package de.baaasty.baaastybackend.discord.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class DiscordUserCreateException extends ResponseStatusException {

    public DiscordUserCreateException() {
        super(HttpStatus.BAD_REQUEST, "The discord user ID is missing or invalid.");
    }

}