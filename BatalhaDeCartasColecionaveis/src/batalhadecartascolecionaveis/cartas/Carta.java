/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batalhadecartascolecionaveis.cartas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author arthu
 */
public abstract class Carta {

    private static Vector<Carta> cartas;

    /*
     * A função importaCarta verifica se o Vector cartas já foi instanciado, ou seja, as cartas já foram importadas do armazenamento secundário.
     * Caso o Vector cartas já tenha sido instanciado, este é imediatamente retornado,
     * no outro caso, as cartas são importadas do arquivo e carregadas para a memória RAM.
     */

    public static Vector<Carta> importaCarta() throws IOException {
        if (cartas == null) {
            cartas = new Vector<>();
            File f = new File("arquivos\\cartas.csv");
            if (f.exists() && f.canRead()) {
                try {
                    cartas = new Vector<>();
                    FileReader marcaLeitura = new FileReader(f);
                    BufferedReader bufLeitura = new BufferedReader(marcaLeitura);

                    String linha;
                    do {
                        linha = bufLeitura.readLine();
                        if (linha != null) {
                            String dadosLinha[] = linha.split(";");

                            if (dadosLinha.length == 5) {
                                String nome = dadosLinha[0];
                                String descricao = dadosLinha[1];
                                int def = Integer.parseInt(dadosLinha[2]);
                                int atk = Integer.parseInt(dadosLinha[3]);
                                if (dadosLinha[4].equalsIgnoreCase("equipamento")) {
                                    Equipamento temp = new Equipamento(nome, descricao, atk, def);
                                    cartas.add(temp);
                                } else {
                                    Monstro temp = new Monstro(nome, descricao, atk, def);
                                    cartas.add(temp);
                                }
                            } else {
                                System.err.println("Problhema na linha " + linha);
                            }
                        }
                    } while (linha != null);
                    bufLeitura.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("Erro ao ler arquivo. Arquivo corrompido ou em uso!");
                }
            }

        }

        Collections.shuffle(cartas);
        return cartas;
    }

    protected String nome;
    protected String descricao;
    protected int atk, def;

    public Carta(String nome, String descricao, int atk, int def) {
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
