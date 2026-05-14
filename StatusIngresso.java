package model;

enum StatusIngresso {
    RESERVADO("Seu ingresso foi reservado! \n\nEfetue o pagamento!"),
    PAGO("Seu pagamento foi efetuado com sucesso!"),
    CANCELADO("Você cancelou sua reserva!");

    private String descricao;

    private StatusIngresso(String descricao){
        this.descricao = descricao;
    }
}
