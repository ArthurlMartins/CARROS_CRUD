package br.edu.qi.projetofinal;

import java.io.Serializable;

public class Carro implements Serializable {

    private int id;
    private String nome;
    private String modelo;
    private String cor;
    private String teto;

    public String getTeto() {
        return teto;
    }

    public void setTeto(String teto) {
        this.teto = teto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "ID: "+id+"\n" +
                "Nome: "+nome+"\n" +
                "Modelo: "+modelo+"\n" +
                "Cor do Teto: "+teto;
    }
}
