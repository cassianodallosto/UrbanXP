package ui;

import model.*;
import service.ExperienciaService;
import service.IngressoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Pacote ui — responsável pela interação com o atendente via terminal.
// Usa Scanner para leitura do teclado e delega a lógica aos services.
public class Console {

    private final Scanner scanner = new Scanner(System.in);
    private final ExperienciaService experienciaService = new ExperienciaService();
    private final IngressoService ingressoService = new IngressoService();
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ── Ponto de entrada ─────────────────────────────────────────────────────

    public void iniciar() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        Bem-vindo ao UrbanXP          ║");
        System.out.println("║    VIVA A CIDADE. VIVA EXPERIÊNCIAS. ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean rodando = true;
        while (rodando) {
            exibirMenuPrincipal();
            int opcao = lerInt("Escolha: ", true);
            switch (opcao) {
                case 1 -> cadastrarExperiencia();
                case 2 -> listarExperiencias();
                case 3 -> emitirIngresso();
                case 4 -> gerenciarIngresso();
                case 5 -> listarIngressos();
                case 6 -> buscarIngressosPorCliente();
                case 0 -> rodando = false;
                default -> System.out.println("⚠ Opção inválida.");
            }
        }
        System.out.println("\nEncerrando sistema. Até logo!");
    }

    // ── Menus ────────────────────────────────────────────────────────────────

    private void exibirMenuPrincipal() {
        System.out.println("\n════════════ MENU PRINCIPAL ════════════");
        System.out.println("  1. Cadastrar experiência");
        System.out.println("  2. Listar experiências");
        System.out.println("  3. Emitir ingresso");
        System.out.println("  4. Gerenciar ingresso (pagar / cancelar)");
        System.out.println("  5. Listar ingressos emitidos");
        System.out.println("  6. Buscar ingressos por cliente");
        System.out.println("  0. Sair");
        System.out.println("════════════════════════════════════════");
    }

    // ── 1. Cadastrar Experiência ─────────────────────────────────────────────

    private void cadastrarExperiencia() {
        System.out.println("\n──── CADASTRAR EXPERIÊNCIA ────");
        System.out.println("  1. Show");
        System.out.println("  2. Passeio Turístico");
        System.out.println("  3. Workshop");
        int tipo = lerInt("Tipo: ", false);

        if (tipo < 1 || tipo > 3) {
            System.out.println("⚠ Tipo inválido.");
            return;
        }

        // Dados comuns a todas as experiências
        String titulo = lerNome("Título: ", true);
        String descricao = lerNome("Descrição breve: ", true);
        LocalDateTime dataHora = lerDataHora("Data e hora (dd/MM/yyyy HH:mm): ");
        int capacidade = lerInt("Capacidade máxima: ", false);
        double preco = lerDouble("Preço base (R$): ");

        switch (tipo) {
            case 1 -> cadastrarShow(titulo, descricao, dataHora, capacidade, preco);
            case 2 -> cadastrarPasseio(titulo, descricao, dataHora, capacidade, preco);
            case 3 -> cadastrarWorkshop(titulo, descricao, dataHora, capacidade, preco);
        }

        System.out.println("✔ Experiência cadastrada com sucesso!");
    }

    private void cadastrarShow(String titulo, String descricao,
                                LocalDateTime dataHora, int capacidade, double preco) {
        String artista = lerNome("Artista principal: ", true);

        // Campos opcionais — Builder deixa claro o que é obrigatório e o que não é
        System.out.println("(Campos opcionais — pressione Enter para pular)");
        String patrocinador = lerTextoOpcional("Patrocinador: ");
        String brideStr     = lerTextoOpcional("Brinde: ");
        int restricaoIdade  = lerIntOpcional("Restrição de idade (0 = sem restrição): ");


        Show show = new Show.Builder(titulo, descricao, dataHora, capacidade, preco, artista)
                .patrocinador(patrocinador)
                .brinde(brideStr)
                .restricaoIdade(restricaoIdade)
                .build();

        experienciaService.adicionar(show);
    }

    private void cadastrarPasseio(String titulo, String descricao,
                                   LocalDateTime dataHora, int capacidade, double preco) {
        String ponto = lerNome("Ponto de encontro: ", true);
        String guia  = lerNome("Guia responsável: ", false);
        experienciaService.adicionar(new Passeios(titulo, descricao, dataHora, capacidade, preco, ponto, guia));
    }

