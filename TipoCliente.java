package model;

public enum TipoCliente{

    REGULAR("Regular") {
        @Override
        public double calcularPreco(double precoBase) {
            return precoBase;
        }
    },

    ESTUDANTE("Estudante") {
        @Override
        public double calcularPreco(double precoBase) {
            return precoBase * 0.67;
        }
    },

    PREMIUM("Premium") {
        @Override
        public double calcularPreco(double precoBase) {
            return Math.max(precoBase - 50.0, 0);
        }
    };

    private final String descricao;

    TipoCliente(String descricao) {
        this.descricao = descricao;
    }



}
