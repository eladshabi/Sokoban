package model.data;

public class Wall extends Element{

	
	/**
	 * An {@link Element} using by {@link Level} to take place in maze
	 * that element implements {@link Element_ability}
	 * @see Point
	 * @see Element_ability
	 * 
	 */
	
	public Wall() {
		this.moveable=false;
		this.passableElement=false;
	}
	public Wall(int x,int y)
	{
		point.setPoint(x, y);
		this.moveable=false;
		this.passableElement=true;
	}
	@Override
	public void set_point(int x, int y) {
		this.point.setPoint(x, y);
	   
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Wall";
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
