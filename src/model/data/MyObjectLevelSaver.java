package model.data;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;



public class MyObjectLevelSaver implements Level_Saver {
/**
 * Data saver to object file by using location of {@link OutputStream} from {@link Level}.
 * 
 */
	@Override
	public void saveLevel(OutputStream out, Level level) throws IOException, ClassNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(out));
		oos.writeObject(level);
		oos.close();
		
	}

}
