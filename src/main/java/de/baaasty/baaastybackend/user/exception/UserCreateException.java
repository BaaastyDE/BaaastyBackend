package de.baaasty.baaastybackend.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UserCreateException extends ResponseStatusException {

    public UserCreateException() {
        super(HttpStatus.BAD_REQUEST, "The user UUID is missing or invalid.");
    }

}