package gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPaint extends JPanel {
	private static final long serialVersionUID = 1L;
	protected static boolean gamePause;

	static Point mouseP = new Point();
	static Point valMouse = new Point();
	static World w = new World();
	static Screen sc = new Screen();
	static GetPropertyValues properties = new GetPropertyValues();

	MainPaint() throws IOException {
		properties.getPropValues();
		// w.WorldInit(53 * properties.getX(), 32 * properties.getY());
		w.WorldInit(properties.getX(), properties.getY());
		sc.ScreenInit(World.maxX, World.maxY, properties.getRatio());
	}

	static public KeyListener myKL = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == 'r') {
				w.clearWorld();
			}
			if (e.getKeyChar() == 'g') {
				w.genRandomWorld();
			}
			if (e.getKeyChar() == 's') {
				w.saveWorldToDisk();
			}
			if (e.getKeyChar() == 'l') {
				w.loadWorldFromDisk();
			}
			if (e.getKeyChar() == 'p') {
				if (gamePause)
					gamePause = false;
				else
					gamePause = true;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
		}
	};

	static public PointerInfo pi;

	static public MouseListener ml = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// System.out.println(mouseP);
			if (w.getCellState(mouseP.x, mouseP.y) == false)
				w.setCellState(mouseP.x, mouseP.y, true);
			else
				w.setCellState(mouseP.x, mouseP.y, false);

		}

	};

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);

		this.setBackground(Color.black);
		g2d.scale(sc.scalex, sc.scaley);
		for (int x = 0; x < World.maxX; x++)
			for (int y = 0; y < World.maxY; y++) {
				g.setColor(w.getCellColor(x, y));
				g.drawLine(x, y, x, y);
				// g.drawOval(x, y, 1, 1);
			}
		valMouse = getMousePosition();
		if (valMouse != null) {
			mouseP = sc.convertRealXY2gridXY(valMouse);
			g.setColor(Color.black);
			g.drawLine(mouseP.x, mouseP.y, mouseP.x, mouseP.y);
		}
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException, IOException {

		JFrame frame = new JFrame("Game of life");
		MainPaint universe = new MainPaint();

		frame.add(universe);
		frame.setSize(sc.max.x, sc.max.y);
		frame.setVisible(true);
		frame.addMouseListener(ml);
		frame.addKeyListener(myKL);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.genRandomWorld();
		while (true) {
			universe.repaint();
			if (!gamePause) {
				w.calcWorld();
				w.click();
			}

			if (gamePause)
				frame.setTitle("Pause | Green(" + w.teamACount + ") Red(" + w.teamBCount + ")");
			else
				frame.setTitle("Game of life | Green(" + w.teamACount + ") Red(" + w.teamBCount + ")");
			Thread.sleep(100);
		}

	}

}
