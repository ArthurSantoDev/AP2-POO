# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

Projeto em Java com Programação Orientada a Objetos, tratamento de exceções e testes com JUnit 5.

## Funcionalidades

- Cadastrar funcionários (CLT, PJ e Comissionado)
- Listar funcionários cadastrados
- Buscar por código, CPF ou nome
- Remover funcionário
- Calcular remuneração de cada funcionário
- Exibir dados completos de um funcionário
- Gerar relatório com todos os funcionários e salários
- Calcular total da folha de pagamento

## Regras de remuneração

| Categoria | Cálculo |
|---|---|
| FuncionarioCLT | salário-base + adicional de benefícios |
| FuncionarioPJ | valor por projeto x quantidade de projetos |
| FuncionarioComissionado | salário-base + vendas x percentual de comissão |

## Organização do projeto

```
src/main/java/
 ├── model/       Funcionario (abstrata), subclasses e interfaces
 ├── repository/  armazenamento em memória (HashMap)
 ├── service/     regras de negócio
 ├── exception/   exceções personalizadas
 ├── view/        menu no console
 └── Main.java
src/test/java/    testes JUnit 5 de cada classe
```

## Como executar

Requisitos: Java 17+ e Maven.

```bash
mvn compile exec:java -Dexec.mainClass=Main
```

## Como executar os testes

```bash
mvn test
```
