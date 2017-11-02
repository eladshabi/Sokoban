package model.data;

public class Obj_Saver_Creacteor implements Saver_Createor {
/**
 * Class object creator using by {@link Level_Saver_Factory}.
 * @see MyObjectLevelSaver
 */
	@Override
	public Level_Saver get_saver() {
		
		return new MyObjectLevelSaver();
	}

}
