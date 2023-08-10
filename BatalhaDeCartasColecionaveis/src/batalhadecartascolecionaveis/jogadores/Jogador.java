package batalhadecartascolecionaveis.jogadores;

import batalhadecartascolecionaveis.Estilizacao;
import batalhadecartascolecionaveis.Tabuleiro;
import batalhadecartascolecionaveis.cartas.Carta;
import batalhadecartascolecionaveis.cartas.Monstro;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Valtin
 */
public class Jogador {

    protected int pontosDeVida;
    protected Carta[] maoDeCarta;
    protected int id;

    public Jogador(int id) {
        this.id = id;
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
        Estilizacao.imprimeLinha();
        Scanner s = new Scanner(System.in);
        System.out.println("""
                           Insira o numero desejado:
                           1- Realizar Ataque;
                           2- Posicionar Carta;
                           3- Equipar Monstro;
                           4- Comprar Carta;
                           5- Descartar Carta;
                           6- Mudar Estado da Carta;
                           7- Consultar Cartas da mao""");
        int opcao = s.nextInt();
        Estilizacao.imprimeLinha();

        switch (opcao) {
            case 1 ->
                this.realizaAtaque();
            case 2 ->
                this.posicionaCarta(tabuleiro);
            case 3 ->
                this.equipaMonstro();
            case 4 ->
                this.compraCarta(tabuleiro.getBaralho());
            case 5 ->
                this.descartaCarta(tabuleiro);
            case 6 ->
                this.alteraEstadoCarta();
            case 7 ->
                this.exibirCartasDaMao();
            default -> {
                System.out.println("Insira um número válido!");
                this.realizaJogada(tabuleiro);
            }

        }

    }

    protected void realizaAtaque() {

    }

    protected void posicionaCarta(Tabuleiro tabuleiro) {
        Scanner s = new Scanner(System.in);
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[id];
        //resolver esse 'id', pq tem que ter algum jeito de saber de quem estará modificando as cartas no tabuleiro

        System.out.println("Qual carta deseja posicionar? ");
        this.exibirCartasDaMao();

        int indiceConsulta;

        do {
            System.out.println("Insira o numero da carta você deseja posicionar: (-1 para retornar");
            indiceConsulta = s.nextInt();

            if (indiceConsulta == -1) {
                this.realizaJogada(tabuleiro);
            } else {

                if (indiceConsulta < this.maoDeCarta.length && this.maoDeCarta[indiceConsulta] != null) {
                    if (this.maoDeCarta[indiceConsulta].getClass() == Monstro.class) {
                        Monstro monstro = (Monstro) maoDeCarta[indiceConsulta];
                        for (int i = 0; i < mesaJogador.length; i++) {
                            if (mesaJogador[i] == null) {
                                //terminar isso ainda       
                                System.out.println("Insira:\n1: Ataque;\n2: Defesa;");
                                int opcao = s.nextInt();
                                switch (opcao) {
                                    case 1:
                                        monstro.alteraEstado(1);
                                        break;
                                    case 2:
                                        monstro.alteraEstado(0);
                                        break;
                                    default:
                                        System.out.println("Número invalido! Tente novamente!");
                                        this.posicionaCarta(tabuleiro);
                                }
                                mesaJogador[i] = this.maoDeCarta[indiceConsulta];
                                this.maoDeCarta[indiceConsulta] = null;
                                System.out.println("Carta posicionada com sucesso!");
                                return;
                            }
                        }
                        System.out.println("Voce atingiu o limite do numero de cartas na mesa!");
                    } else {
                        System.out.println("Somente monstros podem ser selecionados!");
                        this.realizaJogada(tabuleiro);
                    }
                } else {
                    System.out.println("Insira um numero valido!");
                }
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
                return;
            }
        }
        System.out.println("Limite de cartas atingido!");

    }

    protected void descartaCarta(Tabuleiro tabuleiro) {
        Scanner s = new Scanner(System.in);
        this.exibirCartasDaMao();

        int indiceDescarte;
        do {
            System.out.println("Insira o numero da carta você deseja descartar: (-1 para cancelar)");
            indiceDescarte = s.nextInt();
            if (indiceDescarte == -1) {
                this.realizaJogada(tabuleiro);
            } else {
                if (indiceDescarte < this.maoDeCarta.length && this.maoDeCarta[indiceDescarte] != null) {
                    this.maoDeCarta[indiceDescarte] = null;
                    System.out.println("Carta descartada!");
                } else {
                    System.out.println("Insira um numero valido!");
                }
            }
        } while (!(indiceDescarte < this.maoDeCarta.length) && (this.maoDeCarta[indiceDescarte] == null));
    }

    protected void alteraEstadoCarta() {

    }

    protected void exibirCartasDaMao() {
        System.out.println("CARTAS DA SUA MAO:");
        Estilizacao.imprimeLinha();
        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] != null) {
                System.out.println(i + " " + this.maoDeCarta[i]);
            }
        }
        Estilizacao.imprimeLinha();
    }

}
