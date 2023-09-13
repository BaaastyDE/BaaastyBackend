package de.baaasty.baaastybackend.user.controller;

import de.baaasty.baaastybackend.user.entity.User;
import de.baaasty.baaastybackend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/users")
@RestController
public class UserController {
    
    private final de.baaasty.baaastybackend.user.service.UserService UserService;
    
    private final HttpServletRequest httpServletRequest;

    public UserController(UserService UserService, HttpServletRequest httpServletRequest) {
        this.UserService = UserService;
        this.httpServletRequest = httpServletRequest;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(UserService.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User> getUser(@PathVariable UUID uuid) {
        return ResponseEntity.ok(UserService.getUserById(uuid));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User User) {
        return ResponseEntity
                .created(URI.create(httpServletRequest.getRequestURI()))
                .body(UserService.createUser(User));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User User) {
        return ResponseEntity.ok(UserService.updateUser(User));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<User> deleteUser(@PathVariable UUID uuid) {
        UserService.deleteUser(uuid);
        return ResponseEntity.ok().build();
    }
    
}
