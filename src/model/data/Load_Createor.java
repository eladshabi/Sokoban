package model.data;



public interface Load_Createor {

	/**
	 *Main interface using by level_loader creator.
	 * @see Text_Load_level_createor
	 * @see Xml_Loader_Creacteor
	 * @see Obj_Loader_Creacteor
	 */
	public Level_Loader getloader();
}
