package br.pucrs.rmi.model;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import br.pucrs.rmi.interfaces.FSInterface;

public class Client {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String[] retorno;

		if (args.length != 1) {
			System.out.println("Usage: java Client <machine>");
			System.exit(1);
		}

		String remoteHostName = args[0];
		String connectLocation = "//" + remoteHostName + "/Client";

		FSInterface fs = null;
		try {
			System.out.println("Connecting to client at : " + connectLocation);
			fs = (FSInterface) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("Fs connection failed: ");
			e.printStackTrace();
		}

		try {
			String opcao;
			Scanner entrada = new Scanner(System.in);

			do {
				Menu();
				opcao = entrada.nextLine();

				switch (opcao) {
				case "ls":
					Scanner input = new Scanner(System.in);
					while (input.hasNext()) {
						retorno = fs.ls(entrada.nextLine());
						for (String arquivo : retorno) {
							System.out.println("Arquivos: " + arquivo.toString());
						}
					}
					break;

				case "mkdir":
					break;

				default:
					System.out.println("Opção inválida.");
				}

			} while (!opcao.equalsIgnoreCase("exit"));
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void Menu() {
		System.out.println("\tRMI Commander");
		System.out.println("Digite:");
		System.out.println("M - Lista Opcoes");
		System.out.println("Exit  -> Sair");
		System.out.println("ls    -> Listar arquivos Ex: ls /tmp");
		System.out.println("mkdir -> Cria diretorio Ex: mkdir teste");
	}
}
