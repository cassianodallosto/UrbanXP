package model;

//desconto percentual sobre o preço base.

public class DescontoEstudante implements Desconto {

    private double percentualDesconto; // ex: 0.20 para 20%

    public DescontoEstudante(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    @Override
    public double calcularPreco(double precoBase) {
        return precoBase * (1 - percentualDesconto);
    }

    @Override
    public boolean temAcessoPrioritario() {
        return false;
    }
}
