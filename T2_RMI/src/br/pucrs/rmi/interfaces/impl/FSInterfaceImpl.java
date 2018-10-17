package br.pucrs.rmi.interfaces.impl;

import java.io.IOException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int mkdir(String path) throws RemoteException {
		try {
			Path newPath = Paths.get(BASEPATH+path);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unlink(String path) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int write(byte[] data, String path) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int append(byte[] data, String path) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] read(String path) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
