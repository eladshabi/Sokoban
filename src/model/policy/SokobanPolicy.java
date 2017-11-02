package model.policy;


import model.data.Level;
import model.data.Point;

public interface SokobanPolicy {
/**
 * Interface using to check if some action is valid on Sokoban game program work with commands.
 * @param level - using to check if some action on level is by the policy.
 * @param move - command how take action if the policy accept it.
 * @param step - {@link String} take the direction for the command.
 * @param point - {@link Point} use to check some point by the policy
 * 
 * 
 */
	public void setLevel(Level level);
	public abstract boolean move(String step);
	public abstract Point getNextPoint(String direction, Point point);
	public abstract boolean check_if_win();//function that check by policy if it get to win condition.
	
}
