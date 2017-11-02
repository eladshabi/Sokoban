package controller.commands;

import java.io.FileOutputStream;
import java.io.IOException;

import controller.server.ClientHandler;
import controller.server.MyServer;
import model.Model;
import model.data.*;
/**
 * Command how responsible of saving hire data member by using location filename.
 * @see Level
 * @see Level_Saver
 * @see String
 */


public class Save_file_name extends Command {

	private Model model;
	private MyServer server;
	
	
	
	public Save_file_name(Model model,MyServer server) {
		this.model=model;
		this.server=server;
	}
	
	@Override
	public void execute() throws ClassNotFoundException, IOException {
		
		this.model.save(params.get(0));
		if(this.server!=null&&this.server.isIn_connction())
		{
			this.server.gethandler().sendToClient("Game has been saved");
		}
		

	}

	@Override
	public String toString() {
		
		return "save";
	}

}
