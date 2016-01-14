package gol;

import java.awt.Color;

public class World {
	static int maxX;
	static int maxY;
	static Cell[][] world;

	public World(int x, int y) {
		maxX = x;
		maxY = y;
		world = new Cell[maxX][maxY];
		for (x = 0; x < maxX; x++)
			for (y = 0; y < maxY; y++)
				world[x][y] = new Cell();

	}

	public void genRandomWorld() {
		for (int x = 0; x < maxX; x++)
			for (int y = 0; y < maxY; y++) {
				if (Util.randInt(0, 1) == 0)
					world[x][y].setInitalState(' ');
				else
					world[x][y].setInitalState('*');

			}

	}

	public char getCellState(int x, int y) {
		return world[x][y].getNewState();

	}

	public Cell getCellObj(int x, int y) {
		return world[x][y];
	}

	public String getCellStateStr(int x, int y) {
		String retVal = "";
		if (world[x][y].getNewState() == ' ')
			retVal = " ";
		if (world[x][y].getNewState() == '*')
			retVal = "*";

		return retVal;

	}

	public Color getCellColor(int x, int y) {
		Color retVal;
		if (world[x][y].getNewState() == ' ')
			retVal = Color.green;
		else
			retVal = Color.blue;
		return retVal;

	}

	public void printWorldOldState() {
		int x, y;
		System.out.println("Print World : Old State");
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				System.out.print(world[x][y].getOldState());
			}
			System.out.println();
		}

	}

	public static void printWorldCalcState() {
		int x, y;
		System.out.println("Print World : Calc State");

		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				System.out.print(world[x][y].getCalcState());
			}
			System.out.println();
		}

	}

	public static void printWorldNewState() {
		int x, y;
		System.out.println("Print World : New State");
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				System.out.print(world[x][y].getNewState());
			}
			System.out.println();
		}

	}

	public void calcWorld() {
		int x, y;
		for (x = 0; x < maxX; x++)
			for (y = 0; y < maxY; y++)
				calcNewState(x, y);

	}

	public static Cell[] get8NeighboursOldState(int x, int y) {
		Cell[] n = new Cell[10];
		// look at keypad to find index

		for (int i = 0; i < n.length; i++) {
			n[i] = new Cell();
		}

		try {
			n[7] = world[x - 1][y - 1];
		} catch (Exception e) {
			n[7].setOldState(' ');
		}
		try {
			n[8] = world[x - 1][y];
		} catch (Exception e) {
			n[8].setOldState(' ');
		}
		try {
			n[9] = world[x - 1][y + 1];
		} catch (Exception e) {
			n[9].setOldState(' ');
		}

		try {
			n[4] = world[x][y - 1];
		} catch (Exception e) {
			n[4].setOldState(' ');
		}
		try {
			n[6] = world[x][y + 1];
		} catch (Exception e) {
			n[6].setOldState(' ');
		}

		// bund 3 = x + 1
		try {
			n[1] = world[x + 1][y - 1];
		} catch (Exception e) {
			n[1].setOldState(' ');
		}
		try {
			n[2] = world[x + 1][y];
		} catch (Exception e) {
			n[2].setOldState(' ');
		}
		try {
			n[3] = world[x + 1][y + 1];
		} catch (Exception e) {
			n[3].setOldState(' ');
		}

		return n;

	}

	public static int getNeighboursAlive(Cell[] n) {
		// get old state data from neighbours
		int retVal = 0;
		for (int i = 0; i < n.length; i++) {
			if (n[i].getOldState() == '*') {
				retVal += 1;
			}
		}
		return retVal;
	}

	public static void calcNewState(int x, int y) {
		int neighboursAlive = getNeighboursAlive(get8NeighboursOldState(x, y));
		if (world[x][y].getOldState() == '*') {
			// Rule #1
			if (neighboursAlive < 2)
				world[x][y].setCalcState(' ');
			// Rule #2
			if (neighboursAlive == 2 || neighboursAlive == 3)
				world[x][y].setCalcState('*');

			// Rule #3
			if (neighboursAlive > 3)
				world[x][y].setCalcState(' ');
		}
		// Rule #4
		if (world[x][y].getOldState() == ' ' && neighboursAlive == 3) {
			world[x][y].setCalcState('*');
		}

		// Rule #5 by Rasmus
		// intet liv - 10 % change for at liv opstår
		if (neighboursAlive == 0) {
			if (Util.randInt(0, 100000) == 10) {
				world[Util.randInt(10, maxX-10)][Util.randInt(10, maxY-10)].setCalcState('*');
			}

		}

	}

	public void click() {
		System.out.println("Click world calcState = NewState");
		int x, y;
		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				world[x][y].setNewState(world[x][y].getCalcState());
				world[x][y].setOldState(world[x][y].getNewState());

			}

		}

	}

}
