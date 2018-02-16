package mvc.model;
import java.util.LinkedList;
import java.util.List;
import mvc.controller.GameObjektObserver;

public abstract class GameObjekt {
	private List<GameObjektObserver> observers;
	private char displayChar;
	public GameObjekt(char displayChar) {
		this.observers = new LinkedList<GameObjektObserver>();
		this.displayChar = displayChar;
	}
	public abstract List<Change> getChanges();
	public void notifyObservers() {
		for (GameObjektObserver observer:observers) {
			observer.update(this);
		}
	}
	public void addOberserver(GameObjektObserver o) {
		observers.add(o);
	}
	public char getDisplayChar() {
		return displayChar;
	}
	public void removeObserver(GameObjektObserver o) {
		observers.remove(o);
	}
	public void setDisplayChar(char displayChar) {
		this.displayChar = displayChar;
	}
}
