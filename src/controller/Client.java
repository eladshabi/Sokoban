package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Client extends Observable {

	private boolean active;
	private Socket sock;
	private BufferedReader bf;

	public Client() throws IOException {
		sock = new Socket("127.0.0.1", 3333);
		this.bf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		active = true;
	}

	public InputStream getIn() throws IOException {

		return sock.getInputStream();
	}

	public OutputStream getOut() throws IOException {
		return sock.getOutputStream();
	}

	// get the answer from far services and send to the controller. 
	public void ListenForSoletion() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (active) {
					try {
						
						Scanner s = new Scanner(bf.readLine());
						s.useDelimiter(",");
						List<String> list = new ArrayList<>();
						list.add("move");
						while (s.hasNext()) {
							switch (s.next()) {
							case "u":
								list.add("up");
								break;
							case "d":
								list.add("down");
								break;
							case "r":
								list.add("right");
								break;
							case"l":
								list.add("left");
								break;
								

							default:
								break;
							}
							
						}
						s.close();
						setChanged();
						notifyObservers(list);
					} catch (IOException e) {

						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public void sendsol(String sol){
		System.out.println("in loop1");
		LinkedList<String> list = new LinkedList<>();
		list.add("move");
		String direction = null;
		for (int i= 0; i<sol.length();i++)
		{
			
			String command = "move";
			LinkedList<String> params = new LinkedList<String>();
			params.add(command);
			
			switch (sol.charAt(i)) {
			case 'u':
				list.add("up");
				direction="up";
				params.add(direction);
				setChanged();
				notifyObservers(params);
				break;
			case 'd':
				list.add("down");
				direction="down";
				params.add(direction);
				setChanged();
				notifyObservers(params);
				break;
			case 'r':
				list.add("right");
				direction="right";
				params.add(direction);
				setChanged();
				notifyObservers(params);
				break;
			case'l':
				System.out.println("in left");
				list.add("left");
				direction="left";
				params.add(direction);
				setChanged();
				notifyObservers(params);
				break;
				

			default:
				break;
			}
			
		
			
		}
	
		
	/*	Scanner s = new Scanner(sol);
		System.out.println(sol);
		s.useDelimiter(",");
		
		while (s.hasNext()) {
			System.out.println("in loop");
			switch (s.next()) {
			case 'u':
				list.add("up");
				continue;
			case 'd':
				list.add("down");
				continue;
			case 'r':
				list.add("right");
				continue;
			case'l':
				list.add("left");
				continue;
				

			default:
				break;
			}
			
		}*/
	/*	for (String string : list) {
			System.out.println(string);
		}
		
		setChanged();
		notifyObservers(list);*/
		
	}
	

}
