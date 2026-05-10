package model;

import java.time.LocalDateTime;

public class Passeios extends Experiencia{
    private String pontoEncontro;
    private String guiaResponsavel;

    public Passeios(String titulo, String descricao, LocalDateTime dataHora, int capacidadeMax, double precoBase,
                            String pontoEncontro, String guiaResponsavel) {
        super(titulo, descricao, dataHora, capacidadeMax, precoBase);
        this.pontoEncontro = pontoEncontro;
        this.guiaResponsavel = guiaResponsavel;
    }

    public String getPontoEncontro() {
        return pontoEncontro;
    }

    public String getGuiaResponsavel() {
        return guiaResponsavel;
    }


    public String gerarResumo() {
        return "PASSEIO TURÍSTICO: " + getTitulo() +
                "\nGuia: " + guiaResponsavel +
                "\nPonto de encontro: " + pontoEncontro +
                "\nData: " + getDataHora() +
                "\nPreço base: R$" + getPrecoBase();
    }
}
