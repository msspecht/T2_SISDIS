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
			fs = (FSInterface) Naming.lookup(connectLocation);
			Menu();
		} catch (Exception e) {
			System.out.println ("Fs connection failed: ");
			e.printStackTrace();
		}

		try {
			Scanner entrada = new Scanner(System.in);

			while(entrada.hasNext()) {
				String texto = entrada.nextLine();
				
				if(texto.startsWith("ls ")) {
					retorno = fs.ls(texto.split(" ")[1]);
					for (String arquivo : retorno) {
						System.out.println("# " + arquivo.toString());
					}
				}
				
				if(texto.startsWith("mkdir ")) {
					if(fs.mkdir(entrada.nextLine()) == 1) {
						System.out.println("Pasta criada com sucesso!");
					} else {
						System.out.println("Erro ao criar a pasta!");
					}
				}
				
				if(texto.startsWith("create ")) {
					if(fs.create(entrada.nextLine()) == 1) {
						System.out.println("Arquivo criada com sucesso!");
					} else {
						System.out.println("Erro ao criar o arquivo!");
					}
				}
				
				if(texto.startsWith("unlink ")) {
					if(fs.unlink(entrada.nextLine()) == 1) {
						System.out.println("Arquivo removido com sucesso!");
					} else {
						System.out.println("Erro ao remover o arquivo!");
					}
				}
				
				if(texto.equalsIgnoreCase("exit")) {
					System.out.println("Até Breve!");
					System.exit(0);
				}
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void Menu() {
		System.out.println("\tBem Vindo ao - RMI Commander");
		System.out.println("Lista de Comandos:");
		System.out.println("Exit   -> Sair");
		System.out.println("ls     -> Listar arquivos Ex: ls ./tmp");
		System.out.println("mkdir  -> Criar diretorio Ex: mkdir teste");
		System.out.println("create -> Criar arquivos Ex: create ./arquivo.txt");
		System.out.println("unlink -> Remove arquivos Ex: create ./arquivo.txt");
	}
}
