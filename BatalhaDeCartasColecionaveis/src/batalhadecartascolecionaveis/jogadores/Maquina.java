package batalhadecartascolecionaveis.jogadores;

import batalhadecartascolecionaveis.Tabuleiro;
import batalhadecartascolecionaveis.cartas.Carta;
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
        return super.realizaJogada(tabuleiro);
    }

    @Override
    protected void equipaMonstro(Tabuleiro tabuleiro) {
        super.equipaMonstro(tabuleiro);
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
                break;
            case 1: //atacando
                //o maior ataque será posicionado
                int indiceMaiorAtaque = 0;
                for (int i = 0; i < super.maoDeCarta.length; i++) {
                    if (super.maoDeCarta[i] != null) {
                        if (super.maoDeCarta[i].getAtk() >= super.maoDeCarta[indiceMaiorAtaque].getAtk()) {
                            indiceMaiorAtaque = i;
                        }
                    }
                }
                for (int i = 0; i < mesaJogador.length; i++) {
                    if (mesaJogador[i] == null) {
                        mesaJogador[i] = super.maoDeCarta[indiceMaiorAtaque];
                        super.maoDeCarta[indiceMaiorAtaque] = null;
                        break;
                    }
                }

                break;
        }
    }

    @Override
    protected int realizaAtaque(Tabuleiro tabuleiro) {
        return super.realizaAtaque(tabuleiro);
    }

}
