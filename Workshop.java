package model;

import java.time.LocalDateTime;
import java.util.*;

public class Workshop extends Experiencia{
    ArrayList<String> materiais = new ArrayList<String>();

    public Workshop(String titulo, String descricao, LocalDateTime dataHora, int capacidadeMax, double precoBase, ArrayList<String> materiais) {
        super(titulo, descricao, dataHora, capacidadeMax, precoBase);
        this.materiais = materiais;
    }

    public ArrayList<String> getmateriais() {
        return materiais;
    }


    public String gerarResumo() {
        return "WORKSHOP: " + getTitulo() +
                "\nO que levar: " + String.join(", ", materiais) +
                "\nData: " + getDataHora() +
                "\nPreço base: R$" + getPrecoBase();
    }
}
