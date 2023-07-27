package batalhadecartascolecionaveis.jogadores;

import batalhadecartascolecionaveis.Tabuleiro;
import batalhadecartascolecionaveis.cartas.Carta;
import java.util.Scanner;
import java.util.Vector;

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

    public void realizaJogada(Tabuleiro tabuleiro) {
        Scanner s = new Scanner(System.in);
        System.out.println("""
                           Insira o numero desejado:
                           1- Realizar Ataque;
                           2- Posicionar Carta;
                           3- Equipar Monstro;
                           4- Comprar Carta;
                           5- Descartar Carta;
                           6- Mudar Estado da Carta;
                           """);
        int opcao = s.nextInt();

        switch (opcao) {
            case 1 ->
                realizaAtaque();
            case 2 ->
                posicionaCarta(tabuleiro);
            case 3 ->
                equipaMonstro();
            case 4 ->
                compraCarta(tabuleiro.getBaralho());
            case 5 ->
                descartaCarta();
            case 6 ->
                alteraEstadoCarta();
            default ->
                System.out.println("Insira um número válido!");

        }

    }

    protected void realizaAtaque() {

    }

    protected void posicionaCarta(Tabuleiro tabuleiro) {
        Scanner s = new Scanner(System.in);
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[i];
        //resolver esse 'i', pq tem que ter algum jeito de saber de quem estará modificando as cartas no tabuleiro
        
        System.out.println("Qual carta deseja posicionar? ");
        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] != null) {
                System.out.println(i + " " + this.maoDeCarta[i]);
            }
        }
        
        int indiceConsulta;
        
        do {
            System.out.println("Insira o numero da carta você deseja posicionar:");
            indiceConsulta = s.nextInt();
            if (indiceConsulta < this.maoDeCarta.length && this.maoDeCarta[indiceConsulta] != null) {
                for (int i = 0; i < mesaJogador.length; i++) {
                    if(mesaJogador[i] == null) {
                        //terminar isso ainda                        
                        //mesaJogador[i] = this.maoDeCarta[indiceConsulta];
                    }
                }
                System.out.println("Voce atingiu o limite do numero de cartas na mesa!");
            }else{
                System.out.println("Insira um numero valido!");
            }
        } while (!(indiceConsulta < this.maoDeCarta.length && this.maoDeCarta[indiceConsulta] != null));
        
    }

    protected void equipaMonstro() {

    }

    protected void compraCarta(Vector<Carta> baralho) {
        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] == null) {
                Carta novaCarta = baralho.firstElement();
                System.out.println("Carta comprada: " + novaCarta);
                this.maoDeCarta[i] = novaCarta;
                baralho.remove(0);
                break;
            }
        }
        
    }

    protected void descartaCarta() {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] != null) {
                System.out.println(i + " " + this.maoDeCarta[i]);
            }
        }
        int indiceDescarte;
        do {
            System.out.println("Insira o numero da carta você deseja descartar:");
            indiceDescarte = s.nextInt();
            if (indiceDescarte < this.maoDeCarta.length && this.maoDeCarta[indiceDescarte] != null) {
                this.maoDeCarta[indiceDescarte] = null;
            }else{
                System.out.println("Insira um numero valido!");
            }
        } while (!(indiceDescarte < this.maoDeCarta.length && this.maoDeCarta[indiceDescarte] != null));
    }

    protected void alteraEstadoCarta() {

    }

}
