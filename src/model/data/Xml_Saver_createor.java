package model.data;

public class Xml_Saver_createor implements Saver_Createor {
	/**
	 * Class xml creator using by {@link Level_Saver_Factory}.
	 * @see MyXmlLevelSave
	 */
	public Level_Saver get_saver() {
		
		return new MyXmlLevelSave();
	}

}
