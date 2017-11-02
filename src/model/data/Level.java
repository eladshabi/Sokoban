package model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Level implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String difficulty;
	public ArrayList<ArrayList<Element>> constant_element;
	public ArrayList<ArrayList<Element>> moveble_element;
	private Point start_point, box_point;
	private Player player;
	private ArrayList<Element> gols;
	
	
	public Point getBox_point() {
		return box_point;
	}

	public void setBox_point(Point box_point) {
		this.box_point = box_point;
	}


	/**
	 * Class Level contains all the data member the need to represent  level in Sokoban game.
	 * @see Element
	 * @see Point
	 * @see Player
	 * @see SokobanPolicy
	 * @see MySokobanPolicy
	 * @param String name
	 * @param String difficulty
	 * @param constant_element - it represent the constant element in the maze.
	 * @param moveble_element - it represent the dynamic element in the maze.
	 * @param Player player - the main object in the Sokoban game.
	 * @param Point start point
	 * @param goles - an ArrayList of Elements using to represent the targets on the game. 
	 */
	public Level() {
		this.name = null;
		this.constant_element = new ArrayList<ArrayList<Element>>();
		this.moveble_element = new ArrayList<ArrayList<Element>>();
		this.start_point = new Point();
		this.player = new Player();
		this.gols=new ArrayList<Element>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getStart_point() {
		return start_point;
	}

	public void setStart_point(Point start_point) {
		this.start_point.setPoint(start_point.getX(),start_point.getY());
	}

	public Level(Level level) {
		this.setName(level.getName());
		this.setDifficulty(level.getDifficulty());
		this.constant_element = level.getConstant_element();
		this.moveble_element = level.getMoveble_element();
		this.start_point = level.getStart_point();
		this.player = level.getPlayer();
	}

	public void setLevel(Level level) {
		this.setName(level.getName());
		this.setDifficulty(level.getDifficulty());
		this.constant_element = level.getConstant_element();
		this.moveble_element = level.getMoveble_element();
		this.start_point = level.getStart_point();
		this.player = level.getPlayer();
		this.gols=level.gols;
	}

	public Level(String name, String difficulty, Point start_point, ArrayList<ArrayList<Element>> constant_element,
			ArrayList<ArrayList<Element>> moveble_element, Player player) {
		this.name = name;
		this.difficulty = difficulty;
		this.setStart_point(start_point);
		this.constant_element = constant_element;
		this.moveble_element = moveble_element;
		this.player = player;
		this.player.set_point(this.start_point.getX(), this.start_point.getY());

	}

	public ArrayList<ArrayList<Element>> getConstant_element() {
		return constant_element;
	}

	public void setConstant_element(ArrayList<ArrayList<Element>> constant_element) {
		this.constant_element = constant_element;
	}

	public ArrayList<ArrayList<Element>> getMoveble_element() {
		return moveble_element;
	}

	public void setMoveble_element(ArrayList<ArrayList<Element>> moveble_element) {
		this.moveble_element = moveble_element;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player.point.setPoint(player.point.getX(),player.point.getY());
	}

	public String getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
//that function use to get an constant element by point.
	public Element getConstElementAtPoint(Point point) {
		return this.getConstant_element().get(point.getX()).get(point.getY());
	}
	//that function use to get an movable element by point.
	public Element getMoveAbleElementAtPoint(Point point) {
		return this.getMoveble_element().get(point.getX()).get(point.getY());
	}
//that function switching two points on level after policy command.
	public boolean switch_points(Point point1, Point point2) {
		Element temp_el;
		temp_el = this.moveble_element.get(point1.getX()).get(point1.getY());
		this.moveble_element.get(point1.getX()).set(point1.getY(), this.getMoveAbleElementAtPoint(point2));
		this.moveble_element.get(point2.getX()).set(point2.getY(), temp_el);
		this.moveble_element.get(point1.getX()).get(point1.getY()).point.setPoint(point1.getX(), point1.getY());
		this.moveble_element.get(point2.getX()).get(point2.getY()).point.setPoint(point2.getX(), point2.getY());

	
		if (this.moveble_element.get(point1.getX()).get(point1.getY()) == null) {
			return false;
		}

		return true;

	}
//that function print one maze that including the constant element and the movable element on one screen.
	public void Print_game_maze() {
		int i, j;
		for (i = 0; i < constant_element.size(); i++) {
			for (j = 0; j < constant_element.get(i).size(); j++) {
				
				switch (constant_element.get(i).get(j).getType()) {
				
				case "Floor":
				{
					if (moveble_element.get(i).get(j).getType() == "Box") {
						System.out.print("@");
						break;
					} else if (moveble_element.get(i).get(j).getType() == "Floor") {
						System.out.print(" ");
						break;
					} else if (moveble_element.get(i).get(j).getType() == "Player") {
						System.out.print("A");
						break;
					}
				}
				case "Wall":
					System.out.print("#");
					break;

				case "Target": {
					if (moveble_element.get(i).get(j).getType() == "Box") {
						System.out.print("$");// Box on target
						break;
					} else if (moveble_element.get(i).get(j).getType() == "Player") {
						System.out.print("%");// player on target.
						break;
					}
					else if(moveble_element.get(i).get(j).getType()=="Floor")
					{
						System.out.print("o");
						break;
					}
				}
				case "Start Point": {
					if (moveble_element.get(i).get(j).getType() == "Box") {
						System.out.print("@");
						break;
					} else if (moveble_element.get(i).get(j).getType() == "Floor") {
						System.out.print(" ");
						break;
					} else if (moveble_element.get(i).get(j).getType() == "Player") {
						System.out.print("A");
						break;
					}
					
				}
				default:
					break;
				}
			}
			System.out.print("\n");
		}
	}

	public List<Element> getGols() {
		return getAllTargets();
	}

	public void setGols(ArrayList<Element> gols) {
		this.gols = gols;
	}
	
//that function is add some target or element to the goals Array.
	public void setNewGol(Element gol)
	{
		this.gols.add(gol);
	}

	public List<Element> getAllWalls() {
		List<Element> walls = new ArrayList<>();
		for (ArrayList<Element> arrayList : constant_element) {
			for (Element element : arrayList) {
				if (element instanceof Wall)
					walls.add(element);
			}
		}
		return walls;
	}

	public List<Element> getAllTargets() {
		List<Element> targets = new ArrayList<>();
		for (ArrayList<Element> arrayList : constant_element) {
			for (Element element : arrayList) {
				if (element instanceof Target)
					targets.add(element);
			}
		}
		//System.out.println(targets.size());
		return targets;
	}

	public List<Element> getAllBoxes() {
		List<Element> boxes = new ArrayList<>();
		for (ArrayList<Element> arrayList : moveble_element) {
			for (Element element : arrayList) {
				if (element instanceof Box)
					boxes.add(element);
			}
		}
		return boxes;
	}
	
	
}
