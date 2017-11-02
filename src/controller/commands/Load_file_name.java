package controller.commands;

import java.io.FileInputStream;
import java.io.IOException;

import controller.server.ClientHandler;
import controller.server.MyServer;
import model.Model;
import model.data.Level;
import model.data.Level_Loader;


/**
 * Command how responsible to load file by using hire data member.
 * @see Level
 * @see String
 *
 */

public class Load_file_name extends Command {
	
	private Model model;
	private MyServer server;
	

	
	public Load_file_name(Model model,MyServer server) {
		this.model=model;
		this.server=server;
	}
	
	@Override
	public void execute() throws ClassNotFoundException, IOException {
		
		this.model.load(params.get(0));
		
		if(this.server!=null&&this.server.isIn_connction())
		{
			this.server.gethandler().sendToClient("Game has been loaded");
			
		}
		
	}


	@Override
	public String toString() {
		
		return "load";
	}

}
