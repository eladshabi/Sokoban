package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import controller.commands.Command;
import model.Model;
import model.data.SokoData;
import model.data.SokoDataManager;
import view.Viewer;

public class Get_data extends Command {

	private Model model;
	private Viewer ui;
	LinkedList<SokoData> dataList;
	
	public Get_data(Model model, Viewer ui) {
		this.model = model;
		this.ui = ui;
		dataList = new LinkedList<SokoData>();
	}
	
	@Override
	public void execute() throws ClassNotFoundException, IOException {
		SokoDataManager sdManager = new SokoDataManager();
		dataList = sdManager.getData();
		ui.setData(dataList);
		ui.setTable();
	}

}
