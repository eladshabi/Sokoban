package model.data;


import java.io.IOException;
import java.util.HashMap;



public class Level_Loader_Factory {

	private static HashMap<String, Load_Createor> factory=new HashMap<String,Load_Createor>();

	
	
	/**
	 * That class has {@link HashMap} that Contains {@link Level_Loader class creators 
	 * @see Text_Load_level_createor
	 * @see Xml_Loader_Creacteor
	 * @see Obj_Loader_Creacteor
	 * 
	 *
	 */
	
	//in start put all the main keys in map to creators. 
	public Level_Loader_Factory() {
		
		
		factory.put("txt",new Text_Load_level_createor());
		factory.put("obj",new Obj_Loader_Creacteor());
		factory.put("xml",new Xml_Loader_Creacteor());
		
	}
	
	//this function get string - name of file and locate the kind of file and return the level loader..
	public Level_Loader getLevel(String filename) throws ClassNotFoundException, IOException
	{
		 Load_Createor c;
		 String str;
		 
		 str=filename.substring(filename.lastIndexOf('.')+1);
		 
				 c=factory.get(str);
				 if(c!=null)
				 {
					 return c.getloader();
				 }
				 
		return null;
	}
	
	
	
}
