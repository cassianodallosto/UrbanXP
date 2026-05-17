package model;

public class Cliente {

    private final String nome;
    private final int idade;
    private final Desconto politicaDesconto;

    public Cliente(String nome, int idade, Desconto politicaDesconto) {
        this.nome             = nome;
        this.idade            = idade;
        this.politicaDesconto = politicaDesconto;
    }

    public String getNome()                { return nome; }
    public int getIdade()                  { return idade; }
    public Desconto getPoliticaDesconto()  { return politicaDesconto; }
}
