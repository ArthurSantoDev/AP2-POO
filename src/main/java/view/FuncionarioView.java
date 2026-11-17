package view;

import java.util.List;
import java.util.Scanner;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioComissionado;
import model.FuncionarioPJ;
import service.FuncionarioService;

public class FuncionarioView {
    private FuncionarioService service;
    private Scanner scanner;

    public FuncionarioView(FuncionarioService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao == 1) {
                    cadastrar();
                } else if (opcao == 2) {
                    listar();
                } else if (opcao == 3) {
                    buscarPorCodigo();
                } else if (opcao == 4) {
                    remover();
                } else if (opcao == 5) {
                    calcularRemuneracao();
                } else if (opcao == 6) {
                    exibirDadosCompletos();
                } else if (opcao == 7) {
                    System.out.println(service.gerarRelatorio());
                } else if (opcao == 8) {
                    System.out.println("Total da folha de pagamento: R$ " + String.format("%.2f", service.calcularFolhaPagamento()));
                } else if (opcao == 0) {
                    System.out.println("Encerrando o sistema...");
                } else {
                    System.out.println("Opcao invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            } catch (FuncionarioNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (FuncionarioJaCadastradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("");
        System.out.println("=== SISTEMA DE FUNCIONARIOS E FOLHA DE PAGAMENTO ===");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Buscar por codigo");
        System.out.println("4 - Remover funcionario");
        System.out.println("5 - Calcular remuneracao");
        System.out.println("6 - Exibir dados completos");
        System.out.println("7 - Gerar relatorio");
        System.out.println("8 - Total da folha de pagamento");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private void cadastrar() {
        System.out.println("Tipo de funcionario: 1-CLT | 2-PJ | 3-Comissionado");
        int tipo = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        Funcionario funcionario = null;

        if (tipo == 1) {
            System.out.print("Salario-base: ");
            double salarioBase = Double.parseDouble(scanner.nextLine().replace(",", "."));
            System.out.print("Adicional de beneficios: ");
            double adicional = Double.parseDouble(scanner.nextLine().replace(",", "."));
            funcionario = new FuncionarioCLT(codigo, nome, cpf, salarioBase, adicional);
        } else if (tipo == 2) {
            System.out.print("Valor por projeto: ");
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            System.out.print("Quantidade de projetos: ");
            int quantidade = Integer.parseInt(scanner.nextLine().trim());
            funcionario = new FuncionarioPJ(codigo, nome, cpf, valor, quantidade);
        } else if (tipo == 3) {
            System.out.print("Salario-base: ");
            double salarioBase = Double.parseDouble(scanner.nextLine().replace(",", "."));
            System.out.print("Percentual de comissao (0 a 100): ");
            double percentual = Double.parseDouble(scanner.nextLine().replace(",", "."));
            System.out.print("Total de vendas: ");
            double vendas = Double.parseDouble(scanner.nextLine().replace(",", "."));
            funcionario = new FuncionarioComissionado(codigo, nome, cpf, salarioBase, percentual, vendas);
        } else {
            System.out.println("Tipo invalido.");
            return;
        }

        service.cadastrar(funcionario);
        System.out.println("Funcionario cadastrado com sucesso.");
    }

    private void listar() {
        List<Funcionario> funcionarios = service.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println("Codigo: " + f.getCodigo()
                        + " | Nome: " + f.getNome()
                        + " | CPF: " + f.getCpf()
                        + " | Tipo: " + f.getClass().getSimpleName());
            }
        }
    }

    private void buscarPorCodigo() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine().trim());
        Funcionario f = service.buscarFuncionario(codigo);
        System.out.println("Encontrado: " + f.getNome() + " (" + f.getClass().getSimpleName() + ")");
    }

    private void remover() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine().trim());
        service.removerFuncionario(codigo);
        System.out.println("Funcionario removido com sucesso.");
    }

    private void calcularRemuneracao() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Remuneracao: R$ " + String.format("%.2f", service.calcularRemuneracao(codigo)));
    }

    private void exibirDadosCompletos() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(scanner.nextLine().trim());
        Funcionario f = service.buscarFuncionario(codigo);
        System.out.println("--- Dados completos ---");
        System.out.println("Codigo: " + f.getCodigo());
        System.out.println("Nome: " + f.getNome());
        System.out.println("CPF: " + f.getCpf());
        System.out.println("Tipo: " + f.getClass().getSimpleName());
        System.out.println("Remuneracao: R$ " + String.format("%.2f", f.calcularRemuneracao()));
        System.out.println("Tributos: R$ " + String.format("%.2f", f.calcularTributos()));
    }
}
