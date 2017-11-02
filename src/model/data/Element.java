package model.data;

public abstract class Element implements Element_ability {
	
	protected Point point;
	protected boolean moveable;
	protected boolean passableElement;
	/**
	 * Mainly abstract class of elements using by {@link Level} and implements {@link Element_ability}.
	 * @see Element_ability
	 * @param Point
	 * @param moveable
	 * @param passable
	 */
	
	public Element() {
		point=new Point();
	}
//All the extends calls must have point and type 
	public abstract void set_point(int x,int y);
	public abstract Point get_point();
	public abstract String getType();

}
