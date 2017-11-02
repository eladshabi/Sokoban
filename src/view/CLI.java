package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;


import model.data.*;
import controller.commands.Display;
import controller.commands.Exit_game;
import controller.commands.Load_file_name;
import controller.commands.Move;
import controller.commands.Save_file_name;
import javafx.beans.property.StringProperty;

public class CLI extends Observable implements Viewer{


	private BufferedReader reader;
	private PrintWriter writer;
	private String exitString;
	private boolean stop;
	private boolean onplay;
	

	
	
	public CLI(BufferedReader reader, PrintWriter writer, String exitString) {
		this.reader = reader;
		this.writer = writer;
		this.exitString = exitString;
		
		stop=false;
	}
	
	@Override
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String commandLine = "";
				do {
					
					writer.println("Enter command: " );
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
				} while (!commandLine.equals(exitString)&&!stop);
				
				
			}
		}).start();	
	}


	@Override
	public void print(Level level) {
		
		int i, j;
		System.out.print("\n");
		for (i = 0; i < level.getConstant_element().size(); i++) {
			for (j = 0; j < level.getConstant_element().get(i).size(); j++) {
				
				
				switch (level.getConstant_element().get(i).get(j).getType()) {
				
				case "Floor":
				{
					if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
						System.out.print("@");
						break;
					} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
						System.out.print(" ");
						break;
					} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
						System.out.print("A");
						break;
					}
				}
				case "Wall":
					System.out.print("#");
					break;

				case "Target": {
					if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
						System.out.print("$");// Box on target
						break;
					} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
						System.out.print("%");// player on target.
						break;
					}
					else if(level.getMoveble_element().get(i).get(j).getType()=="Floor")
					{
						System.out.print("o");
						break;
					}
				}
				case "Start Point": {
					if (level.getMoveble_element().get(i).get(j).getType() == "Box") {
						System.out.print("@");
						break;
					} else if (level.getMoveble_element().get(i).get(j).getType() == "Floor") {
						System.out.print(" ");
						break;
					} else if (level.getMoveble_element().get(i).get(j).getType() == "Player") {
						System.out.print("A");
						break;
					}
					
				}
				default:
					break;
				}
			}
			System.out.print("\n");
		}
	}
	
	 public void printMessage(String message)
	 {
		 
		 switch (message) {
		case "save":
			System.out.println("Game saved !");
			break;
		case "win":
			System.out.println("----------------------");
			System.out.println("       You win !      ");
			System.out.println("----------------------");
			break;
		default:
			System.out.println(message);
			break;
		}
		
	 }

	@Override
	public void stop() {
		
		this.stop=true;
		
	}

	@Override
	public boolean onPlay() {
		
		return onplay;
	}

	@Override
	public LinkedList<Object> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(LinkedList<SokoData> dataList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindTimer(StringProperty timer) {
		// TODO AutSo-generated method stub
		
	}

	



		
	}


