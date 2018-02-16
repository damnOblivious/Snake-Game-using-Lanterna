package mvc.model;

public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int y, int x) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Coordinate getRandomCoordinate() {
		int x,y;
		x = (int) (Math.random() * 97);
		y = (int) (Math.random() * 30);
		return new Coordinate(y,x);
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Coordinate other = (Coordinate) obj;
			return other.getX() == x && other.getY() == y;
		} catch (Exception e) {
			return false;
		}
	}

}
