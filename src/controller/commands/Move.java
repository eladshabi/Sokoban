package controller.commands;

import java.util.concurrent.TimeUnit;

import controller.server.ClientHandler;
import controller.server.MyServer;
import model.Model;
import model.data.Level;
import model.data.Point;
/**
 * Command how responsible to execute the action move by using hire data member.
 * @see Point
 * @see Level
 *
 */

public class Move extends Command {

	
	
	private Model model;
	private MyServer server;

	public Move(Model model,MyServer server) {
		this.model=model;
		this.server=server;
		
	
	}
	
	@Override
	public void execute() {

		model.move(params.get(0));
		if(this.server!=null&&this.server.isIn_connction())
		{
			this.server.gethandler().sendToClient("player has been moved");
		}

	}


	@Override
	public String toString() {
		
		return "move";
	}

}
