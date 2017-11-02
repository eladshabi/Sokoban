package model.data;




public class Obj_Loader_Creacteor implements Load_Createor {

	/**
	 * Class object creator using by {@link Level_Loader_Factory}.
	 * @see MyObjectLevelLoader
	 */
	
	@Override
	public Level_Loader getloader() {
		
		return new MyTextLevelLoader();
	}

}
