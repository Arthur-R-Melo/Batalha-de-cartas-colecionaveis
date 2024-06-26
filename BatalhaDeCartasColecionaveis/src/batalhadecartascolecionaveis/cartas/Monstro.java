/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhadecartascolecionaveis.cartas;

/**
 *
 * @author arthu
 */
public class Monstro extends Carta {
    private Equipamento equipamento;
    private boolean estado;// verdadeiro para defesa e falso para ataque

    public Monstro(String nome, String descricao, int atk, int def) {
        super(nome, descricao, atk, def);
    }

    public void alteraEstado(int estadoInt) {// 0 para defesa e 1 para ataque
        this.estado = (estadoInt == 0);
    }

    public int atacaDefende() {
        int atributo = 0;
        if (estado) {
            atributo += super.def;
            if (equipamento != null) {
                atributo += equipamento.getDef();
            }
        } else {
            atributo += super.atk;
            if (equipamento != null) {
                atributo += equipamento.getAtk();
            }
        }
        return atributo;
    }

    public void equipaItem(Equipamento equipamento) {
        if (this.equipamento == null) {
            this.equipamento = equipamento;
        }
    }

    public boolean isEstado() {
        return estado;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    @Override
    public String toString() {
        String monstroTxt = "Nome: " + this.nome + "\nAtaque: " + atk + "\nDefesa: " + def + "\nEstado: ";
        
        monstroTxt += this.estado ? "Defesa" : "Ataque";
               
        monstroTxt += this.equipamento != null ? "\n Equipamento: " + this.equipamento : "";
        
        return monstroTxt;
        
    }

}
