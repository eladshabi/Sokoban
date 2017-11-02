package model;

import java.io.IOException;

import model.data.Level;

public interface Model {

	public Level getLevel();
	public void move(String direction);
	public void load(String filename) throws ClassNotFoundException, IOException;
	public void save(String filename)throws ClassNotFoundException, IOException;;
	public boolean checkIfWin();
	public void solveLevel();
	

}
