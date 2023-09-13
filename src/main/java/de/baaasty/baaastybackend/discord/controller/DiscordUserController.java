package de.baaasty.baaastybackend.discord.controller;

import de.baaasty.baaastybackend.discord.entity.DiscordUser;
import de.baaasty.baaastybackend.discord.service.DiscordUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping(path = "/discord/users")
@RestController
public class DiscordUserController {
    
    private final DiscordUserService discordUserService;
    
    private final HttpServletRequest httpServletRequest;

    public DiscordUserController(DiscordUserService discordUserService, HttpServletRequest httpServletRequest) {
        this.discordUserService = discordUserService;
        this.httpServletRequest = httpServletRequest;
    }

    @GetMapping
    public ResponseEntity<List<DiscordUser>> getAllDiscordUsers() {
        return ResponseEntity.ok(discordUserService.getAllDiscordUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscordUser> getDiscordUser(@PathVariable long id) {
        return ResponseEntity.ok(discordUserService.getDiscordUserById(id));
    }

    @PostMapping
    public ResponseEntity<DiscordUser> createDiscordUser(@RequestBody DiscordUser discordUser) {
        return ResponseEntity
                .created(URI.create(httpServletRequest.getRequestURI()))
                .body(discordUserService.createDiscordUser(discordUser));
    }

    @PutMapping
    public ResponseEntity<DiscordUser> updateDiscordUser(@RequestBody DiscordUser discordUser) {
        return ResponseEntity.ok(discordUserService.updateDiscordUser(discordUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiscordUser> deleteDiscordUser(@PathVariable long id) {
        discordUserService.deleteDiscordUser(id);
        return ResponseEntity.ok().build();
    }
    
}
