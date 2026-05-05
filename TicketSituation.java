package model;

enum TicketSituation {
    RESERVADO("Seu ingresso foi reservado! \n\nEfetue o pagamento!"),
    PAGO("Seu pagamento foi efetuado com sucesso!"),
    CANCELADO("Você cancelou sua reserva!");

    private String descricao;

    private TicketSituation(String descricao){
        this.descricao = descricao;
    }
}
