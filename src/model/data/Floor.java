package model.data;

public class Floor extends Element {
	
	/**
	 * An {@link Element} using by {@link Level} to take place in maze
	 * that element implements {@link Element_ability}
	 * @see Point
	 * @see Element_ability
	 * 
	 */
	
	public Floor() {
		this.moveable=false;
		this.passableElement=true;
		
	}
	
	public Floor(int x,int y)
	{
		this.set_point(x, y);
		this.moveable=false;
		this.passableElement=true;
	}
	
	@Override
	public void set_point(int x,int y) {
		this.point.setPoint(x, y);
		
	}

	@Override
	public String getType() {
		
		return "Floor";
	}

	@Override
	public Point get_point() {
		Point temp_point=new Point();
		temp_point.setPoint(this.point.getX(), this.point.getY());
		return temp_point;
	}

	@Override
	public void set_moveable(boolean bool) {
		this.moveable=bool;
		
	}

	@Override
	public boolean get_moveable() {
		
		return this.moveable;
	}

	@Override
	public void set_passableElement(boolean bool) {
		this.passableElement=bool;
		
	}

	@Override
	public boolean get_passableElement() {
		
		return this.passableElement;
	}


}
