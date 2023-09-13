package de.baaasty.baaastybackend.user.entity;

import de.baaasty.baaastybackend.discord.entity.DiscordUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.cache.annotation.CachePut;

import java.util.UUID;

@Setter
@Getter
@Entity
public class User {

    @Id
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @OneToOne(orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private DiscordUser discordUser;

    @Column
    private Long webUser;

}
