package mvc.controller;

import java.util.List;

import mvc.model.Change;
import mvc.model.Direction;
import mvc.model.Field;
import mvc.model.GameObjekt;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

/**
 * 
 * This is the Controller. It determines when to update the View and gives the View access to the Model.
 * The Controller also reads the commands given by the user and gives the changes to the Model
 *
 */

public class ViewController implements GameObjektObserver, Runnable {
	
	private SwingTerminal view;
	
	private Field field;
	
	public ViewController() {
		view = TerminalFacade.createSwingTerminal();
		field = new Field(view.getTerminalSize().getColumns(), view.getTerminalSize().getRows());
		field.addOberserver(this);
	}
	
	/**
	 * Star screen and all Threads
	 */
	public void startScreen() {
		view.enterPrivateMode();
		view.setCursorVisible(false);
		Thread controllerThread = new Thread(this);
		controllerThread.start();
		field.start();
	}

	@Override
	public void update(GameObjekt o) {
		List<Change> changes = o.getChanges();
		for (Change change : changes) {
			char display = change.getDisplay();
			view.moveCursor(change.getX(), change.getY());
			view.putCharacter(display);
		}
	}
	
	/**
	 * Read input from the user
	 */
	private void read() {
		Key key = view.readInput();
		if (key != null) {
			Kind kind = key.getKind();
			switch (kind) {
			case ArrowLeft: 
				field.setDirection(Direction.Left);
				break;
			case ArrowRight:
				field.setDirection(Direction.Right);
				break;
			case ArrowDown:
				field.setDirection(Direction.Down);
				break;
			case ArrowUp:
				field.setDirection(Direction.Up);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void run() {
		while(field.isAlive()) {
			read();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		view.exitPrivateMode();
	}

}
