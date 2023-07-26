package batalhadecartascolecionaveis;

import batalhadecartascolecionaveis.cartas.Carta;
import java.util.Vector;

/**
 *
 * @author Vartin
 */
public class Tabuleiro {
    
    private Vector<Carta>[] cartasJogador;//um vetor das cartas de cada jogador no tabuleiro

    public Tabuleiro() {
        cartasJogador = new Vector[2];
        for (int i = 0; i < cartasJogador.length; i++) {
            cartasJogador[i] = new Vector<>();
        }
    }
    
    
    
}
