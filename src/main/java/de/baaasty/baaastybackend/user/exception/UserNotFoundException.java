package de.baaasty.baaastybackend.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Getter
public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(UUID uuid) {
        super(HttpStatus.NOT_FOUND, "The user with the specified UUID '%s' was not found.".formatted(uuid));
    }

}