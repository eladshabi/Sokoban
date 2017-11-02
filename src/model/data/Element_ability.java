package model.data;

public interface Element_ability {
	/**
	 *Main interface how gives ability to get pass or move (boolean);
	 * 
	 */
	public abstract void set_moveable(boolean bool);
	public abstract boolean get_moveable();
	public abstract void set_passableElement(boolean bool);
	public abstract boolean get_passableElement();
	
	

}
