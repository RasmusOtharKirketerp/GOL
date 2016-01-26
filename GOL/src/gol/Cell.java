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
	private boolean oldStateAlive;
	private boolean calcStateAlive;
	private boolean newStateAlive;

	private char team;

	// private static boolean alive;

	public Cell() {
		setNewState(false);
		setOldState(false);
		setCalcState(false);
		setTeam(' ');
	}

	// CALC STATE
	public void setCalcStateDead() {
		setCalcState(false);
	}

	public void setCalcStateAlive() {
		setCalcState(true);
	}

	public boolean getCalcState() {
		return calcStateAlive;
	}

	public void setCalcState(boolean calcState) {
		this.calcStateAlive = calcState;
	}

	public boolean isCalcStateAlive() {
		return calcStateAlive;
	}

	// NEW STATE
	public void setNewStateDead() {
		setNewState(false);
	}

	public void setNewStateAlive() {
		setNewState(true);
	}

	public boolean getNewState() {
		return newStateAlive;
	}

	public void setNewState(boolean state) {
		this.newStateAlive = state;
	}

	public boolean isNewStateAlive() {
		return newStateAlive;
	}

	// OLD STATE

	public void setOldStateDead() {
		setOldState(false);
	}

	public void setOldStateAlive() {
		setOldState(true);
	}

	public boolean getOldState() {
		return oldStateAlive;
	}

	public void setOldState(boolean oldStateIsAlive) {
		this.oldStateAlive = oldStateIsAlive;
	}

	public boolean isOldStateAlive() {
			return oldStateAlive;
	}

	// INITIAL STATE
	public void setInitalState(boolean c) {
		setNewState(c);
		setCalcState(c);
		setOldState(c);
	}

	public char getTeam() {
		return team;
	}

	public void setTeam(char team) {
		this.team = team;
	}

}
