package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import controller.commands.Add_session;
import controller.commands.Command;
import controller.commands.Display;
import controller.commands.Exit_game;
import controller.commands.Load_file_name;
import controller.commands.Move;
import controller.commands.Save_file_name;
import controller.commands.SolveLevel;
import controller.server.ClientHandler;
import controller.server.Handler_sokoban_player;
import controller.server.MyServer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import model.Model;
import view.Viewer;

public class MyController implements Observer {
	// all data member that needed by the mvc.
	private Model model;
	private Viewer ui;
	private ClientHandler clientHandler = null;
	private Command_stock stock;
	private Map<String, Command> commands;
	private StringProperty stime;
	private String levelName;
	private Socket server1;
	private Client client;
	private InputStream inFromServer;
	private OutputStream outToServer;
	private MyServer server;

	// get the model view and strings to tell us if run on server or not.
	public MyController(Model model, Viewer ui, List<String> server_p) throws IOException {

		this.model = model;
		this.ui = ui;
		try {
			this.client= new Client();
			client.addObserver(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stock = new Command_stock();
		/*
		 * try { server1 = new Socket("127.0.0.1", 3333); inFromServer =
		 * server1.getInputStream(); outToServer = server1.getOutputStream(); }
		 * catch (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		/*
		 * if(server_p!=null) { if(server_p.get(0).contentEquals("server")) {
		 * Scanner s =new Scanner(server_p.get(1));
		 * this.startsUpServer(s.nextInt()); }
		 * 
		 * 
		 * }
		 */

		/**/
		stime = new SimpleStringProperty();
		stime.set("0");
		ui.bindTimer(stime);
		Timer timefx = new Timer();
		timefx.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						int sec = Integer.parseInt(stime.get());
						sec++;
						stime.set("" + sec);
					}
				});
			}
		}, 0, 1000);
		initCommands();
	}

	// set all the command to map for be use at the time.
	private void initCommands() throws IOException {
		commands = new HashMap<String, Command>();
		commands.put("move", new Move(model, server));
		commands.put("load", new Load_file_name(model, server));
		commands.put("save", new Save_file_name(model, server));
		commands.put("Display", new Display(model, ui, server));
		commands.put("Exit", new Exit_game(model, ui, this, server));
		commands.put("windata", new Add_session(ui));
		commands.put("getData", new Get_data(model, ui));
		commands.put("solve", new SolveLevel(client.getIn(), client.getOut(),client));

	}

	// this function get the notification from all the observers and call the
	// match command and put it in the commands stock.
	@Override
	public void update(Observable o, Object arg) {

		LinkedList<String> params = (LinkedList<String>) arg;

		if (o.equals(ui) || o.equals(clientHandler)) {

			if (params.get(0) == "solve") {
				String commandKey = params.removeFirst();
				String levelName = params.removeFirst();
				Command c = commands.get(commandKey);
				SolveLevel solveCommand = (SolveLevel) c;
				solveCommand.setLevel(levelName);
				if (c == null) {
					System.out.println("Command not found");
					return;
				}
				stock.insertCommand(c);
				
				
				String ck = "load";
				Command lc = commands.get(ck);
				if (lc == null) {
					System.out.println("Command not found");
					return;
				}
				LinkedList<String>list = new LinkedList<>();
				System.out.println(model.getLevel().getName());
				list.add("res/levels/"+model.getLevel().getName()+model.getLevel().getDifficulty()+".txt");
				lc.setParams(list);
				stock.insertCommand(lc);
				
				return;
			}

			if (params.get(0) == "windata") {
				String commandKey = params.removeFirst();
				Command c = commands.get(commandKey);
				if (c == null) {
					System.out.println("Command not found");
					return;
				}
				stock.insertCommand(c);
				return;
			}

			if (params.get(0) == "getData") {
				String commandKey = params.removeFirst();
				Command c = commands.get(commandKey);
				if (c == null) {
					System.out.println("Command not found");
					return;
				}
				stock.insertCommand(c);
				return;
			}

			if (!(params.get(0) == "Exit")) {
				String commandKey = params.removeFirst();
				Command c = commands.get(commandKey);
				if (c == null) {
					System.out.println("Command not found");
					return;
				}

				c.setParams(params);
				stock.insertCommand(c);

			}

			else if (params.get(0) == "Exit") {
				String commandKey = params.removeFirst();
				Command c = commands.get(commandKey);
				if (c == null) {
					System.out.println("Command not found");
					return;
				}
				stock.insertCommand(c);

			}

		} else if (o.equals(model)) {

			String commandKey = params.removeFirst();
			Command c = commands.get(commandKey);
			if (c == null) {
				System.out.println("Command not found");
				return;
			}
			c.setParams(params);
			stock.insertCommand(c);

		}

		else if (o.equals(client)) {
			System.out.println("igeasdas");
	
			String commandKey = params.removeFirst();
			Command c = commands.get(commandKey);
			if (c == null) {
				System.out.println("Command not found");
				return;
			}
			
			c.setParams(params);
			stock.insertCommand(c);
			return;
			
			/*String commandKey = params.removeFirst();
			for(int i=0;i<params.size();i++){
				LinkedList<String> list2 = new LinkedList<>();
				list2.add(params.get(i));
				Command c = commands.get(commandKey);
				if (c == null) {
					System.out.println("Command not found");
					return;
				}
				c.setParams(list2);
			
				stock.insertCommand(c);*/
				
				
			}
		
		}

	

	// if we get the string for server the func will try to starts up server
	// with the port if it up the server will wait for client 6 sec.
	public void startsUpServer(int port) {

		System.out.println("serever try");
		if (port <= 0) {
			System.out.println("invalid port");
			return;
		}
		ClientHandler c = new Handler_sokoban_player();
		MyServer s = new MyServer(port, c);
		try {
			s.start();
		} catch (Exception e) {

			System.out.println("eror on start server");
			this.stock.insertCommand((this.commands.get("Exit")));
		}

		c.addObserver(this);
		s.addObserver(this);

		this.server = s;
		this.clientHandler = this.server.gethandler();

	}

	public void stop() {
		this.stock.stop();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Viewer getUi() {
		return ui;
	}

	public void setUi(Viewer ui) {
		this.ui = ui;
	}

}
