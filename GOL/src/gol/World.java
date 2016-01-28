package gol;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class World implements Serializable {

	private static final long serialVersionUID = 1L;
	static int maxX;
	static int maxY;
	static Cell[][] world;

	static int teamACount = 0;
	static int teamBCount = 0;

	// how often can life respawn
	static int respawn = 100000;

	public World() {
	};

	public void saveWorldToDisk() {
		try {
			FileOutputStream fileOut = new FileOutputStream("world.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(world);
			out.close();
			fileOut.close();
			System.out.println("World saved!!!");
		} catch (IOException i)// exception stuff
		{
			i.printStackTrace();
		}
	}

	public void loadWorldFromDisk() {
		try {

			FileInputStream fin = new FileInputStream("world.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			world = (Cell[][]) ois.readObject();
			ois.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return new StringBuffer(" cells : ").append(World.world).toString();
	}

	public void WorldInit(int x, int y) {
		maxX = x;
		maxY = y;
		world = new Cell[maxX][maxY];
		for (x = 0; x < maxX; x++)
			for (y = 0; y < maxY; y++)
				world[x][y] = new Cell();

	}

	public static void calcTeamAlive(int x, int y) {
		boolean state = world[x][y].getOldState();
		char team = world[x][y].getTeam();
		if (state) {
			if (team == 'A')
				teamACount += 1;
			if (team == 'B')
				teamBCount += 1;
		}

	}

	public void genRandomWorld() {
		teamACount = 0;
		teamBCount = 0;

		for (int x = 0; x < maxX; x++)
			for (int y = 0; y < maxY; y++) {

				if (Util.randInt(0, 1) == 1) {
					world[x][y].setInitalState(false);
				} else {
					world[x][y].setInitalState(true);

					if (Util.randInt(0, 1) == 1) {
						world[x][y].setTeam('A');
					} else
						world[x][y].setTeam('B');
				}
				calcTeamAlive(x, y);

			}

	}

	public void clearWorld() {
		for (int x = 0; x < maxX; x++)
			for (int y = 0; y < maxY; y++) {
				world[x][y].setInitalState(false);
				// world[x][y].setTeam('A');
			}

	}

	public boolean getCellState(int x, int y) {
		return world[x][y].getNewState();
	}

	public void setCellState(int x, int y, boolean newState) {
		world[x][y].setInitalState(newState);
		// world[x][y].setTeam('A');

	}

	public Cell getCellObj(int x, int y) {
		return world[x][y];
	}

	public String getCellStateStr(int x, int y) {
		String retVal = "";
		if (world[x][y].isNewStateAlive() == false)
			retVal = " ";
		if (world[x][y].isNewStateAlive())
			retVal = "*";

		return retVal;

	}

	public Color getCellColor(int x, int y) {
		Color retVal;
		if (world[x][y].isNewStateAlive())
			if (getCellObj(x, y).getTeam() == 'A')
				retVal = Color.green;
			else
				retVal = Color.red;
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
		teamACount = 0;
		teamBCount = 0;
		for (x = 0; x < maxX; x++)
			for (y = 0; y < maxY; y++) {
				calcNewState(x, y);
				calcTeamAlive(x, y);
			}

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
			n[7].setOldStateDead();
		}
		try {
			n[8] = world[x - 1][y];
		} catch (Exception e) {
			n[8].setOldStateDead();
		}
		try {
			n[9] = world[x - 1][y + 1];
		} catch (Exception e) {
			n[9].setOldStateDead();
		}

		try {
			n[4] = world[x][y - 1];
		} catch (Exception e) {
			n[4].setOldStateDead();
		}
		try {
			n[6] = world[x][y + 1];
		} catch (Exception e) {
			n[6].setOldStateDead();
		}

		// bund 3 = x + 1
		try {
			n[1] = world[x + 1][y - 1];
		} catch (Exception e) {
			n[1].setOldStateDead();
		}
		try {
			n[2] = world[x + 1][y];
		} catch (Exception e) {
			n[2].setOldStateDead();
		}
		try {
			n[3] = world[x + 1][y + 1];
		} catch (Exception e) {
			n[3].setOldStateDead();
		}

		return n;

	}

	public static int getNeighboursAlive(Cell[] n) {
		// get old state data from neighbours
		int retVal = 0;
		for (int i = 0; i < n.length; i++) {
			if (n[i].isOldStateAlive()) {
				retVal += 1;
			}
		}
		return retVal;
	}

	public static int getNeighboursAliveTeam(Cell[] n, char team) {
		// get old state data from neighbours
		int retVal = 0;
		for (int i = 0; i < n.length; i++) {
			if (n[i].isOldStateAlive() && n[i].getTeam() == team) {
				retVal += 1;
			}
		}
		return retVal;
	}

	public static void calcNewState(int x, int y) {
		// int neighboursAlive = getNeighboursAlive(get8NeighboursOldState(x,
		// y));
		int neighboursAliveA = getNeighboursAliveTeam(get8NeighboursOldState(x, y), 'A');
		int neighboursAliveB = getNeighboursAliveTeam(get8NeighboursOldState(x, y), 'B');
		int neighboursAlive = 0;
		if (neighboursAliveA > neighboursAliveB) {
			neighboursAlive = neighboursAliveA;
			world[x][y].setTeam('A');

		} else if (neighboursAliveA < neighboursAliveB) {
			neighboursAlive = neighboursAliveB;
			world[x][y].setTeam('B');

		} else {
			/*
			 * if (Util.randInt(0, 1) == 1) { neighboursAlive =
			 * neighboursAliveA; world[x][y].setTeam('A'); } else {
			 * neighboursAlive = neighboursAliveB; world[x][y].setTeam('B');
			 * 
			 * }
			 */

		}
		if (world[x][y].isOldStateAlive()) {
			// Rule #1
			if (neighboursAlive < 2) {
				world[x][y].setCalcStateDead();
			}
			// Rule #2
			if (neighboursAlive == 2 || neighboursAlive == 3) {
				world[x][y].setCalcStateAlive();
			}

			// Rule #3
			if (neighboursAlive > 3) {
				world[x][y].setCalcStateDead();
			}
		}
		// Rule #4
		if (world[x][y].isOldStateAlive() == false && neighboursAlive == 3) {
			world[x][y].setCalcStateAlive();
		}

		// Rule #5 by Rasmus
		// intet liv - 10 % change for at liv opstår

		int rx = Util.randInt(1, maxX - 1);
		int ry = Util.randInt(1, maxY - 1);
		if (neighboursAlive == 0) {
			if (Util.randInt(0, respawn) == respawn) {
				world[rx][ry].setCalcState(true);
				if (Util.randInt(0, 1) == 1)
					world[rx][ry].setTeam('A');
				else
					world[rx][ry].setTeam('B');

			}

		}

	}

	public void click() {
		// System.out.println("Click world calcState = NewState");
		int x, y;

		for (x = 0; x < maxX; x++) {
			for (y = 0; y < maxY; y++) {
				world[x][y].setNewState(world[x][y].getCalcState());
				world[x][y].setOldState(world[x][y].getNewState());
			}

		}

	}

}
