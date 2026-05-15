package model;
import java.time.LocalDateTime;
public abstract class Experiencia {
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private int capacidadeMax;
    private double precoBase;

    public Experiencia(String titulo, String descricao, LocalDateTime dataHora, int capacidadeMax, double precoBase){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.capacidadeMax = capacidadeMax;
        this.precoBase = precoBase;
        }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public abstract String gerarResumo();
}
