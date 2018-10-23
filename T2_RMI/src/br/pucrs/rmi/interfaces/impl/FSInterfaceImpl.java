package br.pucrs.rmi.interfaces.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.pucrs.rmi.interfaces.FSInterface;

public class FSInterfaceImpl extends UnicastRemoteObject implements FSInterface {

	private static final long serialVersionUID = 1L;
	private static final String BASEPATH = "./tmp/";

	public FSInterfaceImpl() throws RemoteException {
	}

	@Override
	public String[] ls(String path) throws RemoteException {
		String[] listaString = null;
		try {
			File directory = new File(path);

			File[] fList = directory.listFiles();
			listaString = new String[fList.length];
			for (int i = 0; i < fList.length; i++) {
				listaString[i] = fList[i].getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaString;
	}

	@Override
	public int mkdir(String path) throws RemoteException {
		try {
			Path newPath = Paths.get(BASEPATH + path);
			if (!Files.exists(newPath)) {

				Files.createDirectory(newPath);
				System.out.println("Directory created");

			} else {
				System.out.println("Directory already exists");
				return 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int create(String path) throws RemoteException {
		PrintWriter writer;
		try {
			writer = new PrintWriter(BASEPATH + path, "UTF-8");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int unlink(String path) throws RemoteException {
		try {
			File file = new File(BASEPATH + path);
			Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int write(byte[] data, String path) throws RemoteException {
		PrintWriter writer;
		try {
			writer = new PrintWriter(BASEPATH + path, "UTF-8");
			writer.print(data);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int append(byte[] data, String path) throws RemoteException {
		try {
			FileWriter fw = new FileWriter(BASEPATH + path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data.toString());
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public byte[] read(String path) throws RemoteException {
		FileReader fr;
		try {
			fr = new FileReader(BASEPATH + path);
			int i;
			while ((i = fr.read()) != -1) {
				System.out.print((char) i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
