package gol;

import java.awt.Point;

import javax.swing.JPanel;

public class Main extends JPanel {
	private static final long serialVersionUID = 1L;
	static World w = new World(5,5);
	static Screen sc = new Screen(5,5);

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		w.printWorldOldState();
		w.calcWorld();
		w.click();
		System.out.println(sc.getCellCenter(new Point(0,0)));
		System.out.println(sc.getCellCenter(new Point(1,1)));
		
		w.printWorldOldState();
		w.calcWorld();
		w.click();

		w.printWorldOldState();
		w.calcWorld();
		w.click();
	
		w.printWorldOldState();
		w.calcWorld();
		w.click();
		
		w.printWorldOldState();
		w.calcWorld();
		w.click();
	}

}

