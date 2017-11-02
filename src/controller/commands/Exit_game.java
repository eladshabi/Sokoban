package controller.commands;

import controller.MyController;
import controller.server.MyServer;
import model.Model;
import model.data.*;
import view.Viewer;
/**
 * Command that responsible to safe exit .
 * @see Level
 *
 */
public class Exit_game extends Command {

	private MyController controller;
	private Model model;
	private Viewer ui;
	private MyServer server;
	
	
	public Exit_game(Model model, Viewer ui,MyController controller,MyServer myServer) {
		this.model=model;
		this.ui=ui;
		this.controller=controller;
		this.server=myServer;
	}
	
	public void execute() {
	 
	 if(this.server!=null)
	 {
		 
		 this.server.stop();
	 }
	
	 this.controller.stop();
	 ui.stop();
	  
     
	}

	


}
