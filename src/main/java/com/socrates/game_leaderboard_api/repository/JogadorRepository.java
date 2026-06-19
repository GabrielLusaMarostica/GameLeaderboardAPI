package com.socrates.game_leaderboard_api.repository;

import com.socrates.game_leaderboard_api.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    boolean existsByNickname(String nickname);
}
