package batalhadecartascolecionaveis;

import batalhadecartascolecionaveis.cartas.Carta;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Vartin
 */
public class Tabuleiro {

    private Carta[][] cartasJogador;//um vetor das cartas de cada jogador no tabuleiro
    private Vector<Carta> baralho;

    public Tabuleiro() {
        try {
            baralho = Carta.importaCarta();
        } catch (IOException e) {
            System.err.println("Erro com a importação do arquivo das cartas");
        }
        this.cartasJogador = new Carta[2][5];

    }

    public Carta[][] getCartasJogador() {
        return cartasJogador;
    }

    public Vector<Carta> getBaralho() {
        return baralho;
    }

    public void setCartasJogador(Carta[][] cartasJogador) {
        this.cartasJogador = cartasJogador;
    }

    @Override
    public String toString() {
        String texto;
        texto = "Cartas do jogador 1: ";
        for (Carta carta : cartasJogador[0]) {
            if (carta != null) {
                texto += carta.toString();
            }
        }
        texto += "\n----------------------------------------------------------------------";
        texto += "\nCartas do jogador 2: ";

        for (Carta carta : cartasJogador[1]) {
            if (carta != null) {
                texto += carta.toString();
            }
        }
        return texto;
    }

}
