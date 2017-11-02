package model.data;

import java.security.Policy;

public class Player extends Element implements Element_ability {

	int can_push;
	
	
	/**
	 * An {@link Element} using by {@link Level} to take place in maze
	 * that element implements {@link Element_ability}
	 * @see Point
	 * @see Element_ability
	 * @param int can push - using by {@link Policy}
	 */
	public Player() {
		
		this.can_push=1;
		this.passableElement=false;
		this.moveable=true;
		
	}
	
	
	public Player(int x,int y)
	{
		this.set_point(x, y);
		this.can_push=1;
		this.passableElement=false;
		this.moveable=true;
	}

	@Override
	public void set_point(int x, int y) {
		this.point.setPoint(x, y);		
	}


	@Override
	public String getType() {
		
		return "Player";
	}


	@Override
	public Point get_point() {
		Point temp_point=new Point();
		temp_point.setPoint(this.point.getX(), this.point.getY());
		return temp_point;
	}


	public int getCan_push() {
		return this.can_push;
	}


	public void setCan_push(int can_push) {
		this.can_push = can_push;
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
