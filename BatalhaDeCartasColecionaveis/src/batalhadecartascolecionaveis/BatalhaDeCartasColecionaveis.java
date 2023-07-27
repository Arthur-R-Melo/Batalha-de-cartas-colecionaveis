package batalhadecartascolecionaveis;

import batalhadecartascolecionaveis.jogadores.Jogador;

/**
 *
 * @author arthu
 */
public class BatalhaDeCartasColecionaveis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //só pra testar :)
        Tabuleiro tabuleiro = new Tabuleiro();
        Jogador jog = new Jogador();
        while (true) {
            jog.realizaJogada(tabuleiro);
        }
    }

}
