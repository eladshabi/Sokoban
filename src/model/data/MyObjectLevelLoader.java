package model.data;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;




public class MyObjectLevelLoader implements Level_Loader  {

	/**
	 * Data loader from object file by using location of {@link IntputStream} to {@link Level}.
	 * 
	 */
	
	@Override
	public Level loadLevel(InputStream in) throws IOException, ClassNotFoundException {
		try {
			
			Level l;
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(in));
			l = (Level) ois.readObject();
			ois.close();
			return l;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		

	}
}