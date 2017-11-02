package model.data;


import java.io.IOException;
import java.util.HashMap;


/**
 * That class has {@link HashMap} that Contains {@link Level_Saver class creators 
 * @see Text_saver_createor
 * @see Xml_Saver_createor
 * @see Obj_Saver_Creacteor
 * 
 *
 */
public class Level_Saver_Factory {
	
	private static HashMap<String, Saver_Createor> factory_save=new HashMap<String,Saver_Createor>();

	//in start put all the main keys in map to creators. 
	public Level_Saver_Factory() {
	factory_save.put("txt",new Text_saver_createor());
	factory_save.put("obj",new Obj_Saver_Creacteor());
	factory_save.put("xml",new Xml_Saver_createor());
}

	//this function get string - name of file and locate the kind of file and return the level saver.
	public Level_Saver getLevel(String filename) throws ClassNotFoundException, IOException
	{
		 Saver_Createor c;
		 String str;
		 
		 str=filename.substring(filename.lastIndexOf('.')+1);
		 
				 c=factory_save.get(str);
				 if(c!=null)
				 {
					 return c.get_saver();
				 }
				 
		return null;
	}
	
}


