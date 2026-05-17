package service;

import model.Experiencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Regras de negócio relacionadas às experiências:
// armazenamento, busca e ordenação por diferentes critérios.
// Os Comparators são definidos pontualmente com lambdas —
// sem criar uma nova classe para cada critério de ordenação.
public class ExperienciaService {

    private final List<Experiencia> experiencias = new ArrayList<>();

    public void adicionar(Experiencia experiencia) {
        experiencias.add(experiencia);
    }

    public List<Experiencia> getExperiencias() {
        return Collections.unmodifiableList(experiencias);
    }

    public boolean isEmpty() {
        return experiencias.isEmpty();
    }

    // ── Ordenações com lambdas (Comparator anônimo) ───────────────────────

    public List<Experiencia> listarPorData() {
        List<Experiencia> lista = new ArrayList<>(experiencias);

        lista.sort(new Comparator<Experiencia>() {
            @Override
            public int compare (Experiencia a,  Experiencia b) {
                return a.getDataHora().compareTo(b.getDataHora());
            }
        });
        return lista;
    }

    public List<Experiencia> listarPorPreco() {
        List<Experiencia> lista = new ArrayList<>(experiencias);
        lista.sort(Comparator.comparingDouble(Experiencia::getPrecoBase));
        return lista;
    }

    public List<Experiencia> listarPorTitulo() {
        List<Experiencia> lista = new ArrayList<>(experiencias);
        lista.sort(Comparator.comparing(e -> e.getTitulo().toLowerCase()));
        return lista;
    }

    // Busca por índice (1-based, para uso no menu)
    public Experiencia buscarPorIndice(int indice) {
        if (indice < 1 || indice > experiencias.size()) {
            return null;
        }
        return experiencias.get(indice - 1);
    }
}
