package com.socrates.game_leaderboard_api.repository;

import com.socrates.game_leaderboard_api.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    boolean existsByNickname(String nickname);
    List<Jogador> findAllByOrderByPontuacaoDesc();
}
