package gol;

import java.awt.Point;

public class Screen {

	Point max = new Point();

	int countX = 0;
	int countY = 0;

	int xWidth = 0;
	int yWidth = 0;

	int radius = 0;

	double scalex = 0;
	double scaley = 0;

	double ratio = 0.0;

	public void ScreenInit(int cx, int cy, double ratio_input) {
		countX = cx;
		countY = cy;
		max.x = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		max.y = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		ratio = max.x / max.y;
		// max.x = 600;
		// max.y = 600;
		xWidth = max.x / countX;
		yWidth = max.y / countY;
		if (xWidth > 3)
			radius = (int) ((xWidth / 2) - 1);
		else
			radius = 1;

		scalex = (max.x / countX);
		scaley = (max.y / countY);

		// test
		scalex = max.x / (countX * ratio_input);
		scaley = scalex * this.ratio;
	}

	public Point convertRealXY2gridXY(Point realP) {
		Point gridPoint = new Point();

		gridPoint.x = (realP.x / xWidth) - 1;
		gridPoint.y = (realP.y / yWidth) - 1;

		gridPoint.x = (int) ((realP.x / scalex));
		gridPoint.y = (int) ((realP.y / scaley));

		return gridPoint;

	}

	public Point getCellCenter(Point p) {
		Point retVal = new Point();
		retVal.x = (p.x * xWidth) + (xWidth / 2);
		retVal.y = (p.y * yWidth) + (yWidth / 2);
		return retVal;

	}

}
