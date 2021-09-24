package com.ti2cc;
import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DAO dao = new DAO();
		Teste teste = new Teste();
		Teste[] testes;
		int fim = 0, entrada, temp;
		
		dao.conectar();

		do {
			System.out.println("Escolha:\n1)Listar\n2)Inserir\n3)Excluir\n4)Atualizar\n5)Sair\n");
			entrada = scan.nextInt();
			
			switch(entrada) {
				case 1:
					testes = dao.listarTestes();
					System.out.println("==== Mostrar === ");		
					for(int i = 0; i < testes.length; i++) {
						System.out.println(testes[i].toString());
					}
					break;
					
				case 2:
					System.out.println("Digite cod1: ");
					temp = scan.nextInt();
					System.out.println("Digite cod2: ");
					entrada = scan.nextInt();
					teste = new Teste(temp, entrada);
					if(dao.inserirTeste(teste) == true) {
						System.out.println("InserÃ§Ã£o com sucesso -> " + teste.toString());
					}
					break;
					
				case 3:
					dao.excluirTeste(teste.getCod1());
					break;
					
				case 4:
					System.out.println("Digite novo cod2: ");
					temp = scan.nextInt();
					teste.setCod2(temp);
					dao.atualizarTeste(teste);
					break;
					
				case 5:
					System.out.println("Encerrando...\n");
					fim = 1;
					break;
					
				default:
					System.out.println("ERRO: opção inválida.\n");
					break;
			}
			
		} while(fim != 1);	
		
		scan.close();
		dao.close();
	}
}