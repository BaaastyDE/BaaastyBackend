package de.baaasty.baaastybackend.exception.user;

import java.util.UUID;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(UUID uuid) {
        super("A user with the specified UUID '%s' already exists.".formatted(uuid));
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
