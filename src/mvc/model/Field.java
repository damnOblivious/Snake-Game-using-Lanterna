package mvc.model;

import java.util.LinkedList;
import java.util.List;

import mvc.controller.GameObjektObserver;
import javafx.util.Pair;

public class Field extends GameObjekt implements GameObjektObserver {

	private Food food;
	private Snake snake;
	private int width, height;

	private List<Change> changes;

	public Field(int width, int height) {
		super(' ');
		changes = new LinkedList<Change>();
		snake = new Snake(this,5);
		snake.addOberserver(this);
		food = new Food();
		food.addOberserver(this);
		this.width = width;
		this.height = height;
	}

	public boolean isAlive() {
		return snake.isAlive();
	}

	public void setDirection(Direction d) {
		snake.setDirection(d);
	}

	/**
	 * Will return if a Coordinate is Outside the field
	 */
	public boolean coordinateIsOut(Coordinate c) {
		if (c.getX() < 0 || c.getY() < 0)
			return true;
		if (c.getX() >= width || c.getY() >= height)
			return true;
		return false;
	}


	/**
	 * Return if c is the same coordinate of the eatable item
	 */
	public boolean didEat(List<Coordinate> c) {
		Coordinate f = food.getCoordinate();
		if (c.contains(f)) {
			food.eat(c);
			return true;
		}
		return false;
	}

	/**
	 * Starts the Thread for the snake
	 */
	public void start() {
		Thread snakeThread = new Thread(snake);
		snakeThread.start();
		food.notifyObservers();
	}

	@Override
	public List<Change> getChanges() {
		List<Change> returnable = new LinkedList<Change>();
		returnable.addAll(changes);
		changes.clear();
		return returnable;
	}

	@Override
	public void update(GameObjekt o) {
		changes.addAll(o.getChanges());
		notifyObservers();
	}

}
