package de.baaasty.baaastybackend.controller;

import de.baaasty.baaastybackend.entity.User;
import de.baaasty.baaastybackend.service.UserService;
import de.baaasty.baaastybackend.exception.user.UserAlreadyExistsException;
import de.baaasty.baaastybackend.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUser(@PathVariable UUID uuid) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUuid(uuid));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        } catch (UserAlreadyExistsException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateUser(@PathVariable UUID uuid, @RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(uuid, user));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID uuid) {
        try {
            userService.deleteUser(uuid);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
