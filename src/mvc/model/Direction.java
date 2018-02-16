package mvc.model;

public enum Direction {

	Up(0,-1),
	Down(0,1),
	Left(-1,0),
	Right(1,0);

	public int y;
	public int x;

	Direction(int x, int y) {
		this.y = y;
		this.x = x;
	}

}
