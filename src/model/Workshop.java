package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Workshop extends Experiencia {

    private final List<String> materiais; // private — encapsulamento correto

    public Workshop(String titulo, String descricao, LocalDateTime dataHora,
                    int capacidadeMax, double precoBase, List<String> materiais) {
        super(titulo, descricao, dataHora, capacidadeMax, precoBase);
        this.materiais = new ArrayList<>(materiais); // cópia defensiva
    }

    @Override
    public String gerarResumo() {
        return "👨‍🍳 WORKSHOP: " + getTitulo() +
               "\n   Descrição: " + getDescricao() +
               "\n   O que levar: " + String.join(", ", materiais) +
               "\n   Data: " + getDataHoraFormatada() +
               "\n   Preço base: R$ " + String.format("%.2f", getPrecoBase()) +
               "\n   Vagas disponíveis: " + getVagasDisponiveis() + "/" + getCapacidadeMax();
    }

    // Retorna cópia para não expor a lista interna
    public List<String> getMateriais() {
        return new ArrayList<>(materiais);
    }
}
