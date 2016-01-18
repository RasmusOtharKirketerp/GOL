package gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPaint extends JPanel {
	private static final long serialVersionUID = 1L;
	static World w = new World(53 * 1, 32 * 1);
	static Screen sc = new Screen(World.maxX, World.maxY);
	protected static boolean gamePause;

	public MainPaint() {
		// TODO Auto-generated constructor stub
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
			if (e.getKeyChar() == 'p') {
				if (gamePause)
					gamePause = false;
				else
					gamePause = true;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	};
	
	static public PointerInfo pi;

	static public MouseListener ml = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Point p = new Point(sc.convertRealXY2gridXY(e.getPoint()));
			System.out.println(p);
			if (w.getCellState(p.x, p.y) == false)
				w.setCellState(p.x, p.y, true);
			else
				w.setCellState(p.x, p.y, false);

		}

	};

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
				g.setColor(Color.black);
				;
				g.drawLine(sc.convertRealXY2gridXY(getMousePosition()).x, sc.convertRealXY2gridXY(getMousePosition()).y, sc.convertRealXY2gridXY(getMousePosition()).x, sc.convertRealXY2gridXY(getMousePosition()).y);
			}
	}

	public static void main(String[] args) throws InterruptedException {

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
			Thread.sleep(30);

		}

	}

}
