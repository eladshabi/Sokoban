package controller.commands;

import java.io.IOException;
import java.util.LinkedList;

import model.data.SokoData;
import model.data.SokoDataManager;
import view.Viewer;

public class Add_session extends Command {

	
	private Viewer ui;
	private String uname;
	private String lname;
	private int step1;
	private int timeg;
	private int score1;
	
	public Add_session(Viewer ui) {
		this.ui = ui;
	}

	@Override
	public void execute() throws ClassNotFoundException, IOException {
		LinkedList<Object> params = new LinkedList<Object>();
		params = ui.getData();
		this.uname=(String) params.get(0);
		this.lname=(String) params.get(1);
		this.step1=(Integer) params.get(2);
		this.timeg=(Integer) params.get(3);
		this.score1=(Integer) params.get(4);
		
		SokoData sokodata = new SokoData((String)params.get(0), (String)params.get(1), (Integer)params.get(2), (Integer)params.get(3), (Integer)params.get(4));
		SokoDataManager sokoManagar = new SokoDataManager();
		sokoManagar.addSokoData(sokodata);
		
	}

}
