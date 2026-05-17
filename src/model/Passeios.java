package model;

import java.time.LocalDateTime;

public class Passeios extends Experiencia {

    private final String pontoEncontro;
    private final String guiaResponsavel;

    public Passeios(String titulo, String descricao, LocalDateTime dataHora,
                    int capacidadeMax, double precoBase,
                    String pontoEncontro, String guiaResponsavel) {
        super(titulo, descricao, dataHora, capacidadeMax, precoBase);
        this.pontoEncontro    = pontoEncontro;
        this.guiaResponsavel  = guiaResponsavel;
    }


    @Override
    public String gerarResumo() {
        return "🗺️  PASSEIO TURÍSTICO: " + getTitulo() +
               "\n   Descrição: " + getDescricao() +
               "\n   Guia: " + guiaResponsavel +
               "\n   Ponto de encontro: " + pontoEncontro +
               "\n   Data: " + getDataHoraFormatada() +
               "\n   Preço base: R$ " + String.format("%.2f", getPrecoBase()) +
               "\n   Vagas disponíveis: " + getVagasDisponiveis() + "/" + getCapacidadeMax();
    }

    public String getPontoEncontro()   { return pontoEncontro; }
    public String getGuiaResponsavel() { return guiaResponsavel; }
}
