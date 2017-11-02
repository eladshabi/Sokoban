package model.data;

public interface Saver_Createor {
	/**
	 *Main interface using by levels saver creator.
	 *@see Text_saver_createor 
	 * @see Xml_Saver_createor
	 * @see Obj_Saver_Creacteor
	 */
	public Level_Saver get_saver();

}
