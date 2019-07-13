
//=============================================================
//-------------------------------------------------------------
// Description: class that generates main GUI for game
//-------------------------------------------------------------
//=============================================================

package _2048;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame {

	//----------------------------------------------
	// constants
	//----------------------------------------------
	private static final long serialVersionUID = 1L;

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private static JLayeredPane layeredPane;
	private static JPanel gameBgGrid;
	private static JPanel gameGrid;

	//----------------------------------------------
	// main
	//----------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setResizable(false);
//					frame.setLocationRelativeTo(null);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//----------------------------------------------
	// Vue class
	//----------------------------------------------
	public GUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("2048");
		setLocation(0,0);
		setSize(Const.surfaceWidth, Const.surfaceHeight);
		setBackground(Const.surfaceBgColor);

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		setContentPane(layeredPane);

		gameBgGrid = new JPanel();
		gameBgGrid.setLayout(null);
		gameBgGrid.setLocation((Const.surfaceWidth - Const.gameGridWidth) / 2 - 2, 200);
		gameBgGrid.setSize(Const.gameGridWidth, Const.gameGridHeight);
		gameBgGrid.setBackground(Const.gameGridBgColor);
		layeredPane.add(gameBgGrid);

		gameGrid = new JPanel();
		gameGrid.setLayout(null);
		gameGrid.setLocation((Const.surfaceWidth - Const.gameGridWidth) / 2 - 2, 200);
		gameGrid.setSize(Const.gameGridWidth, Const.gameGridHeight);
		gameGrid.setOpaque(false);
		layeredPane.add(gameGrid);

		drawBgGrid();

		AvailableSpaces.initialize();
		PlayerBlocks.initialize();
		PlayerBlocks.list().add(new Block());
		for (Block b : PlayerBlocks.list()) {gameGrid.add(b.image());}

		layeredPane.setLayer(gameBgGrid, 1);
		layeredPane.setLayer(gameGrid, 2);

		Listener l = new Listener();
		addKeyListener(l);
	}

	//----------------------------------------------
	// drawBgGrid method
	//----------------------------------------------
	private static void drawBgGrid() {
		for (int lineNb = 1; lineNb <= 4; lineNb++) {
			for (int columnNb = 1; columnNb <= 4; columnNb++) {
				gameBgGrid.add(generateABgBlock(columnNb, lineNb));
			}
		}
	}

	//----------------------------------------------
	// generateABgBlock method
	//----------------------------------------------
	private static JLabel generateABgBlock(int columnNb, int lineNb) {

		JLabel result = new JLabel();

		result.setLocation(convertBlockLocation(new Point(columnNb, lineNb)));
		result.setSize(Const.blockSize, Const.blockSize);
		result.setBackground(Const.blockBgColor);
		result.setFont(Const.mainFont);
		result.setOpaque(true);

		return result;
	}

	//----------------------------------------------
	// convertBlockLocation method
	//----------------------------------------------
	public static Point convertBlockLocation(Point blockLocation) {

		Point visualLocation = new Point();

		int x = (blockLocation.x - 1) * (Const.blockSize + 5) + 5;
		int y = (blockLocation.y - 1) * (Const.blockSize + 5) + 5;
		visualLocation.setLocation(x, y);

		return visualLocation;
	}

	//----------------------------------------------
	// Listener class
	//----------------------------------------------
	public class Listener implements KeyListener {

		//----------------------------------------------
		// keyPressed method
		//----------------------------------------------
		@Override
		public void keyPressed(KeyEvent ke) {}

		//----------------------------------------------
		// keyReleased method
		//----------------------------------------------
		@Override
		public void keyReleased(KeyEvent ke) {

			boolean pressedUp = ke.getKeyCode() == KeyEvent.VK_UP;
			boolean pressedDown = ke.getKeyCode() == KeyEvent.VK_DOWN;
			boolean pressedLeft = ke.getKeyCode() == KeyEvent.VK_LEFT;
			boolean pressedRight = ke.getKeyCode() == KeyEvent.VK_RIGHT;

			boolean validKey = pressedUp || pressedDown || pressedLeft || pressedRight;

			if (validKey) {
				
				boolean makeANewBlock = false;

				for (Block b : PlayerBlocks.list()) {

					Point oldLocation = new Point();
					oldLocation.x = b.location().x;
					oldLocation.y = b.location().y;

					if (pressedUp && b.canMoveUp) {b.location().y = 1;}
					else if (pressedDown && b.canMoveDown) {b.location().y = 4;}
					else if (pressedLeft && b.canMoveLeft) {b.location().x = 1;}
					else if (pressedRight && b.canMoveRight) {b.location().x = 4;}

					boolean blockHasMoved = (pressedUp && b.canMoveUp)
							|| (pressedDown && b.canMoveDown)
							|| (pressedLeft && b.canMoveLeft)
							|| (pressedRight && b.canMoveRight);

					if (blockHasMoved) {
						makeANewBlock = makeANewBlock || true;
						AvailableSpaces.update(oldLocation, b.location());
					}

					b.image().setLocation(convertBlockLocation(b.location()));
					gameGrid.add(b.image());
				}
				
				if (makeANewBlock) {
					Block newBlock = new Block();
					PlayerBlocks.list().addElement(newBlock);
					gameGrid.add(newBlock.image());
				}
				
				layeredPane.setLayer(gameBgGrid, 1);
				layeredPane.setLayer(gameGrid, 2);
			}
		}

		//----------------------------------------------
		// keyTyped method
		//----------------------------------------------
		@Override
		public void keyTyped(KeyEvent ke) {}
	}
}
