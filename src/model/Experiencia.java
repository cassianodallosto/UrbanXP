package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Classe abstrata com dados comuns a todas as experiências.
// gerarResumo() é abstrato — cada subclasse define seu próprio comportamento,
// mas o chamador sempre usa o mesmo método, independente do tipo concreto (polimorfismo).
public abstract class Experiencia {

    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private int capacidadeMax;
    private double precoBase;
    private int vagasOcupadas;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Experiencia(String titulo, String descricao, LocalDateTime dataHora,
                       int capacidadeMax, double precoBase) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.capacidadeMax = capacidadeMax;
        this.precoBase = precoBase;
        this.vagasOcupadas = 0;
    }

    // Método abstrato: toda experiência DEVE implementar seu próprio resumo
    public abstract String gerarResumo();

    // Controle de vagas
    public boolean temVagasDisponiveis() {
        return vagasOcupadas < capacidadeMax;
    }

    public void ocuparVaga() {
        if (temVagasDisponiveis()) {
            vagasOcupadas++;
        }
    }

    public void liberarVaga() {
        if (vagasOcupadas > 0) {
            vagasOcupadas--;
        }
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public int getCapacidadeMax() { return capacidadeMax; }
    public double getPrecoBase() { return precoBase; }
    public int getVagasOcupadas() { return vagasOcupadas; }
    public int getVagasDisponiveis() { return capacidadeMax - vagasOcupadas; }

    public String getDataHoraFormatada() {
        return dataHora.format(FORMATTER);
    }
}
