package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;

public class Handler_sokoban_player extends Observable implements ClientHandler {

	private BufferedReader reader;
	private PrintWriter writer;
	private String exitString = "Exit";
	
	public Handler_sokoban_player() {

		this.reader =null;
		this.writer = null;
		

	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {

		this.reader = new BufferedReader(new InputStreamReader(inFromClient));
		this.writer = new PrintWriter(new OutputStreamWriter(outToClient));
		this.start();
		
	}

	@Override
	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String commandLine = "";
				do {
					writer.println("Enter command: ");
					writer.flush();
					try {
						commandLine = reader.readLine();
						String[] arr = commandLine.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						for (String param : arr) {
							params.add(param);
						}
						setChanged();
						notifyObservers(params);

					} catch (IOException e) {

						e.printStackTrace();
					}
				} while (!commandLine.equals(exitString));
			}
		}).start();
	}

	@Override
	public void sendToClient(String message) {
		this.writer.println(message);
		this.writer.flush();

	}

}
