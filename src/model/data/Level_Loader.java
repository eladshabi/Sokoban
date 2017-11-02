package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public interface Level_Loader {
	
	/**
	 * Main interface that get location to load from it to the level data member.
	 * @see IntputStream
	 * @see Level
	 * 
	 *
	 */
	
	public Level loadLevel(InputStream in) throws IOException, ClassNotFoundException;
	

}
