package controller.commands;
import controller.server.MyServer;
import model.Model;
import view.*;

public class Display extends Command {

	private Model model;
	private Viewer ui;
	private MyServer server;

	public Display(Model model, Viewer ui,MyServer server) {
		this.model = model;
		this.ui = ui;
		this.server=server;
	}

	@Override
	public void execute() {
     
		if (params.get(0) == "level") {
			this.ui.print(this.model.getLevel());
			if(this.server!=null&&this.server.isIn_connction())
			{
				this.server.gethandler().sendToClient("map displayed");
				if(ui.toString()=="gui"&&ui.onPlay()==false)
				{
					ui.start();
				}
			}
		}
		else 
		{
			if(this.server!=null&&this.server.isIn_connction())
			{
				this.server.gethandler().sendToClient(params.get(0));
			}
		ui.printMessage(params.remove(0));
		
		}

	}

}
