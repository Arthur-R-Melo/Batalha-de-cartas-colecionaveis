package batalhadecartascolecionaveis.jogadores;

import batalhadecartascolecionaveis.Tabuleiro;
import batalhadecartascolecionaveis.cartas.Carta;
import java.util.Scanner;

/**
 *
 * @author Valtin
 */
public class Jogador {
    protected int pontosDeVida;
    protected Carta[] maoDeCarta;

    public Jogador() {
        this.pontosDeVida = 10000;
        this.maoDeCarta = new Carta[10];
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public Carta[] getMaoDeCarta() {
        return maoDeCarta;
    }
       
        
    public void realizaJogada(Tabuleiro tabuleiro){
        Scanner s = new Scanner(System.in);
        System.out.println("""
                           Insira o numero desejado:
                           1- Realizar Ataque;
                           2- Posicionar Carta;
                           3- Equipar Monstro;
                           4- Comprar Carta;
                           5- Discartar Carta;
                           6- Mudar Estado da Carta;
                           """);
        int opcao = s.nextInt();
                           
    }
}
