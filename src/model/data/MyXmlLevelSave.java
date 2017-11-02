package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class MyXmlLevelSave implements Level_Saver {

	/**
	 * Data saver to xml file by using location of {@link OutputStream} from {@link Level}.
     *    
	 */
	@Override
	public void saveLevel(OutputStream out, Level level) throws IOException, ClassNotFoundException {
		XMLEncoder xos = new XMLEncoder(new BufferedOutputStream(out));
		xos.writeObject(level);
		xos.close();
		
	}

}
