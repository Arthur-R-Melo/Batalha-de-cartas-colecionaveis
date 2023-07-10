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
    private boolean estado;
    private boolean atacou;

    public Monstro(String nome, String descricao, int atk, int def) {
        super(nome, descricao, atk, def);
    }
    
    
}
