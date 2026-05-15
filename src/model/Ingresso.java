package model;

public class Ingresso {
    private Cliente cliente;
    private Experiencia experiencia;
    private double precoFinal;
    private StatusIngresso situacao;
    private boolean acessoPrioritario;

    public Ingresso(Cliente cliente, Experiencia experiencia) {
        this.cliente = cliente;
        this.experiencia = experiencia;
        this.precoFinal = cliente.getPoliticaDesconto().calcularPreco(experiencia.getPrecoBase());
        this.acessoPrioritario = cliente.getPoliticaDesconto().temAcessoPrioritario();
        this.situacao = StatusIngresso.RESERVADO;
    }

    //Pagar e Cancelar
    public void pagar() {
        if (situacao == StatusIngresso.RESERVADO) {
            situacao = StatusIngresso.PAGO;
        }
        else{}
    }

    public void cancelar() {
        if (situacao != StatusIngresso.CANCELADO) {
            situacao = StatusIngresso.CANCELADO;
        }
        else{}
    }

    //getters
    public Experiencia getExperiencia() {
        return experiencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public StatusIngresso getStatus() {
        return situacao;
    }

    public boolean isAcessoPrioritario() {
        return acessoPrioritario;
    }

    public void exibirIngresso(){
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Experiência: " + experiencia.getTitulo());
        System.out.println("Preço final: R$" + precoFinal);
        System.out.println("Status: " + situacao);
    }
    
}
