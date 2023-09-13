package de.baaasty.baaastybackend.discord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.baaasty.baaastybackend.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class DiscordUser {

    @Id
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "discordUser")
    @JsonIgnore
    private User user;

}
