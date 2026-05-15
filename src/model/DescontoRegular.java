package model;

public class DescontoRegular implements Desconto {

    @Override
    public double calcularPreco(double precoBase) {
        return precoBase;
    }

    @Override
    public boolean temAcessoPrioritario() {
        return false;
    }
}