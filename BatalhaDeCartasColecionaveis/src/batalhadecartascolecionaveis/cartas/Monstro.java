/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhadecartascolecionaveis.cartas;

/**
 *
 * @author arthu
 */
public class Monstro extends Carta{
    private Equipamento equipamento;
    private boolean estado;//verdadeiro para defesa e falso para ataque
    private boolean atacou;

    public Monstro(String nome, String descricao, int atk, int def) {
        super(nome, descricao, atk, def);
        this.atacou = false;
    }
    
    public void alteraEstado(int estadoInt) {// 0 para defesa e 1 para ataque
        this.estado = (estadoInt == 0);
    }

    public int atacaDefende() {
        //TODO
        return 0;
    }
    public void equipaItem(Equipamento equipamento) {
        if(this.equipamento == null) {
            this.equipamento = equipamento;
        }
    }
}
