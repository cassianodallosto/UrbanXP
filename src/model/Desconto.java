package model;

// Interface para política de desconto — permite adicionar novas políticas
// sem alterar Experiencia ou Ingresso (Strategy Pattern)
public interface Desconto {
    double calcularPreco(double precoBase);
    boolean temAcessoPrioritario();
}
