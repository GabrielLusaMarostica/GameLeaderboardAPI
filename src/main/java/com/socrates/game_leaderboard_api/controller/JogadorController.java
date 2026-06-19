package com.socrates.game_leaderboard_api.controller;

import com.socrates.game_leaderboard_api.model.Jogador;
import com.socrates.game_leaderboard_api.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {
    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    public Jogador criar(@RequestBody Jogador jogador){
        return jogadorService.criarJogador(jogador);
    }

    @GetMapping
    public List<Jogador> listarTodosJogadores(){
        return jogadorService.listarJogadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPeloId(@PathVariable Long id){
        return jogadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizarJogador(@PathVariable Long id, @RequestBody Jogador jogadorAtualizado){
        return jogadorService.atualizar(id, jogadorAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogador(@PathVariable Long id){
        if(!jogadorService.deletarJogador(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
