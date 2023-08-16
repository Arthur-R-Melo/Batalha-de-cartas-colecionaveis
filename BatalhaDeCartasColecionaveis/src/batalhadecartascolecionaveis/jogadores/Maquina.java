package batalhadecartascolecionaveis.jogadores;

import batalhadecartascolecionaveis.Tabuleiro;
import batalhadecartascolecionaveis.cartas.Carta;
import batalhadecartascolecionaveis.cartas.Equipamento;
import batalhadecartascolecionaveis.cartas.Monstro;
import java.util.Random;

/**
 *
 * @author Vartin
 */
public class Maquina extends Jogador {

    public Maquina(int id) {
        super(id);
        super.pontosDeVida = 10000;
        super.maoDeCarta = new Carta[10];
    }

    @Override
    public int realizaJogada(Tabuleiro tabuleiro) {
        int dano = 0;
        
        Random r = new Random();
        int opc = r.nextInt(0, 3); //sortear o q a maquina ira fazer
        
        switch (opc) {
            case 0:
                posicionaCarta(tabuleiro);
                break;
            case 1:
                equipaMonstro(tabuleiro);
                break;
            case 2:
                dano = realizaAtaque(tabuleiro);
        }
        
        return dano;
    }

    @Override
    protected void equipaMonstro(Tabuleiro tabuleiro) {
        Random r = new Random();
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[super.id];

        int indiceMelhorAtributo = 0;
        int indiceMelhorEquipamento = 0;

        int op = r.nextInt(0, 2); //sorteará para descobrir se ira equipar algo na defesa ou no ataque

        switch (op) {
            case 0: //defesa
                for (int i = 0; i < mesaJogador.length; i++) {
                    if (mesaJogador[i] != null) {
                        Monstro temp = (Monstro) mesaJogador[i];
                        if (temp.getEquipamento() == null) {
                            if (temp.getDef() >= mesaJogador[indiceMelhorAtributo].getDef()) {
                                indiceMelhorAtributo = i;
                            }
                        }
                    }
                }
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null && super.maoDeCarta[i].getClass() == Equipamento.class) {
                        if (super.maoDeCarta[i].getDef() >= super.maoDeCarta[indiceMelhorEquipamento].getDef()) {
                            indiceMelhorEquipamento = i;
                        }
                    }
                }

