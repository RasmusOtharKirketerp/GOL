package gol;

import java.awt.Point;

public class Screen {

	Point max = new Point();

	int countX = 0;
	int countY = 0;

	int xWidth = 0;
	int yWidth = 0;

	int radius = 0;
	
	int scalex = 0;
	int scaley = 0;

	public Screen(int cx, int cy) {
		countX = cx;
		countY = cy;
		max.x = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		max.y = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		//max.x = 600;
		//max.y = 600;
		xWidth = max.x / countX;
		yWidth = max.y / countY;
		if (xWidth > 3)
			radius = (int) ((xWidth/2) - 1);
		else
			radius = 1;
		
		scalex = max.x / countX;
		scaley = max.y / countY;
	}

	public Point getCellCenter(Point p) {
		Point retVal = new Point();
		retVal.x = (p.x * xWidth) + (xWidth / 2);
		retVal.y = (p.y * yWidth) + (yWidth / 2);
		return retVal;

	}

}
