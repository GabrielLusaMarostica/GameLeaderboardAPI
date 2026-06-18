package com.socrates.game_leaderboard_api.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jogadores")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Integer pontuacao = 0;

    public Jogador() {
    }

    public Jogador(String nickname) {
        this.nickname = nickname;
        this.pontuacao = 0;
    }
}
