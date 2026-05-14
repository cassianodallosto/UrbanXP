package model;

import model.*;
import java.util.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Criando experiências
        Show show = new Show(
                "Rock in Rio",
                "Festival de música",
                LocalDateTime.of(2025, 9, 20, 20, 0),
                5000,
                250.0,
                "Iron Maiden"
        );

        Passeios passeio = new Passeios(
                "Centro Histórico SP",
                "Passeio pelo centro antigo",
                LocalDateTime.of(2025, 8, 10, 9, 0),
                20,
                80.0,
                "Praça da Sé",
                "Carlos Silva"
        );

        Workshop workshop = new Workshop(
                "Massa Italiana",
                "Aprenda a fazer pasta fresca",
                LocalDateTime.of(2025, 7, 15, 14, 0),
                15,
                120.0,
                new ArrayList<>(List.of("farinha", "ovos", "azeite"))
        );

        // Testando polimorfismo — gerarResumo() de cada tipo
        System.out.println("=== RESUMOS ===");
        System.out.println(show.gerarResumo());
        System.out.println();
        System.out.println(passeio.gerarResumo());
        System.out.println();
        System.out.println(workshop.gerarResumo());
        System.out.println();

        // Criando clientes com políticas diferentes
        Cliente clienteRegular = new Cliente("Ana", 30, new DescontoRegular());
        Cliente clienteEstudante = new Cliente("Bruno", 20, new DescontoEstudante(0.20));
        Cliente clientePremium = new Cliente("Carla", 35, new DescontoPremium(150.0));

        // Emitindo ingressos
        Ingresso i1 = new Ingresso(clienteRegular, show);
        Ingresso i2 = new Ingresso(clienteEstudante, show);
        Ingresso i3 = new Ingresso(clientePremium, show);

        System.out.println("=== INGRESSOS ===");
        i1.exibirIngresso();
        System.out.println();
        i2.exibirIngresso();
        System.out.println();
        i3.exibirIngresso();
        System.out.println();

        // Testando mudança de status
        System.out.println("=== MUDANÇA DE STATUS ===");
        System.out.println("Status inicial de i1: " + i1.getStatus());
        i1.pagar();
        System.out.println("Após pagar: " + i1.getStatus());
        i1.cancelar();
        System.out.println("Após cancelar: " + i1.getStatus());

        // Tentativa não válida pagar um cancelado
        i1.pagar();
        System.out.println("Tentou pagar cancelado: " + i1.getStatus());

        // Testando acesso prioritário
        System.out.println();
        System.out.println("=== ACESSO PRIORITÁRIO ===");
        System.out.println("Ana (regular): " + i1.isAcessoPrioritario());
        System.out.println("Carla (premium): " + i3.isAcessoPrioritario());
    }
}
