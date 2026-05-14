package model;


 //valor fixo reduzido + acesso prioritário.
public class DescontoPremium implements Desconto {

    private double valorFixo;

    public DescontoPremium(double valorFixo) {
        this.valorFixo = valorFixo;
    }

    @Override
    public double calcularPreco(double precoBase) {
        return valorFixo;
    }

    @Override
    public boolean temAcessoPrioritario() {
        return true;
    }
}
