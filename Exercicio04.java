package exercicio;

import java.util.Scanner;
import redis.clients.jedis.Jedis;

public class Exercicio04 {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost", 6379);
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\nLISTA DE CONTATOS");
            System.out.println("1 - Criar contato");
            System.out.println("2 - Ler contato");
            System.out.println("3 - Atualizar contato");
            System.out.println("4 - Deletar contato");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {

                System.out.print("Apelido: ");
                String apelido = sc.nextLine();

                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Sobrenome: ");
                String sobrenome = sc.nextLine();

                System.out.print("Telefone: ");
                String telefone = sc.nextLine();

                System.out.print("Idade: ");
                String idade = sc.nextLine();

                String chave = "contatos:" + apelido;

                jedis.hset(chave, "nome", nome);
                jedis.hset(chave, "sobrenome", sobrenome);
                jedis.hset(chave, "telefone", telefone);
                jedis.hset(chave, "idade", idade);

                System.out.println("Contato criado!");

            } else if (opcao == 2) {

                System.out.print("Apelido do contato: ");
                String apelido = sc.nextLine();

                String chave = "contatos:" + apelido;

                System.out.println("Nome: " + jedis.hget(chave, "nome"));
                System.out.println("Sobrenome: " + jedis.hget(chave, "sobrenome"));
                System.out.println("Telefone: " + jedis.hget(chave, "telefone"));
                System.out.println("Idade: " + jedis.hget(chave, "idade"));

            } else if (opcao == 3) {

                System.out.print("Apelido do contato: ");
                String apelido = sc.nextLine();

                String chave = "contatos:" + apelido;

                System.out.print("Novo telefone: ");
                String telefone = sc.nextLine();

                jedis.hset(chave, "telefone", telefone);

                System.out.println("Contato atualizado!");

            } else if (opcao == 4) {

                System.out.print("Apelido do contato: ");
                String apelido = sc.nextLine();

                String chave = "contatos:" + apelido;

                jedis.del(chave);

                System.out.println("Contato removido!");

            } else if (opcao == 5) {

                System.out.println("Programa encerrado.");

            } else {

                System.out.println("Opção inválida!");

            }
        }

        jedis.close();
        sc.close();
    }
}