
//=============================================================
//-------------------------------------------------------------
// Description: class that generates main GUI for game
//-------------------------------------------------------------
//=============================================================

package _2048;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	//----------------------------------------------
	// constants
	//----------------------------------------------
	private static final long serialVersionUID = 1L;
	private static final Font mainFont = new Font("Tahoma", Font.BOLD, 25);
	private static final int surfaceWidth = 625;
	private static final int surfaceHeight = 650;
	private static final Color surfaceBgColor = Color.WHITE;
	private static final int gameGridWidth = 425;
	private static final int gameGridHeight = 425;
	private static final Color gameGridBgColor = Color.ORANGE;
	private static final int blockSize = 100;
	private static final Color blockBgColor = Color.GRAY;
	private static final Color playerBlockBgColor = Color.YELLOW;

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private JLayeredPane layeredPane;
	private JPanel gameBgGrid;
	private JPanel gameGrid;
	private JLabel playerBlock;
	private int playerBlockLineNb = 1;
	private int playerBlockColumnNb = 1;

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
					frame.setLocationRelativeTo(null);
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
		setSize(surfaceWidth, surfaceHeight);
		setBackground(surfaceBgColor);

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		setContentPane(layeredPane);

		gameBgGrid = new JPanel();
		gameBgGrid.setLayout(null);
		gameBgGrid.setLocation((surfaceWidth - gameGridWidth) / 2 - 2, 150);
		gameBgGrid.setSize(gameGridWidth, gameGridHeight);
		gameBgGrid.setBackground(gameGridBgColor);
		layeredPane.add(gameBgGrid);

		gameGrid = new JPanel();
		gameGrid.setLayout(null);
		gameGrid.setLocation((surfaceWidth - gameGridWidth) / 2 - 2, 150);
		gameGrid.setSize(gameGridWidth, gameGridHeight);
		gameGrid.setOpaque(false);
		layeredPane.add(gameGrid);
		
		drawBgGrid();

		playerBlock = new JLabel("TEST");
		playerBlock.setLocation(findBlockLocation(1,1));
		playerBlock.setSize(blockSize, blockSize);
		playerBlock.setBackground(playerBlockBgColor);
		playerBlock.setFont(mainFont);
		playerBlock.setHorizontalAlignment(SwingConstants.CENTER);
		playerBlock.setOpaque(true);
		gameGrid.add(playerBlock);
		
		layeredPane.setLayer(gameBgGrid, 1);
		layeredPane.setLayer(gameGrid, 2);
		
		Listener l = new Listener();
		addKeyListener(l);
	}

	//----------------------------------------------
	// drawBgGrid method
	//----------------------------------------------
	private void drawBgGrid() {
		
		for (int lineNb = 1; lineNb <= 4; lineNb++) {
			for (int columnNb = 1; columnNb <= 4; columnNb++) {
				gameBgGrid.add(drawABlock(lineNb, columnNb));
			}
		}
	}

	//----------------------------------------------
	// drawABlock method
	//----------------------------------------------
	private JLabel drawABlock(int lineNb, int columnNb) {

		JLabel block = new JLabel();

		block.setLocation(findBlockLocation(lineNb, columnNb));
		block.setSize(blockSize, blockSize);
		block.setBackground(blockBgColor);
		block.setFont(mainFont);
		block.setOpaque(true);

		return block;
	}

	//----------------------------------------------
	// findBlockLocation method
	//----------------------------------------------
	private Point findBlockLocation(int lineNb, int columnNb) {

		Point point = new Point();

		lineNb--;
		columnNb--;
		int y = lineNb * (blockSize + 5) + 5;
		int x = columnNb * (blockSize + 5) + 5;
		point.setLocation(x, y);

		return point;
	}

	//----------------------------------------------
	// Listener class
	//----------------------------------------------
	public class Listener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent ke) {}

		@Override
		public void keyReleased(KeyEvent ke) {

			boolean pressedUp = ke.getKeyCode() == KeyEvent.VK_UP;
			boolean pressedDown = ke.getKeyCode() == KeyEvent.VK_DOWN;
			boolean pressedLeft = ke.getKeyCode() == KeyEvent.VK_LEFT;
			boolean pressedRight = ke.getKeyCode() == KeyEvent.VK_RIGHT;

			boolean validKey = pressedUp || pressedDown || pressedLeft || pressedRight;

			if (validKey) {
				if (pressedUp) {playerBlockLineNb--;}
				else if (pressedDown) {playerBlockLineNb++;}
				else if (pressedLeft) {playerBlockColumnNb--;}
				else if (pressedRight) {playerBlockColumnNb++;}

				playerBlock.setLocation(findBlockLocation(playerBlockLineNb, playerBlockColumnNb));
			}
		}

		@Override
		public void keyTyped(KeyEvent ke) {}
	}
}
