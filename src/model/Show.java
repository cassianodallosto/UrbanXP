package model;

import java.time.LocalDateTime;

public class Show extends Experiencia {
    private String nomeArtista;

    public Show(String titulo, String descricao, LocalDateTime dataHora, int capacidadeMaxima, double precoBase, String nomeArtista) {
        //super pega da classe abstrata pai
        super(titulo, descricao, dataHora, capacidadeMaxima, precoBase);
        this.nomeArtista = nomeArtista;
    }

    public String getNomeArtistaPrincipal() {
        return nomeArtista;
    }


    public String gerarResumo() {
        return " SHOW: " + getTitulo() +
                "\nArtista: " + nomeArtista +
                "\nData: " + getDataHora() +
                "\nPreço base: R$" + getPrecoBase();
    }


}
