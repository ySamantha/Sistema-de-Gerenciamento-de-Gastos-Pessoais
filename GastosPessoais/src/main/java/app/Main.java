package app;

import facade.FinanceiroFacade;
import model.Relatorio;
import observer.NotificadorLembrete;
import observer.Orcamento;
import strategy.PorDescricao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orcamento orcamento = new Orcamento();
        orcamento.adicionarNotificador(new NotificadorLembrete());
        FinanceiroFacade sistema = new FinanceiroFacade(new PorDescricao(), orcamento);
        Relatorio relatorio = new Relatorio(orcamento);


        System.out.println("=== Configuracao Inicial ===");
        double renda;
        do {
            System.out.print("Digite sua renda mensal: R$ ");
            renda = sc.nextDouble();
            if (renda <= 0) System.out.println("Renda invalida. Tente novamente.");
        } while (renda <= 0);
        sc.nextLine();
        orcamento.setRenda(renda);
        double sugestao = orcamento.getLimiteSugerido();
        orcamento.setLimite(sugestao);
        System.out.printf("Limite sugerido automaticamente: R$ %.2f (70 por cento da renda)%n", sugestao);

        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Adicionar despesa");
            System.out.println("2. Listar despesas e mostrar saude financeira");
            System.out.println("3. Definir novo limite de orcamento");
            System.out.println("4. Apagar todas as despesas");
            System.out.println("5. Mostrar renda atual e alterar");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("Descricao: ");
                String desc = sc.nextLine();
                System.out.print("Valor: ");
                double valor = sc.nextDouble();
                sc.nextLine();
                sistema.registrarDespesa(valor, desc);
            } else if (opcao == 2) {
                relatorio.mostrar();
            } else if (opcao == 3) {
                System.out.print("Novo limite: ");
                double novoLimite = sc.nextDouble();
                sc.nextLine();
                orcamento.setLimite(novoLimite);

                System.out.print("Deseja zerar o total ja gasto? (s/n): ");
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    orcamento.resetarGasto();
                }

                System.out.println("Novo limite definido!");
            } else if (opcao == 4) {
                relatorio.apagarTudo();
                orcamento.resetarGasto();
            } else if (opcao == 5) {
                System.out.printf("Renda atual: R$ %.2f%n", orcamento.getRenda());
                System.out.print("Deseja alterar a renda? (s/n): ");
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("s")) {
                    System.out.print("Digite a nova renda mensal: R$ ");
                    double novaRenda = sc.nextDouble();
                    sc.nextLine();
                    if (novaRenda > 0) {
                        orcamento.setRenda(novaRenda);
                        double novaSugestao = orcamento.getLimiteSugerido();
                        orcamento.setLimite(novaSugestao);
                        orcamento.resetarGasto();
                        System.out.printf("Renda alterada para R$ %.2f e limite ajustado para R$ %.2f%n", novaRenda, novaSugestao);
                    } else {
                        System.out.println("Renda invalida. Nao alterada.");
                    }
                }
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opcao invalida.");
            }
        }

        sc.close();
        System.out.println("Encerrado.");
    }
}
