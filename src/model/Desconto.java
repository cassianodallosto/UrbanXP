package model;
//Interface para os descontos, a fim de poder adicionar novas politicas
public interface Desconto {

    double calcularPreco(double precoBase);

    boolean temAcessoPrioritario();
}
