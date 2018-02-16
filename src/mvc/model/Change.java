package mvc.model;

public class Change {
	
	private char display;

	private Coordinate coordinate;

	public Change(char display, Coordinate coordinate) {
		super();
		this.display = display;
		this.coordinate = coordinate;
	}

	public char getDisplay() {
		return display;
	}
	
	public int getX() {
		return coordinate.getX();
	}
	
	public int getY() {
		return coordinate.getY();
	}

}
