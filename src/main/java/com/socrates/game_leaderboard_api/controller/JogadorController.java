package com.socrates.game_leaderboard_api.controller;

import com.socrates.game_leaderboard_api.model.Jogador;
import com.socrates.game_leaderboard_api.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @PostMapping
    public Jogador criar(@RequestBody Jogador jogador){
        return jogadorRepository.save(jogador);
    }

    @GetMapping
    public List<Jogador> listarTodosJogadores(){
        return jogadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPeloId(@PathVariable Long id){
        //.map serve para ver como o optional vai retornar, se ele tiver algo dentro ele retorna o ok e o optional se o optional for nulo, ele vai cair no orelse e dar um notfound
        return jogadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
