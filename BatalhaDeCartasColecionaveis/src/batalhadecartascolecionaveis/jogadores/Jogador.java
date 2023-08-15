package batalhadecartascolecionaveis.jogadores;

import batalhadecartascolecionaveis.Estilizacao;
import batalhadecartascolecionaveis.Tabuleiro;
import batalhadecartascolecionaveis.cartas.Carta;
import batalhadecartascolecionaveis.cartas.Equipamento;
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

    public void setMaoDeCarta(Carta[] maoDeCartas) {
        for (int i = 0; i < maoDeCartas.length; i++) {
            this.maoDeCarta[i] = maoDeCartas[i];
        }
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public Carta[] getMaoDeCarta() {
        return maoDeCarta;
    }

    public int realizaJogada(Tabuleiro tabuleiro) {
        //retornará para a partida o dano para o adversário
        int dano = 0;

        Estilizacao.imprimeLinha();
        Scanner s = new Scanner(System.in);
        System.out.println("""
                           Insira o numero desejado:
                           1- Realizar Ataque;
                           2- Posicionar Carta;
                           3- Equipar Monstro;
                           4- Descartar Carta;
                           5- Mudar Estado da Carta;
                           6- Consultar Cartas da mao""");
        int opcao = s.nextInt();
        Estilizacao.imprimeLinha();

        switch (opcao) {
            case 1 ->
                dano = this.realizaAtaque(tabuleiro);
            case 2 ->
                this.posicionaCarta(tabuleiro);
            case 3 ->
                this.equipaMonstro(tabuleiro);
            case 4 ->
                this.descartaCarta(tabuleiro);
            case 5 ->
                this.alteraEstadoCarta(tabuleiro);
            case 6 -> {
                this.exibirCartasDaMao();
                this.realizaJogada(tabuleiro);
            }
            default -> {
                System.out.println("Insira um número válido!");
                this.realizaJogada(tabuleiro);
            }

        }

        return dano;

    }

    protected int realizaAtaque(Tabuleiro tabuleiro) {
        int dano;
        Scanner s = new Scanner(System.in);
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[this.id];

        System.out.println("Cartas possíveis para atacar: ");
        for (int i = 0; i < mesaJogador.length; i++) {
            if (mesaJogador[i] != null) {
                System.out.println((Monstro) mesaJogador[i]);

                System.out.println("[" + i + "] " + (Monstro) mesaJogador[i]);
            }
        }

        int indiceMonstro;

        do {
            System.out.println("Insira o indice do seu monstro: (-1 para cancelar)");
            indiceMonstro = s.nextInt();

            if (indiceMonstro == -1) {
                this.realizaJogada(tabuleiro);
            } else if ((indiceMonstro >= 0 && indiceMonstro < mesaJogador.length) && mesaJogador[indiceMonstro] != null) {
                Monstro tempMonstroJogador = (Monstro) mesaJogador[indiceMonstro];
                if (!tempMonstroJogador.isEstado() && tempMonstroJogador.isAtacou()) {
                    //imprima as cartas rivais
                    int idInimigo = this.id == 0 ? 1 : 0; // descobrir o id do jogador inimigo
                    Carta[] mesaInimigo = tabuleiro.getCartasJogador()[idInimigo];
                    Boolean inimigoTemCarta = false;

                    for (int i = 0; i < mesaInimigo.length; i++) {
                        if (mesaInimigo[i] != null) {
                            System.out.println("[" + i + "] " + mesaInimigo[i]);
                            inimigoTemCarta = true;
                        }
                    }

                    if (!inimigoTemCarta) {
                        System.out.println("O inimigo não tem cartas, dano aplicado direto a ele");
                        return tempMonstroJogador.getAtk();
                    }
                    int indiceMonstroInimigo;
                    do {
                        System.out.println("Insira o indice do monstro que deseja atacar: ");
                        indiceMonstroInimigo = s.nextInt();

                        if (indiceMonstroInimigo >= 0 && indiceMonstroInimigo < mesaInimigo.length
                                && mesaInimigo[indiceMonstroInimigo] != null) {
                            Monstro tempMonstroInimigo = (Monstro) mesaInimigo[indiceMonstroInimigo];

                            if (tempMonstroInimigo.isEstado()) {
                                dano = tempMonstroJogador.getAtk() - tempMonstroInimigo.atacaDefende();
                                if (dano > 0) {
                                    mesaInimigo[indiceMonstroInimigo] = null;
                                    System.out.println("Monstro inimigo destruido!!!");
                                } else if (dano < 0) {
                                    mesaJogador[indiceMonstro] = null;
                                    System.out.println("Seu monstro perdeu e foi destruido!");
                                } else {
                                    System.out.println("EMPATE! nada ocorreu");
                                }
                                return 0;
                            } else {
                                dano = tempMonstroJogador.getAtk() - tempMonstroInimigo.atacaDefende();
                                if (dano >= 0) {
                                    return dano;
                                } else {
                                    this.pontosDeVida += dano;
                                }
                            }

                        } else {
                            System.out.println("Insira algo valido!");
                        }

                    } while (indiceMonstroInimigo < 0 || indiceMonstroInimigo >= mesaInimigo.length
                            || mesaInimigo[indiceMonstroInimigo] == null);
                } else {
                    System.out.println("Insira o indice de um monstro que esteja em posicao de ataque ou que ainda n atacou!");
                }

            } else {
                System.out.println("Insira um número válido!");
            }
        } while (indiceMonstro != -1);

        return 0;
    }

    protected void posicionaCarta(Tabuleiro tabuleiro) {
        Scanner s = new Scanner(System.in);
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[this.id];

        System.out.println("Qual carta deseja posicionar? ");
        this.exibirCartasDaMao();

        int indiceConsulta;

        do {
            System.out.println("Insira o numero da carta você deseja posicionar: (-1 para retornar");
            indiceConsulta = s.nextInt();

            if (indiceConsulta == -1) {
                this.realizaJogada(tabuleiro);
            } else {

                //contornando todos os erros possiveis sobre a entrada do usuario
                if (indiceConsulta < this.maoDeCarta.length && this.maoDeCarta[indiceConsulta] != null) {
                    if (this.maoDeCarta[indiceConsulta].getClass() == Monstro.class) {
                        Monstro monstro = (Monstro) maoDeCarta[indiceConsulta];
                        for (int i = 0; i < mesaJogador.length; i++) {
                            if (mesaJogador[i] == null) {
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
        } while ((indiceConsulta < this.maoDeCarta.length) && (indiceConsulta >= 0) && (this.maoDeCarta[indiceConsulta] != null));

    }

    protected void alteraEstadoCarta(Tabuleiro tabuleiro) {
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[this.id];
        Scanner s = new Scanner(System.in);

        System.out.println("Monstros disponíveis para alterar o estado:");
        for (int i = 0; i < mesaJogador.length; i++) {
            if (mesaJogador[i] != null) {
                if (mesaJogador[i].getClass() == Monstro.class) {
                    System.out.println("[" + i + "] " + (Monstro) mesaJogador[i]);
                }
            }
        }

        int indiceMonstro;
        do {
            System.out.println("Insira o indice do monstro: (-1 para cancelar)");
            indiceMonstro = s.nextInt();

            if (indiceMonstro == -1) {
                this.realizaJogada(tabuleiro);
            } else if ((indiceMonstro >= 0 && indiceMonstro < mesaJogador.length) && mesaJogador[indiceMonstro] != null
                    && mesaJogador[indiceMonstro].getClass() == Monstro.class) {
                //if para contornar todas os erros do usuario

                Monstro tempMonstro = (Monstro) mesaJogador[indiceMonstro];
                System.out.println("Insira:\n1: Ataque;\n2: Defesa;");
                int opcao = s.nextInt();
                switch (opcao) {
                    case 1:
                        tempMonstro.alteraEstado(1);
                        indiceMonstro = -1;
                        break;
                    case 2:
                        tempMonstro.alteraEstado(0);
                        indiceMonstro = -1;
                        break;
                    default:
                        System.out.println("Número invalido! Tente novamente!");
                }
            } else {
                System.out.println("Insira um numero valido!");
            }
        } while (indiceMonstro != -1);
    }

    public void compraCarta(Tabuleiro tabuleiro) {

        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] == null) {
                Carta novaCarta = tabuleiro.getBaralho().firstElement();
                System.out.println("Carta comprada: " + novaCarta);
                this.maoDeCarta[i] = novaCarta;
                tabuleiro.getBaralho().remove(0);
                return;
            }
        }
        System.out.println("Limite de cartas atingido! Voce deve remover alguma!");
        this.descartaCarta(tabuleiro);
        this.compraCarta(tabuleiro);
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

    protected void equipaMonstro(Tabuleiro tabuleiro) {
        Scanner s = new Scanner(System.in);
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[this.id];

        //exibindo somente as cartas do jogador na mesa que podem ser equipadas
        System.out.println("Cartas que podem ser equipadas: ");
        for (int i = 0; i < mesaJogador.length; i++) {
            if (mesaJogador[i] != null) {
                if (mesaJogador[i].getClass() == Monstro.class) {
                    Monstro tempMonstro = (Monstro) mesaJogador[i];

                    if (tempMonstro.getEquipamento()
                            == null) {
                        System.out.println("[" + i + "] " + mesaJogador[i]);
                    }
                }
            }
        }

        System.out.println("Equipamentos disponíveis para equipar: ");

        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] != null && this.maoDeCarta[i].getClass() == Equipamento.class) {
                System.out.println(
                        this.maoDeCarta[i]);
            }
        }

        int indiceMonstro;
        do {
            System.out.println("Insira o indice do monstro que deseja equipar: (-1 para cancelar)");
            indiceMonstro = s.nextInt();
            if (indiceMonstro == -1) {
                this.realizaJogada(tabuleiro);
            } else {
                if ((indiceMonstro >= 0 && indiceMonstro < mesaJogador.length) && (mesaJogador[indiceMonstro] != null)) {
                    Monstro tempMonstro = (Monstro) mesaJogador[indiceMonstro];

                    int indiceEquipamento;
                    do {
                        System.out.println("Insira agora o índice do equipamento: (-1 para cancelar)");
                        indiceEquipamento = s.nextInt();

                        if (indiceEquipamento == -1) {
                            this.realizaJogada(tabuleiro);
                            indiceMonstro = -1; //foi atribuido a -1 para encerrar ambos os laços

                        } else {
                            if (this.maoDeCarta[indiceEquipamento] != null && this.maoDeCarta[indiceEquipamento].getClass() == Equipamento.class) {
                                tempMonstro.equipaItem(
                                        (Equipamento) this.maoDeCarta[indiceEquipamento]);
                                indiceMonstro = -1;
                                indiceEquipamento = -1;
                            } else {
                                System.out.println("Insira um indice valido!");
                            }
                        }
                    } while (indiceEquipamento != -1);
                } else {
                    System.out.println("Insira um numero valido!");
                }

            }
        } while (!(indiceMonstro == -1));
    }

    protected void exibirCartasDaMao() {
        System.out.println("CARTAS DA SUA MAO:");
        Estilizacao.imprimeLinha();
        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (this.maoDeCarta[i] != null) {
                System.out.println("[" + i + "] " + this.maoDeCarta[i]);
            }
        }
        Estilizacao.imprimeLinha();
    }

}
