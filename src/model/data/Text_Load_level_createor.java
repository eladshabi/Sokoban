package model.data;

public class Text_Load_level_createor implements Load_Createor {

	/**
	 * Class Text creator using by {@link Level_Loader_Factory}.
	 * @see MyTextLevelLoader
	 */
	
	@Override
	public Level_Loader getloader() {
		
		return new MyTextLevelLoader();
	}

}
