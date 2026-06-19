package com.socrates.game_leaderboard_api.exceptions;

public class NicknameDuplicadoException extends RuntimeException{
    public NicknameDuplicadoException(String nickname){
        super("ja existe um jogador com esse nome " + nickname);
    }
}
