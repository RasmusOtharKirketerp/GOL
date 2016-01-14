package gol;

public class Cell {
	/*
	 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
	 * 
	 * (1) Any live cell with fewer than two live neighbours dies, as if caused
	 * by under-population.
	 * 
	 * (2) Any live cell with two or three live neighbours lives on to the next
	 * generation.
	 * 
	 * (3) Any live cell with more than three live neighbours dies, as if by
	 * over-population.
	 * 
	 * Any dead cell with exactly three live neighbours becomes a live cell, as
	 * if by reproduction.
	 * 
	 */
	private char oldState;
	private char calcState;
	private char newState;
	
	private static final char alive = '*';
	private static final char dead = ' ';

	public Cell() {
		setNewState(dead);
		setOldState(dead);
		setCalcState(dead);
	}
	
	public char getCalcState() {
		return calcState;
	}

	public void setCalcState(char calcState) {
		this.calcState = calcState;
	}

	public char getNewState() {
		return newState;
	}

	public void setNewState(char state) {
		this.newState = state;
	}

	public void setInitalState(char state) {
		setNewState(state);
		setOldState(state);
	}

	public char getOldState() {
		return oldState;
	}

	public void setOldState(char oldStateIsAlive) {
		this.oldState = oldStateIsAlive;
	}

}
