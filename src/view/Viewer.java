package view;

import java.util.LinkedList;

import javafx.beans.property.StringProperty;
import model.data.Level;
import model.data.SokoData;

public interface Viewer {

	
	public void print(Level level);
	public void start();
	public void stop();
	public void printMessage(String message);
	public boolean onPlay();
	public LinkedList<Object> getData();
	public void setData(LinkedList<SokoData> dataList);
	public void setTable();
	public void bindTimer(StringProperty timer);
}
