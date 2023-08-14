package batalhadecartascolecionaveis;

import java.util.Scanner;

import batalhadecartascolecionaveis.cartas.Carta;
import batalhadecartascolecionaveis.jogadores.Jogador;

public class Partida {
    private Tabuleiro tabuleiro;
    private Jogador[] jogadores;
    
    public Partida(Jogador[] jogadores) {
        this.jogadores = jogadores;
        this.tabuleiro = new Tabuleiro();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int jogarNovamente;
        do {
            Jogador[] jogadores = new Jogador[2];
            System.out.println("Deseja jogar contra a máquina(1) ou contra outro jogador(2)?");
            int num = s.nextInt();
            jogadores[0] = new Jogador(0);
            while (num != 1 && num != 2) {
                System.out.println("Insira um número válido: ");
                num = s.nextInt();
            }
            if (num == 1) {
                //TO DO
                jogadores[1] = new Jogador(1);
            } else {
                jogadores[1] = new Jogador(1);
            }

            Partida partida = new Partida(jogadores);
            partida.realizaPartida();
            
            System.out.println("Deseja jogar novamente?(1 - sim/outro valor - não)");
            jogarNovamente = s.nextInt();
        } while (jogarNovamente == 1);
        s.close();
    }

    public void realizaPartida() {
        this.distribuiCarta();
        boolean acabou;
        do {
            Estilizacao.imprimeLinha();
            System.out.println("Rodada do jogador 1");
            Estilizacao.imprimeLinha();
            this.jogadores[0].compraCarta(tabuleiro);
            this.jogadores[0].realizaJogada(tabuleiro);

            Estilizacao.imprimeLinha();
            System.out.println("\n\n");
            
            Estilizacao.imprimeLinha();
            System.out.println("Rodada do jogador 2");
            Estilizacao.imprimeLinha();
            this.jogadores[1].compraCarta(tabuleiro);
            this.jogadores[1].realizaJogada(tabuleiro);

            //Verificando se a vitória ocorreu e informando quem venceu
            acabou = this.jogadores[0].getPontosDeVida()<=0 || this.jogadores[1].getPontosDeVida()<=0;
            if (acabou) {
                if (this.jogadores[0].getPontosDeVida() <= 0) {
                    System.out.println("Vitória do jogador 2!");
                } else {
                    System.out.println("Vitória do jogador 1!");
                }
            }
        } while (!acabou);
    }

    private void distribuiCarta() {
        Carta maoCarta[][] = new Carta[2][5];
        Carta carta;
        for (int i = 0; i < 5; i++) {
            carta = this.tabuleiro.getBaralho().get(0);
            this.tabuleiro.getBaralho().remove(0);
            maoCarta[0][i] = carta;
            carta = this.tabuleiro.getBaralho().get(0);
            this.tabuleiro.getBaralho().remove(0);
            maoCarta[1][i] = carta;
        }
        this.jogadores[0].setMaoDeCarta(maoCarta[0]);
        this.jogadores[1].setMaoDeCarta(maoCarta[1]);
    }
}
