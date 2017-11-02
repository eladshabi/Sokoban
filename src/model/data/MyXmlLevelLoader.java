package model.data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;



public class MyXmlLevelLoader implements Level_Loader {

	/**
	 * Data loader from xml file by using location of {@link IntputStream} to {@link Level}.
	 * 
	 */

	@Override
	public Level loadLevel(InputStream in)  {
		Level l;
		XMLDecoder xis = new XMLDecoder(new BufferedInputStream(in));
		l = (Level) xis.readObject();
		xis.close();

		return l;
	}

}