                break;
            case 1: //ataque
                for (int i = 0; i < mesaJogador.length; i++) {
                    if (mesaJogador[i] != null) {
                        Monstro temp = (Monstro) mesaJogador[i];
                        if (temp.getEquipamento() == null) {
                            if (temp.getAtk() >= mesaJogador[indiceMelhorAtributo].getAtk()) {
                                indiceMelhorAtributo = i;
                            }
                        }
                    }

                }
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null && super.maoDeCarta[i].getClass() == Equipamento.class) {
                        if (super.maoDeCarta[i].getAtk() >= super.maoDeCarta[indiceMelhorEquipamento].getAtk()) {
                            indiceMelhorEquipamento = i;
                        }
                    }
                }
                break;
        }
        
        Monstro tempMonstro = (Monstro)super.maoDeCarta[indiceMelhorAtributo];

        tempMonstro.equipaItem((Equipamento) this.maoDeCarta[indiceMelhorEquipamento]);
        this.maoDeCarta[indiceMelhorEquipamento] = null;

    }

    @Override
    protected void descartaCarta(Tabuleiro tabuleiro) {
        Random r = new Random();
        int op = r.nextInt(0, 2); //será sorteado para ver se remove a menor defesa ou menor ataque

        switch (op) {
            case 0:
                //a menor defesa será removida
                int indiceMenorDefesa = 0;
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null) {
                        if (super.maoDeCarta[i].getDef() <= super.maoDeCarta[indiceMenorDefesa].getDef()) {
                            indiceMenorDefesa = i;
                        }
                    }
                }
                this.maoDeCarta[indiceMenorDefesa] = null;
                break;
            case 1:
                //o menor ataque será removido
                int indiceMenorAtaque = 0;
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null) {
                        if (super.maoDeCarta[i].getAtk() <= super.maoDeCarta[indiceMenorAtaque].getAtk()) {
                            indiceMenorAtaque = i;
                        }
                    }
                }
                this.maoDeCarta[indiceMenorAtaque] = null;
                break;
        }

    }

    @Override
    public void compraCarta(Tabuleiro tabuleiro) {
        //metodo semelhante ao da classe mãe, porem sem saida para o usuario
        for (int i = 0; i < this.maoDeCarta.length; i++) {
            if (super.maoDeCarta[i] == null) {
                Carta novaCarta = tabuleiro.getBaralho().firstElement();
                super.maoDeCarta[i] = novaCarta;
                tabuleiro.getBaralho().remove(0);
                return;
            }
        }
        this.descartaCarta(tabuleiro);
        this.compraCarta(tabuleiro);
    }

    //o método alteraEstadoCarta é considerado inútil para a máquina, visto que ela posicionará da melhor forma
    @Override
    protected void posicionaCarta(Tabuleiro tabuleiro) {
        Carta mesaJogador[] = tabuleiro.getCartasJogador()[super.id];
        Random r = new Random();
        int op = r.nextInt(0, 2); //será sorteado para ver se adiocina uma carta defendendo ou atacando

        switch (op) {
            case 0: //defendendo
                //o maior defesa será posicionado
                int indiceMaiorDefesa = 0;
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null && super.maoDeCarta[i].getClass() == Monstro.class) {
                        if (super.maoDeCarta[i].getDef() >= super.maoDeCarta[indiceMaiorDefesa].getDef()) {
                            indiceMaiorDefesa = i;
                        }
                    }
                }
                for (int i = 0; i < mesaJogador.length; i++) {
                    if (mesaJogador[i] == null) {
                        mesaJogador[i] = super.maoDeCarta[indiceMaiorDefesa];
                        Monstro temp = (Monstro) mesaJogador[i];
                        temp.alteraEstado(0);
                        super.maoDeCarta[indiceMaiorDefesa] = null;

                        break;
                    }
                }
                break;
            case 1: //atacando
                //o maior ataque será posicionado
                int indiceMaiorAtaque = 0;
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null && super.maoDeCarta[i].getClass() == Monstro.class) {
                        if (super.maoDeCarta[i].getAtk() >= super.maoDeCarta[indiceMaiorAtaque].getAtk()) {
                            indiceMaiorAtaque = i;
                        }
                    }
                }
                for (int i = 0; i < mesaJogador.length; i++) {
                    if (mesaJogador[i] == null) {
                        mesaJogador[i] = super.maoDeCarta[indiceMaiorAtaque];
                        Monstro temp = (Monstro) mesaJogador[i];
                        temp.alteraEstado(1);
                        super.maoDeCarta[indiceMaiorAtaque] = null;
                        break;
                    }
                }

                break;
        }
    }

    @Override
    protected int realizaAtaque(Tabuleiro tabuleiro) {
        int dano;
        Random r = new Random();
        Carta[] mesaJogador = tabuleiro.getCartasJogador()[super.id];
        int idInimigo = super.id == 0 ? 1 : 0;
        Carta[] mesaInimigo = tabuleiro.getCartasJogador()[idInimigo];

        int indiceMelhorAtaque = 0;

        for (int i = 0; i < mesaJogador.length; i++) {
            if (mesaJogador[i] != null) {
                Monstro temp = (Monstro) mesaJogador[i];
                if (!temp.isEstado()) {
                    Monstro tempMelhor = (Monstro) mesaJogador[indiceMelhorAtaque];
                    if (temp.atacaDefende() >= tempMelhor.atacaDefende()) {
                        indiceMelhorAtaque = i;
                    }
                }
            }
        }

        Monstro tempMonstroJogador = (Monstro) mesaJogador[indiceMelhorAtaque];

        Boolean inimigoTemCarta = false;
        int indiceMenorInimigo = 0;

        for (int i = 0; i < mesaInimigo.length; i++) {
            if (mesaInimigo[i] != null) {
                inimigoTemCarta = true;
                Monstro tempMonstroInimigo = (Monstro) mesaInimigo[i];
                Monstro tempPiorInimigo = (Monstro) mesaInimigo[indiceMenorInimigo];
                if (tempMonstroInimigo.atacaDefende() <= tempPiorInimigo.atacaDefende()) {
                    indiceMenorInimigo = i;
                }
            }
        }

        if (!inimigoTemCarta) {
            return tempMonstroJogador.atacaDefende();
        } else {
            Monstro tempMonstroInimigo = (Monstro) mesaInimigo[indiceMenorInimigo];
            dano = tempMonstroJogador.atacaDefende() - tempMonstroInimigo.atacaDefende();

            if (tempMonstroInimigo.isEstado()) {
                if (dano > 0) {
                    mesaInimigo[indiceMenorInimigo] = null;
                    System.out.println("Monstro do jogador destruido!!!");
                } else if (dano < 0) {
                    mesaJogador[indiceMelhorAtaque] = null;
                    System.out.println("O monstro da maquina foi destruido!");
                } else {
                    System.out.println("EMPATE! nada ocorreu");
                }
            } else {
                if (dano >= 0) {
                    return dano;
                } else {
                    super.pontosDeVida += dano;
                }
            }
        }
        return 0;
    }

}
