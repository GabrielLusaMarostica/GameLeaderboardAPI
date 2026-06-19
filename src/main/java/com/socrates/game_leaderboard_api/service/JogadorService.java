package com.socrates.game_leaderboard_api.service;

import com.socrates.game_leaderboard_api.exceptions.NicknameDuplicadoException;
import com.socrates.game_leaderboard_api.model.Jogador;
import com.socrates.game_leaderboard_api.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogadorService {
    private final JogadorRepository jogadorRepository;

    public Jogador criarJogador(Jogador jogador){
        if(jogadorRepository.existsByNickname(jogador.getNickname())){
            throw new NicknameDuplicadoException(jogador.getNickname());
        }
        return jogadorRepository.save(jogador);
    }

    public List<Jogador> listarJogadores(){
        return jogadorRepository.findAll();
    }

    public Optional<Jogador> buscarPorId(Long id){
        return jogadorRepository.findById(id);
    }

    public Optional<Jogador> atualizar(Long id, Jogador jogadorAtualizado){
        return jogadorRepository.findById(id)
                //.map serve para ver como o optional vai retornar, se ele tiver algo dentro ele retorna o ok e o optional se o optional for nulo, ele vai cair no orelse e dar um notfound
                .map(jogador -> {
                    jogador.setNickname(jogadorAtualizado.getNickname());
                    jogador.setPontuacao(jogadorAtualizado.getPontuacao());
                    return jogadorRepository.save(jogador);
                });
    }

    public boolean deletarJogador(Long id){
        if(!jogadorRepository.existsById(id)){
            return false;
        }
        jogadorRepository.deleteById(id);
        return true;
    }

    public List<Jogador> listarRanking(){
        return jogadorRepository.findAllByOrderByPontuacaoDesc();
    }

    public Optional<Jogador> incrementarPontuacao(Long id, Integer pontos){
        return jogadorRepository.findById(id)
                .map(jogador -> {
                    jogador.setPontuacao(jogador.getPontuacao() + pontos);
                    return jogadorRepository.save(jogador);
                });
    }
}
