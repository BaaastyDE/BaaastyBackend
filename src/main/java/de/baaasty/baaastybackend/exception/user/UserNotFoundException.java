package de.baaasty.baaastybackend.exception.user;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(UUID uuid) {
        super("The user with the specified UUID '%s' was not found.".formatted(uuid));
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
