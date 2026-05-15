package model;

public class Cliente {
    private String nome;
    private int idade;
    private Desconto politicaDesconto;

    public Cliente(String nome, int idade, Desconto politicaDesconto) {
        this.nome = nome;
        this.idade = idade;
        this.politicaDesconto = politicaDesconto;
    }


    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Desconto getPoliticaDesconto() {
        return politicaDesconto;
    }
}

