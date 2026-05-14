package exercicio;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class exercicio07 {

    public static void main(String[] args) {

        Jedis redis = new Jedis("localhost", 6379);

        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n=== LISTA DE CONTATOS ===");
            System.out.println("1 - Criar contato");
            System.out.println("2 - Ler contato");
            System.out.println("3 - Atualizar contato");
            System.out.println("4 - Deletar contato");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {

                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Sobrenome: ");
                String sobrenome = sc.nextLine();

                System.out.print("Telefone: ");
                String telefone = sc.nextLine();

                System.out.print("Idade: ");
                String idade = sc.nextLine();

                String chave = "contatos:" + nome;

                redis.hset(chave, "nome", nome);
                redis.hset(chave, "sobrenome", sobrenome);
                redis.hset(chave, "telefone", telefone);
                redis.hset(chave, "idade", idade);

                System.out.println("Contato criado");

            } else if (opcao == 2) {

                System.out.print("Nome do contato: ");
                String nome = sc.nextLine();

                String chave = "contatos:" + nome;

                System.out.println("Nome: " + redis.hget(chave, "nome"));
                System.out.println("Sobrenome: " + redis.hget(chave, "sobrenome"));
                System.out.println("Telefone: " + redis.hget(chave, "telefone"));
                System.out.println("Idade: " + redis.hget(chave, "idade"));

            } else if (opcao == 3) {

                System.out.print("Nome do contato: ");
                String nome = sc.nextLine();

                String chave = "contatos:" + nome;

                System.out.print("Novo telefone: ");
                String telefone = sc.nextLine();

                redis.hset(chave, "telefone", telefone);

                System.out.println("Contato atualizado");

            } else if (opcao == 4) {

                System.out.print("Nome do contato: ");
                String nome = sc.nextLine();

                String chave = "contatos:" + nome;

                redis.del(chave);

                System.out.println("Contato deletado");

            } else if (opcao == 5) {

                System.out.println("Programa encerrado.");

            } else {

                System.out.println("Opção inválida.");
            }
        }

        redis.close();
        sc.close();
    }
}