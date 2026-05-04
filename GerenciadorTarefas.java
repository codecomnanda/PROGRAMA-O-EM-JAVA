import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// Classe que representa uma tarefa individual
class Tarefa {
    private String descricao;
    private int prioridade; // 1 = mais prioritário, 5 = menos
    private boolean concluida;

    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = false; // começa sempre pendente
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public String toString() {
        return descricao + " : " + prioridade + " : " + (concluida ? "Concluída" : "Pendente");
    }
}

public class GerenciadorTarefas {
    // Comparator para ordenar por prioridade crescente (1 primeiro)
    private static final Comparator<Tarefa> COMPARADOR_PRIORIDADE = 
        Comparator.comparingInt(Tarefa::getPrioridade);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        boolean mostrarConcluidas = true; // controle do filtro

        int opcao;
        do {
            System.out.println("\n=== GERENCIADOR DE TAREFAS ===");
            System.out.println("1. Adicionar nova tarefa");
            System.out.println("2. Listar todas as tarefas");
            System.out.println("3. Marcar próxima tarefa como concluída");
            System.out.println("4. Remover uma tarefa");
            System.out.println("5. " + (mostrarConcluidas ? "Esconder" : "Mostrar") + " tarefas concluídas");
            System.out.println("6. Encerrar programa");
            System.out.print("Escolha uma opção: ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("Opção inválida! Digite um número: ");
                scanner.next(); // limpa entrada errada
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // consome quebra de linha

            switch (opcao) {
                case 1:
                    adicionarTarefa(scanner, tarefas);
                    break;
                case 2:
                    listarTarefas(tarefas, mostrarConcluidas);
                    break;
                case 3:
                    marcarProximaComoConcluida(tarefas);
                    break;
                case 4:
                    removerTarefa(scanner, tarefas, mostrarConcluidas);
                    break;
                case 5:
                    mostrarConcluidas = !mostrarConcluidas;
                    System.out.println("Exibição de tarefas concluídas: " + 
                        (mostrarConcluidas ? "ATIVADA" : "DESATIVADA"));
                    break;
                case 6:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    // Adiciona uma nova tarefa (valida prioridade entre 1 e 5)
    private static void adicionarTarefa(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.print("Descrição da tarefa: ");
        String descricao = scanner.nextLine();
        
        int prioridade;
        do {
            System.out.print("Prioridade (1-5, onde 1 é mais importante): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Valor inválido! Digite um número de 1 a 5: ");
                scanner.next();
            }
            prioridade = scanner.nextInt();
            scanner.nextLine(); // limpa buffer
            if (prioridade < 1 || prioridade > 5) {
                System.out.println("Prioridade deve estar entre 1 e 5!");
            }
        } while (prioridade < 1 || prioridade > 5);

        tarefas.add(new Tarefa(descricao, prioridade));
        System.out.println("Tarefa adicionada com sucesso!");
    }

    // Lista as tarefas ordenadas por prioridade (1 primeiro), respeitando o filtro de concluídas
    private static void listarTarefas(ArrayList<Tarefa> tarefas, boolean mostrarConcluidas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        // Cria uma cópia ordenada para não alterar a ordem original
        ArrayList<Tarefa> ordenadas = new ArrayList<>(tarefas);
        ordenadas.sort(COMPARADOR_PRIORIDADE);

        System.out.println("\n--- LISTA DE TAREFAS ---");
        int contador = 1;
        for (Tarefa t : ordenadas) {
            // Se não deve mostrar concluídas, pula as que estão concluídas
            if (!mostrarConcluidas && t.isConcluida()) {
                continue;
            }
            System.out.println(contador + ". " + t);
            contador++;
        }
        if (contador == 1) { // significa que todos os itens foram pulados
            System.out.println("(todas as tarefas estão concluídas e estão ocultas)");
        }
    }

    // Marca a tarefa pendente de maior prioridade (menor número) como concluída
    private static void marcarProximaComoConcluida(ArrayList<Tarefa> tarefas) {
        // Procura a tarefa pendente com menor prioridade (1 = mais prioritário)
        Tarefa proxima = null;
        for (Tarefa t : tarefas) {
            if (!t.isConcluida()) {
                if (proxima == null || t.getPrioridade() < proxima.getPrioridade()) {
                    proxima = t;
                }
            }
        }

        if (proxima == null) {
            System.out.println("Não há tarefas pendentes!");
        } else {
            proxima.setConcluida(true);
            System.out.println("Tarefa concluída: " + proxima.getDescricao());
        }
    }

    // Remove uma tarefa com base no índice exibido na listagem (respeitando filtro)
    private static void removerTarefa(Scanner scanner, ArrayList<Tarefa> tarefas, boolean mostrarConcluidas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa para remover.");
            return;
        }

        // Primeiro exibe a lista com índices para o usuário escolher
        ArrayList<Tarefa> ordenadas = new ArrayList<>(tarefas);
        ordenadas.sort(COMPARADOR_PRIORIDADE);

        // Cria uma lista filtrada (apenas as que estão visíveis no momento)
        ArrayList<Tarefa> visiveis = new ArrayList<>();
        for (Tarefa t : ordenadas) {
            if (mostrarConcluidas || !t.isConcluida()) {
                visiveis.add(t);
            }
        }

        if (visiveis.isEmpty()) {
            System.out.println("Nenhuma tarefa visível para remover.");
            return;
        }

        System.out.println("Tarefas visíveis:");
        for (int i = 0; i < visiveis.size(); i++) {
            System.out.println((i + 1) + ". " + visiveis.get(i));
        }

        System.out.print("Digite o número da tarefa a remover: ");
        int indice;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.print("Valor inválido! Digite um número: ");
                scanner.next();
            }
            indice = scanner.nextInt();
            scanner.nextLine();
            if (indice >= 1 && indice <= visiveis.size()) {
                break;
            } else {
                System.out.print("Número fora do intervalo. Digite entre 1 e " + visiveis.size() + ": ");
            }
        }

        Tarefa removida = visiveis.get(indice - 1);
        tarefas.remove(removida); // remove da lista original
        System.out.println("Tarefa removida: " + removida.getDescricao());
    }
}
