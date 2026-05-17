package model;

// Enum garante que apenas estados válidos existam — verificado em tempo de compilação
public enum StatusIngresso {
    RESERVADO("Ingresso reservado! Efetue o pagamento."),
    PAGO("Pagamento efetuado com sucesso!"),
    CANCELADO("Reserva cancelada.");

    private final String descricao;

    StatusIngresso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
