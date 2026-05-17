package service;

import model.Cliente;
import model.Experiencia;
import model.Ingresso;
import model.StatusIngresso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Regras de negócio para emissão e gestão de ingressos.
// Verifica disponibilidade de vagas antes de emitir.
public class IngressoService {

    private final List<Ingresso> ingressos = new ArrayList<>();

    // Emite ingresso verificando capacidade
    public Ingresso emitir(Cliente cliente, Experiencia experiencia) {
        if (!experiencia.temVagasDisponiveis()) {
            System.out.println("⚠ Sem vagas disponíveis para: " + experiencia.getTitulo());
            return null;
        }
        Ingresso ingresso = new Ingresso(cliente, experiencia);
        experiencia.ocuparVaga();
        ingressos.add(ingresso);
        return ingresso;
    }

    // Efetua pagamento do ingresso
    public void pagar(Ingresso ingresso) {
        ingresso.pagar();
    }

    // Cancela ingresso e libera vaga
    public void cancelar(Ingresso ingresso) {
        if (ingresso.getStatus() != StatusIngresso.CANCELADO) {
            ingresso.cancelar();
            ingresso.getExperiencia().liberarVaga();
        } else {
            System.out.println("⚠ Ingresso já está cancelado.");
        }
    }

    public List<Ingresso> getIngressos() {
        return Collections.unmodifiableList(ingressos);
    }

    public boolean isEmpty() {
        return ingressos.isEmpty();
    }

    // Busca por índice (1-based, para uso no menu)
    public Ingresso buscarPorIndice(int indice) {
        if (indice < 1 || indice > ingressos.size()) {
            return null;
        }
        return ingressos.get(indice - 1);
    }
}
