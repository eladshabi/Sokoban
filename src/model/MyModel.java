package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import model.policy.*;
import model.data.*;

public class MyModel extends Observable implements Model {

	private SokobanPolicy policy;
	private Level level;

	public MyModel() {
		this.level = new Level();
		this.policy = new MySokobanPolicy(this.level);
	}

	public MyModel(Level level, SokobanPolicy policy) {
		this.level = level;
		this.policy = policy;
		this.policy.setLevel(this.level);
	}

	public void solveLevel(){
		
	}
	
	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public boolean checkIfWin() {

		return this.policy.check_if_win();
	}

	@Override
	public void move(String direction) {
	
		System.out.println(direction);
		this.policy.setLevel(this.level);
		this.policy.move(direction);
		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");
		if (this.policy.check_if_win()) {
			params.add("win");
		} else {
			params.add("level");
		}

		this.setChanged();
		this.notifyObservers(params);

	}

	@Override
	public void load(String filename) throws ClassNotFoundException, IOException {
		Level_Loader_Factory f = new Level_Loader_Factory();
		Level_Loader l = f.getLevel(filename);
		System.out.println(filename);
		this.level = l.loadLevel(new FileInputStream(filename));
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");
		params.add("level");
		this.setChanged();
		this.notifyObservers(params);

	}

	@Override
	public void save(String filename) throws ClassNotFoundException, IOException {
		Level_Saver_Factory f = new Level_Saver_Factory();
		Level_Saver s = f.getLevel(filename);
		s.saveLevel(new FileOutputStream(filename), this.level);
		this.setChanged();
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");
		params.add("save");
		this.setChanged();
		this.notifyObservers(params);

	}

}
