package gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPaint extends JPanel {
	private static final long serialVersionUID = 1L;
	static World w = new World(55*2, 30*2);
	static Screen sc = new Screen(World.maxX, World.maxY);

	public MainPaint() {
		// TODO Auto-generated constructor stub
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(Color.black);
		g2d.scale(sc.scalex, sc.scaley);
		for (int x = 0; x < World.maxX; x++)
			for (int y = 0; y < World.maxY; y++) {
				g.setColor(w.getCellColor(x, y));
				g.drawLine(x, y, x, y);
			}

	}

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame("Game of life");
		MainPaint universe = new MainPaint();

		frame.add(universe);
		frame.setSize(sc.max.x, sc.max.y);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.genRandomWorld();
		while (true) {
			Thread.sleep(300);
			w.calcWorld();
			w.click();

			universe.repaint();
		}

	}

}
