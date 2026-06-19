package com.socrates.game_leaderboard_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

@Getter
@Setter
@Entity
@Table(name = "jogadores")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "o nickname nao pode ser nulo")
    @Size(min = 3, max = 20, message = "o nickname deve ter entre 3 a 20 caracteres")
    @Column(nullable = false, unique = true)
    private String nickname;

    @NotNull(message = "a pontuacao nao pode ser nula")
    @PositiveOrZero(message = "a pontuacao nao pode ser negativa")
    @Column(nullable = false)
    private Integer pontuacao = 0;

    public Jogador() {
    }

    public Jogador(String nickname) {
        this.nickname = nickname;
        this.pontuacao = 0;
    }
}
