package model;

// Membro premium paga valor fixo reduzido e tem acesso prioritário
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

    public double getValorFixo() {
        return valorFixo;
    }
}
