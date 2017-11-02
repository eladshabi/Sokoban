package model.data;

public class Xml_Loader_Creacteor implements Load_Createor {

	/**
	 * Class Xml creator using by {@link Level_Loader_Factory}.
	 * @see MyXmlLevelLoader
	 */
	
	@Override
	public Level_Loader getloader() {
		
		return new MyXmlLevelLoader();
	}

}
