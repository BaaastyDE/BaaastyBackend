package de.baaasty.baaastybackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "bb_user")
@Getter
@Setter
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
}
