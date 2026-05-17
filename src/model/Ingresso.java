package model;

public class Ingresso {

    private final Cliente cliente;
    private final Experiencia experiencia;
    private final double precoFinal;
    private final boolean acessoPrioritario;
    private StatusIngresso situacao;

    public Ingresso(Cliente cliente, Experiencia experiencia) {
        this.cliente          = cliente;
        this.experiencia      = experiencia;
        this.precoFinal       = cliente.getPoliticaDesconto().calcularPreco(experiencia.getPrecoBase());
        this.acessoPrioritario = cliente.getPoliticaDesconto().temAcessoPrioritario();
        this.situacao         = StatusIngresso.RESERVADO;
    }

    public void pagar() {
        if (situacao == StatusIngresso.RESERVADO) {
            situacao = StatusIngresso.PAGO;
        } else {
            System.out.println("⚠ Pagamento não permitido. Status atual: " + situacao.getDescricao());
        }
    }

    public void cancelar() {
        if (situacao != StatusIngresso.CANCELADO) {
            situacao = StatusIngresso.CANCELADO;
        } else {
            System.out.println("⚠ Ingresso já está cancelado.");
        }
    }

    public void exibirIngresso() {
        System.out.println("┌─────────────────────────────────────────");
        System.out.println("│ Cliente: " + cliente.getNome());
        System.out.println("│ Experiência: " + experiencia.getTitulo());
        System.out.println("│ Data: " + experiencia.getDataHoraFormatada());
        System.out.printf ("│ Preço final: R$ %.2f%n", precoFinal);
        System.out.println("│ Acesso prioritário: " + (acessoPrioritario ? "Sim ✔" : "Não"));
        System.out.println("│ Status: " + situacao.getDescricao());
        System.out.println("└─────────────────────────────────────────");
    }

    // Getters
    public Cliente getCliente()            { return cliente; }
    public Experiencia getExperiencia()    { return experiencia; }
    public double getPrecoFinal()          { return precoFinal; }
    public boolean isAcessoPrioritario()   { return acessoPrioritario; }
    public StatusIngresso getStatus()      { return situacao; }
}