    private void cadastrarWorkshop(String titulo, String descricao,
                                    LocalDateTime dataHora, int capacidade, double preco) {
        String matsRaw = lerNome("Materiais/ingredientes (separados por vírgula,): ", true);
        List<String> materiais = new ArrayList<>();
        for (String m : matsRaw.split(",")) {
            String trimmed = m.trim();
            if (!trimmed.isEmpty()) materiais.add(trimmed);
        }
        experienciaService.adicionar(new Workshop(titulo, descricao, dataHora, capacidade, preco, materiais));
    }

    // ── 2. Listar Experiências ───────────────────────────────────────────────

    private void listarExperiencias() {
        if (experienciaService.isEmpty()) {
            System.out.println("\n⚠ Nenhuma experiência cadastrada.");
            return;
        }

        System.out.println("\n──── LISTAR EXPERIÊNCIAS ────");
        System.out.println("  1. Por data");
        System.out.println("  2. Por preço");
        System.out.println("  3. Por título (A-Z)");
        int criterio = lerInt("Ordenar por: ", false);

        List<Experiencia> lista = switch (criterio) {
            case 1  -> experienciaService.listarPorData();
            case 2  -> experienciaService.listarPorPreco();
            case 3  -> experienciaService.listarPorTitulo();
            default -> experienciaService.getExperiencias();
        };

        System.out.println();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + lista.get(i).gerarResumo());
            System.out.println();
        }
    }

    // ── 3. Emitir Ingresso ───────────────────────────────────────────────────

    private void emitirIngresso() {
        if (experienciaService.isEmpty()) {
            System.out.println("\n⚠ Nenhuma experiência disponível. Cadastre uma primeiro.");
            return;
        }

        System.out.println("\n──── EMITIR INGRESSO ────");

        // Exibe experiências disponíveis
        List<Experiencia> lista = experienciaService.getExperiencias();
        for (int i = 0; i < lista.size(); i++) {
            Experiencia e = lista.get(i);
            System.out.printf("  [%d] %s — R$ %.2f — %d vagas%n",
                    i + 1, e.getTitulo(), e.getPrecoBase(), e.getVagasDisponiveis());
        }

        int idxExp = lerInt("Selecione a experiência: ", false);
        Experiencia experiencia = experienciaService.buscarPorIndice(idxExp);
        if (experiencia == null) {
            System.out.println("⚠ Experiência não encontrada.");
            return;
        }
        if (!experiencia.temVagasDisponiveis()) {
            System.out.println("⚠ Sem vagas disponíveis.");
            return;
        }

        // Dados do cliente
        String nome = lerNome("Nome do cliente: ", false);
        int idade   = lerInt("Idade do cliente: ", false);

        if (experiencia instanceof Show show) {
            if (idade < show.getRestricaoIdade()) {
                System.out.println("\n❌ ACESSO NEGADO: Esta experiência possui restrição de idade.");
                System.out.printf("Idade mínima: %d anos | Idade informada: %d anos.%n",
                        show.getRestricaoIdade(), idade);
                return;
            }
        }

        System.out.println("Perfil do cliente:");
        System.out.println("  1. Regular (preço cheio)");
        System.out.println("  2. Estudante (desconto %)");
        System.out.println("  3. Premium (valor fixo + acesso prioritário)");
        int perfil = lerInt("Perfil: ", false);

        if (experiencia instanceof Show show) {

        }
        Desconto desconto = switch (perfil) {
            case 2 -> {
                double pct = lerDouble("Percentual de desconto (ex: 20 para 20%): ");
                yield new DescontoEstudante(pct / 100.0);
            }
            case 3 -> {
                double fixo = lerDouble("Valor fixo do premium (R$): ");
                yield new DescontoPremium(fixo);
            }
            default -> new DescontoRegular();
        };

        Cliente cliente = new Cliente(nome, idade, desconto);
        Ingresso ingresso = ingressoService.emitir(cliente, experiencia);

        if (ingresso != null) {
            System.out.println("\n✔ Ingresso emitido:");
            ingresso.exibirIngresso();
        }
    }

    // ── 4. Gerenciar Ingresso ────────────────────────────────────────────────

    private void gerenciarIngresso() {
        if (ingressoService.isEmpty()) {
            System.out.println("\n⚠ Nenhum ingresso emitido ainda.");
            return;
        }

        System.out.println("\n──── GERENCIAR INGRESSO ────");
        List<Ingresso> lista = ingressoService.getIngressos();
        for (int i = 0; i < lista.size(); i++) {
            Ingresso ing = lista.get(i);
            System.out.printf("  [%d] %s → %s | Status: %s%n",
                    i + 1,
                    ing.getCliente().getNome(),
                    ing.getExperiencia().getTitulo(),
                    ing.getStatus());
        }

        int idx = lerInt("Selecione o ingresso: ", false);
        Ingresso ingresso = ingressoService.buscarPorIndice(idx);
        if (ingresso == null) {
            System.out.println("⚠ Ingresso não encontrado.");
            return;
        }

        System.out.println("  1. Pagar");
        System.out.println("  2. Cancelar");
        int acao = lerInt("Ação: ", false);

        switch (acao) {
            case 1 -> {
                ingressoService.pagar(ingresso);
                System.out.println("✔ Status atualizado: " + ingresso.getStatus().getDescricao());
            }
            case 2 -> {
                ingressoService.cancelar(ingresso);
                System.out.println("✔ Status atualizado: " + ingresso.getStatus().getDescricao());
            }
            default -> System.out.println("⚠ Ação inválida.");
        }
    }

    // ── 5. Listar Ingressos ──────────────────────────────────────────────────

    private void listarIngressos() {
        if (ingressoService.isEmpty()) {
            System.out.println("\n⚠ Nenhum ingresso emitido.");
            return;
        }

        System.out.println("\n──── INGRESSOS EMITIDOS ────");
        List<Ingresso> lista = ingressoService.getIngressos();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            lista.get(i).exibirIngresso();
        }
    }

    // ── 6. Buscar Ingressos por Cliente ─────────────────────────────────────────
    private void buscarIngressosPorCliente(){
        if (ingressoService.isEmpty()) {
            System.out.println("\n⚠ Nenhum ingresso emitido.");
            return;
        }

        String busca = lerNome("\nNome do Cliente: ", false).toLowerCase();

        List<Ingresso> resultado = ingressoService.getIngressos().stream()
                .filter(i -> i.getCliente().getNome().toLowerCase().contains(busca)).toList();

        if (resultado.isEmpty()) {
            System.out.println("⚠ Nenhum ingresso encontrado para \"" + busca + "\".");
            return;
        }

        System.out.println("\n──── INGRESSOS DE \"" + busca.toUpperCase() + "\" ────");
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            resultado.get(i).exibirIngresso();
        }
    }

    // ── Helpers de leitura ───────────────────────────────────────────────────

    private String lerNome(String prompt, boolean permitirNumero) {
        while (true) {
            System.out.print(prompt);
            String valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("⚠ O nome não pode vicar em branco.");
            }else if (valor.replaceAll("\\s+", "").length() < 2) {
                System.out.println("⚠ O nome deve ter pelo menos 2 caracteres.");
            } else if (!permitirNumero && Character.isDigit(valor.charAt(0))) {
                System.out.println("⚠ O nome não pode começar com número.");
            } else {
                return valor;
            }
        }
    }

    private String lerTextoOpcional(String prompt) {
        System.out.print(prompt);
        String valor = scanner.nextLine().trim();
        return valor.isEmpty() ? null : valor;
    }

    private int lerInt(String prompt, boolean aceitaZero) {
        while (true) {
            System.out.print(prompt);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor < 0) {
                    System.out.println("⚠ O valor não pode ser negativo.");
                } else if (!aceitaZero && valor == 0) {
                    System.out.println("⚠ O valor não pode ser zero.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Digite um número inteiro válido.");
            }
        }
    }

    private int lerIntOpcional(String prompt) {
        System.out.print(prompt);
        try {
            String linha = scanner.nextLine().trim();
            return linha.isEmpty() ? 0 : Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                if (valor <= 0){
                    System.out.println("⚠ O valor não pode ser nulo e/ou negativo.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Digite um número válido (ex: 120.00).");
            }
        }
    }

    private LocalDateTime lerDataHora(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String entrada = scanner.nextLine().trim();
                LocalDateTime dataDigitada = LocalDateTime.parse(entrada, FORMATTER);

                if (dataDigitada.isBefore(LocalDateTime.now())) {
                    System.out.println("⚠ Erro: A data e hora não podem ser no passado. Digite uma data futura.");
                    continue;
                }

                return dataDigitada;
            } catch (DateTimeParseException e) {
                System.out.println("⚠ Formato inválido. Use dd/MM/yyyy HH:mm (ex: 20/09/2025 20:00).");
            }
        }
    }
}
