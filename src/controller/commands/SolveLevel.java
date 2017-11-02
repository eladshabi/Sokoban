package controller.commands;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.Scanner;
import java.util.Timer;

import controller.Client;
import controller.server.MyServer;
import model.Model;
import view.Viewer;

public class SolveLevel extends Command {
	
	private String level;
	private InputStream inFromServer;
	private OutputStream outToServer;
	private Client client;

	

	public SolveLevel(InputStream inFromServer, OutputStream outToServer,Client client) {
		this.inFromServer = inFromServer;
		this.outToServer = outToServer;
		this.client = client;
	}

	@Override
	public void execute() {
		
		PrintStream printer = new PrintStream(outToServer);
		Scanner reader = new Scanner(inFromServer);
		System.out.println("solve level command says level is: " + level);
		printer.println(level);
		System.out.println("solve level command says level is: " + level);
		String fromServer = reader.nextLine();
		System.out.println("solve level command says level is: " + level);
		System.out.println(fromServer.toString());
		client.sendsol(fromServer);
		reader.close();
		
		
		/*
		PrintStream printer = new PrintStream(outToServer);
		Scanner reader = new Scanner(inFromServer);
		System.out.println("solve level command says level is: " + level);
		printer.print(level);
		String fromServer = reader.nextLine();
		System.out.println(fromServer.toString());*/
		

	}

	public void setLevel(String level) {
		this.level = level;
	}
}
