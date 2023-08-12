package batalhadecartascolecionaveis;

import batalhadecartascolecionaveis.jogadores.Jogador;

public class Partida {
    private Tabuleiro tabuleiro;
    private Jogador[] jogadores;
    
    public Partida(Jogador[] jogadores) {
        this.jogadores = jogadores;
        this.tabuleiro = new Tabuleiro();
    }
}
