package model.data;

import java.io.IOException;

import java.io.OutputStream;



/**
 * Main interface that get location to save from the level data member.
 * @see OutputStream
 * @see Level
 * 
 *
 */
public interface Level_Saver {
	
	public void saveLevel(OutputStream out,Level level) throws IOException, ClassNotFoundException;
}
