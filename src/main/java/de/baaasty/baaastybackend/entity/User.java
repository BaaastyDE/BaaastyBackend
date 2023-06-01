package de.baaasty.baaastybackend.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "bb_user")
public class User {

    @Id
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long discordId;

    public User() {

    }

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.discordId = -1L;
    }

    public User(UUID uuid, String name, Long discordId) {
        this.uuid = uuid;
        this.name = name;
        this.discordId = discordId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDiscordId() {
        return discordId;
    }

    public void setDiscordId(Long discordID) {
        this.discordId = discordID;
    }
}
