package br.pucrs.rmi.model;

import java.rmi.Naming;

import br.pucrs.rmi.interfaces.FSInterface;

public class Client {

	public static void main (String[] argv) {
		try {
			FSInterface hello = (FSInterface) Naming.lookup ("//localhost/Client");
			System.out.println (hello.ls(""));
		} catch (Exception e) {
			System.out.println ("Client failed:");
			e.printStackTrace();
		}
	}
}
