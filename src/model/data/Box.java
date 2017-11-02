package model.data;

public class Box extends Element implements Element_ability {
/**
 * An {@link Element} using by {@link Level} to take place in maze
 * that element implements {@link Element_ability}
 * @see Point
 * @see Element_ability
 * 
 */
	public Box() {
		this.passableElement=false;
		this.moveable=true;
	}
	
	public Box(int x,int y)
	{
		this.set_point(x, y);
		this.passableElement=false;
		this.moveable=true;
	}

	@Override
	public void set_point(int x, int y) {
		this.point.setPoint(x, y);
		
	}

	@Override
	public String getType() {
		
		return "Box";
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
