/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhadecartascolecionaveis.cartas;

/**
 *
 * @author arthu
 */
public class Carta {
    protected String nome;
    protected String descricao;
    protected int atk, def;

    public Carta(String nome, String descricao, int atk, int def){
        this.nome = nome;
        this.descricao = descricao;
        this.atk = atk;
        this.def = def;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    
}
