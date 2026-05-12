package model;

public class Ingresso {
    private Cliente cliente;
    private Experiencia experiencia;
    private double precofinal;
    private TicketSituation situacao;

    public Ingresso(Cliente cliente, Experiencia experiencia) {
        this.cliente = cliente;
        this.experiencia = experiencia;
        this.precofinal = cliente.getTipoCliente().calcularPreco(experiencia.getPrecoBase());
        this.situacao = TicketSituation.RESERVADO;
    }
    public void pagar(){
        situacao = TicketSituation.PAGO;
    }
    public void cancelar(){
        situacao = TicketSituation.CANCELADO;
    }
    public void exebirIngresso(){
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Experiência: " + experiencia.getTitulo());
        System.out.println("Preço final: R$" + precofinal);
        System.out.println("Situação: " + situacao.getDescricao());
    }
    
}
