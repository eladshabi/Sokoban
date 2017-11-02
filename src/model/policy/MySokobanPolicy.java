package model.policy;


import java.util.Stack;

import javax.lang.model.element.Element;

import controller.commands.Move;
import model.data.Level;
import model.data.Point;
import model.data.*;

/**
 * Class that implements {@link SokobanPolicy} In that class have all the checks and rules
 * the player can't move box on direction that blocked.
 * the player can't move in to walls
 * the player can't poll box
 * @see Level 
 * @see Element
 * @See {@link Box}
 * @see Move
 * @param Level
 */
public class MySokobanPolicy implements SokobanPolicy {

	private Level level;
	private Point point1;
	private Point point2;
	
	
	
	public Level getLevel() {
		return level;
	}

	public MySokobanPolicy() {
		level = new Level();
		this.point1=new Point();
		this.point2=new Point();
	}

	public MySokobanPolicy(Level level) {
		this.level = level;
		this.point1=new Point();
		this.point2=new Point();

	}
	
	public boolean move(String step) {
		//System.out.println("step: " + step);
		if(level.getName()==null)
			System.out.println("null");
		
		Stack<Point> stack = new Stack<Point>();
	
		int temp_can_push = level.getPlayer().getCan_push(), i;
		Point point_in_check = new Point();

		stack.add(level.getPlayer().get_point());// point of player in stack to
													// remember it
		point_in_check = this.getNextPoint(step, level.getPlayer().get_point());// get
																				// next
																				// point
																				// by
																				// direction
																				// to
																				// be
																				// check.

		if (level.getConstElementAtPoint(point_in_check).get_passableElement()
				&& level.getMoveAbleElementAtPoint(point_in_check).get_passableElement()) {
			{
				this.point1=(stack.pop());
				this.point2=(point_in_check);
				if(!(level.switch_points(point1, point2)))
						{
								System.out.println("can't not be switch");
						}
				return true;
			}

		} else if (!(level.getConstElementAtPoint(point_in_check).get_passableElement())) {
			System.out.println("can not move");
			return false;
		}

		else if (level.getConstElementAtPoint(point_in_check).get_passableElement()
				&& (level.getMoveAbleElementAtPoint(point_in_check).get_moveable()))// case
																					// if
																					// next
																					// point
																					// is
																					// BOX
		{
			for (i = 0; i < temp_can_push; i++) {// loop that checking player
													// ability.
				stack.push(point_in_check);// get to stack the point and check
											// the next.
				point_in_check = this.getNextPoint(step, point_in_check);

				if (!(level.getConstElementAtPoint(point_in_check).get_passableElement())) {
					System.out.println("can not move");
					return false;
				}

				else if (level.getConstElementAtPoint(point_in_check).get_passableElement()// check
																							// if
																							// the
																							// next
																							// point
																							// is
																							// move
																							// able
																							// element
																							// and
																							// push
																							// him
																							// to
																							// stack.
						&& (level.getMoveAbleElementAtPoint(point_in_check).get_moveable())) {
					stack.push(point_in_check);
					
				}

				else if (level.getConstElementAtPoint(point_in_check).get_passableElement()
						&& (level.getMoveAbleElementAtPoint(point_in_check).get_passableElement()))
				// case
				// that
				// the
				// next
				// point
				// is
				// pass
				// able
				// and
				// move
				// the
				// elements.
				{
					while (!(stack.isEmpty())) {
						this.point1=(stack.pop());
						this.point2=(point_in_check);
						if(!(level.switch_points(point1, point2)))
						{
								System.out.println("can't not be switch");
						}
						point_in_check = this.point1;
					}
					
				}

			}

			// end of loop and the player can_pushe is 0;
			
			return false;

		}

		return false;

	}
//function that get direction and point and return the next point in the direction.
	public Point getNextPoint(String direction, Point point) {
		Point temp_point = new Point();

		switch (direction) {
		case "up":
			temp_point.setPoint(point.getX() - 1, point.getY());
			return temp_point;

		case "down":
			temp_point.setPoint(point.getX() + 1, point.getY());
			return temp_point;

		case "right":
			temp_point.setPoint(point.getX(), point.getY() + 1);
			return temp_point;

		case "left":
			temp_point.setPoint(point.getX(), point.getY() - 1);
			return temp_point;

		default:
			break;

		}
		return null;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
//that function check if in all the targets have boxes and if all the boxes in target return true(win game).
	public boolean check_if_win() {
		
		for(model.data.Element target:level.getGols())
		{
			if(level.getMoveAbleElementAtPoint(target.get_point()).getType()!="Box")
			{
				return false;
			}
		}
		return true;
	}
}
