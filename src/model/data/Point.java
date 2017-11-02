package model.data;

public class Point {

	private int x;
	private int y;

/**
 * Class how have two parameters, good to be using give location to some class.
 * @param int x
 * @param int y
 * 
 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Point newPoint = (Point)obj;
		if (x == newPoint.getX() && y == newPoint.getY())
			return true;
		return false;
	}

	public Point(Point point)
	{
		this.x=point.getX();
		this.y=point.getY();
	}

	public void setPoint(int x,int y)
	{
		this.setX(x);
		this.setY(y);
	}
	@Override
	public String toString() {
		return x+","+y+"\n";
	}
	
}
