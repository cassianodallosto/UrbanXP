import ui.Console;

// Ponto de entrada do sistema — apenas inicializa a interface de console.
// Toda a lógica de negócio fica nos pacotes service e model.
public class Main {
    public static void main(String[] args) {
        new Console().iniciar();
    }
}
