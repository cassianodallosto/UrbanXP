package model;

import java.time.LocalDateTime;

// Show usa o padrão Builder para separar campos obrigatórios (artista, etc.)
// dos opcionais (patrocinador, restrição de idade, brinde),
// sem poluir a classe com múltiplos construtores.
public class Show extends Experiencia {

    private final String nomeArtista;

    // Campos opcionais
    private final String patrocinador;
    private final int restricaoIdade; // 0 = sem restrição
    private final String brinde;

    // Construtor privado — só o Builder pode criar um Show
    private Show(Builder builder) {
        super(builder.titulo, builder.descricao, builder.dataHora,
              builder.capacidadeMax, builder.precoBase);
        this.nomeArtista   = builder.nomeArtista;
        this.patrocinador  = builder.patrocinador;
        this.restricaoIdade = builder.restricaoIdade;
        this.brinde        = builder.brinde;
    }

    @Override
    public String gerarResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("🎤 SHOW: ").append(getTitulo())
          .append("\n   Descrição: ").append(getDescricao())
          .append("\n   Artista: ").append(nomeArtista)
          .append("\n   Data: ").append(getDataHoraFormatada())
          .append("\n   Preço base: R$ ").append(String.format("%.2f", getPrecoBase()))
          .append("\n   Vagas disponíveis: ").append(getVagasDisponiveis())
          .append("/").append(getCapacidadeMax());
        if (patrocinador != null)
            sb.append("\n   Patrocinador: ").append(patrocinador);
        if (restricaoIdade > 0)
            sb.append("\n   Idade mínima: ").append(restricaoIdade).append(" anos");
        else
            sb.append("\n   Restrição de idade: Sem restrição");
        if (brinde != null)
            sb.append("\n   Brinde: ").append(brinde);
        return sb.toString();
    }

    // Getters
    public String getNomeArtista()   { return nomeArtista; }
    public String getPatrocinador()  { return patrocinador; }
    public int getRestricaoIdade()   { return restricaoIdade; }
    public String getBrinde()        { return brinde; }

    // ── Builder ──────────────────────────────────────────────────────────────
    public static class Builder {

        // Obrigatórios
        private final String titulo;
        private final String descricao;
        private final LocalDateTime dataHora;
        private final int capacidadeMax;
        private final double precoBase;
        private final String nomeArtista;

        // Opcionais (valores padrão)
        private String patrocinador  = null;
        private int    restricaoIdade = 0;
        private String brinde        = null;

        public Builder(String titulo, String descricao, LocalDateTime dataHora,
                       int capacidadeMax, double precoBase, String nomeArtista) {
            this.titulo        = titulo;
            this.descricao     = descricao;
            this.dataHora      = dataHora;
            this.capacidadeMax = capacidadeMax;
            this.precoBase     = precoBase;
            this.nomeArtista   = nomeArtista;
        }

        public Builder patrocinador(String patrocinador) {
            this.patrocinador = patrocinador;
            return this;
        }

        public Builder restricaoIdade(int restricaoIdade) {
            this.restricaoIdade = restricaoIdade;
            return this;
        }

        public Builder brinde(String brinde) {
            this.brinde = brinde;
            return this;
        }

        public Show build() {
            return new Show(this);
        }
    }
}
