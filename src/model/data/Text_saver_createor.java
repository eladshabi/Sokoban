package model.data;

public class Text_saver_createor implements Saver_Createor {

	/**
	 * Class text creator using by {@link Level_Saver_Factory}.
	 * @see MyTextLevelSaver
	 */
	public Level_Saver get_saver() {
		
		return new MyTextLevelSaver();
	}

}
