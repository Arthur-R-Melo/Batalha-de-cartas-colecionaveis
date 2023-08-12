package batalhadecartascolecionaveis;

import java.util.Scanner;

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
                //TODO
                jogadores[1] = new Jogador(1);
            } else {
                jogadores[1] = new Jogador(1);
            }
            System.out.println("Deseja jogar novamente?(1 - sim/outro valor - não)");
            jogarNovamente = s.nextInt();
        } while (jogarNovamente == 1);
    }
}
