package controller.commands;

import java.io.IOException;
import java.util.LinkedList;
import view.Viewer;
import view.CLI;


/**
 * Main interface how have execute command using by CLI.
 * @see CLI
 * @throws ClassNotFoundException
 * @throws IOException
 */

public abstract class Command {
	
	protected LinkedList<String> params;
	
	public void setParams(LinkedList<String> params) {
		this.params = params;
	}
	
	public abstract void execute() throws ClassNotFoundException, IOException;
	
}
