package view;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Experiências pré-cadastradas
        List<Experiencia> experiencias = new ArrayList<>();
        experiencias.add(new Show(
                "Rock in Rio",
                "Festival de música ao ar livre",
                LocalDateTime.of(2025, 9, 20, 20, 0),
                5000, 250.0,
                "Iron Maiden"
        ));
        experiencias.add(new Passeios(
                "Centro Histórico SP",
                "Passeio pelo centro antigo da cidade",
                LocalDateTime.of(2025, 8, 10, 9, 0),
                20, 80.0,
                "Praça da Sé",
                "Carlos Silva"
        ));
        experiencias.add(new Workshop(
                "Massa Italiana",
                "Aprenda a fazer pasta fresca",
                LocalDateTime.of(2025, 7, 15, 14, 0),
                15, 120.0,
                new ArrayList<>(List.of("farinha", "ovos", "azeite"))
        ));

        List<Ingresso> ingressos = new ArrayList<>();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║     UrbanXP - Menu       ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║ 1. Listar experiências   ║");
            System.out.println("║ 2. Emitir ingresso       ║");
            System.out.println("║ 3. Listar ingressos      ║");
            System.out.println("║ 4. Pagar ingresso        ║");
            System.out.println("║ 5. Cancelar ingresso     ║");
            System.out.println("║ 0. Sair                  ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {

                case 1:
                    System.out.println("\n=== EXPERIÊNCIAS DISPONÍVEIS ===");
                    for (int i = 0; i < experiencias.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + experiencias.get(i).gerarResumo());
                        System.out.println("--------------------------------");
                    }
                    break;

                case 2:
                    // Escolhe experiência
                    System.out.println("\n=== EMITIR INGRESSO ===");
                    for (int i = 0; i < experiencias.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + experiencias.get(i).getTitulo());
                    }
                    System.out.print("Escolha a experiência: ");
                    int idxExp = Integer.parseInt(scanner.nextLine()) - 1;

                    if (idxExp < 0 || idxExp >= experiencias.size()) {
                        System.out.println("Experiência inválida.");
                        break;
                    }

                    // Dados do cliente
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(scanner.nextLine());

                    System.out.println("Perfil: [1] Regular  [2] Estudante  [3] Premium");
                    System.out.print("Escolha: ");
                    int perfil = Integer.parseInt(scanner.nextLine());

                    Desconto politica;
                    switch (perfil) {
                        case 2 -> politica = new DescontoEstudante(0.20);
                        case 3 -> politica = new DescontoPremium(150.0);
                        default -> politica = new DescontoRegular();
                    }

                    Cliente cliente = new Cliente(nome, idade, politica);
                    Ingresso ingresso = new Ingresso(cliente, experiencias.get(idxExp));
                    ingressos.add(ingresso);

                    System.out.println("\n Ingresso emitido com sucesso!");
                    ingresso.exibirIngresso();
                    break;

                case 3:
                    System.out.println("\n=== INGRESSOS EMITIDOS ===");
                    if (ingressos.isEmpty()) {
                        System.out.println("Nenhum ingresso emitido ainda.");
                    } else {
                        for (int i = 0; i < ingressos.size(); i++) {
                            System.out.println("[" + (i + 1) + "]");
                            ingressos.get(i).exibirIngresso();
                            System.out.println("--------------------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n=== PAGAR INGRESSO ===");
                    if (ingressos.isEmpty()) {
                        System.out.println("Nenhum ingresso disponível.");
                        break;
                    }
                    for (int i = 0; i < ingressos.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " +
                                ingressos.get(i).getCliente().getNome() + " — " +
                                ingressos.get(i).getExperiencia().getTitulo() + " — " +
                                ingressos.get(i).getStatus());
                    }
                    System.out.print("Escolha o ingresso: ");
                    int idxPagar = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxPagar < 0 || idxPagar >= ingressos.size()) {
                        System.out.println("Ingresso inválido.");
                        break;
                    }
                    ingressos.get(idxPagar).pagar();
                    System.out.println("Status atualizado: " + ingressos.get(idxPagar).getStatus());
                    break;

                case 5:
                    System.out.println("\n=== CANCELAR INGRESSO ===");
                    if (ingressos.isEmpty()) {
                        System.out.println("Nenhum ingresso disponível.");
                        break;
                    }
                    for (int i = 0; i < ingressos.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " +
                                ingressos.get(i).getCliente().getNome() + " — " +
                                ingressos.get(i).getExperiencia().getTitulo() + " — " +
                                ingressos.get(i).getStatus());
                    }
                    System.out.print("Escolha o ingresso: ");
                    int idxCancelar = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxCancelar < 0 || idxCancelar >= ingressos.size()) {
                        System.out.println("Ingresso inválido.");
                        break;
                    }
                    ingressos.get(idxCancelar).cancelar();
                    System.out.println("Status atualizado: " + ingressos.get(idxCancelar).getStatus());
                    break;

                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
