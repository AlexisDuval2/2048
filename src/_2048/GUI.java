
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
	private static final int surfaceHeight = 700;
	private static final Color surfaceBgColor = Color.YELLOW;
	private static final int gameGridWidth = 425;
	private static final int gameGridHeight = 425;
	private static final Color gameGridBgColor = Color.ORANGE;
	private static final int blockSize = 100;
	private static final Color blockBgColor = Color.GRAY;
	private static final Color playerBlockBgColor = Color.WHITE;

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private static JLayeredPane layeredPane;
	private static JPanel gameBgGrid;
	private static JPanel gameGrid;
	private static JLabel testBlockImage;
	private static Block testBlock;

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
		setSize(surfaceWidth, surfaceHeight);
		setBackground(surfaceBgColor);

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		setContentPane(layeredPane);

		gameBgGrid = new JPanel();
		gameBgGrid.setLayout(null);
		gameBgGrid.setLocation((surfaceWidth - gameGridWidth) / 2 - 2, 200);
		gameBgGrid.setSize(gameGridWidth, gameGridHeight);
		gameBgGrid.setBackground(gameGridBgColor);
		layeredPane.add(gameBgGrid);

		gameGrid = new JPanel();
		gameGrid.setLayout(null);
		gameGrid.setLocation((surfaceWidth - gameGridWidth) / 2 - 2, 200);
		gameGrid.setSize(gameGridWidth, gameGridHeight);
		gameGrid.setOpaque(false);
		layeredPane.add(gameGrid);

		drawBgGrid();
		
		AvailableSpaces.initialize();

		testBlock = new Block();
		AvailableSpaces.update(null, testBlock.location());
		System.out.println("-----");
		System.out.println("size of list: " + AvailableSpaces.list().size());
		System.out.println("testBlock location: " + testBlock.location());
		
		testBlockImage = generateAPlayerBlock(testBlock);
		gameGrid.add(testBlockImage);

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
		result.setSize(blockSize, blockSize);
		result.setBackground(blockBgColor);
		result.setFont(mainFont);
		result.setOpaque(true);

		return result;
	}

	//----------------------------------------------
	// convertBlockLocation method
	//----------------------------------------------
	private static Point convertBlockLocation(Point blockLocation) {

		Point visualLocation = new Point();

		int x = (blockLocation.x - 1) * (blockSize + 5) + 5;
		int y = (blockLocation.y - 1) * (blockSize + 5) + 5;
		visualLocation.setLocation(x, y);

		return visualLocation;
	}

	//----------------------------------------------
	// generateAPlayerBlock method
	//----------------------------------------------
	private static JLabel generateAPlayerBlock(Block block) {

		JLabel result = new JLabel();

		result.setLocation(convertBlockLocation(block.location()));
		result.setSize(blockSize, blockSize);
		result.setBackground(playerBlockBgColor);
		result.setFont(mainFont);
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setText(String.valueOf(block.value()));
		result.setOpaque(true);

		return result;
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

			boolean canMoveUp = testBlock.location().y > 1;
			boolean canMoveDown = testBlock.location().y < 4;
			boolean canMoveLeft = testBlock.location().x > 1;
			boolean canMoveRight = testBlock.location().x < 4;
			
			boolean aBlockHasMoved = (pressedUp && canMoveUp)
					|| (pressedDown && canMoveDown)
					|| (pressedLeft && canMoveLeft)
					|| (pressedRight && canMoveRight);

			if (validKey) {
				
				Point oldLocation = new Point();
				oldLocation.x = testBlock.location().x;
				oldLocation.y = testBlock.location().y;
				
				if (pressedUp && canMoveUp) {testBlock.location().y = 1;}
				else if (pressedDown && canMoveDown) {testBlock.location().y = 4;}
				else if (pressedLeft && canMoveLeft) {testBlock.location().x = 1;}
				else if (pressedRight && canMoveRight) {testBlock.location().x = 4;}

				testBlockImage.setLocation(convertBlockLocation(testBlock.location()));
				
				if (aBlockHasMoved) {
					AvailableSpaces.update(oldLocation, testBlock.location());
					Block b = new Block();
					AvailableSpaces.update(null, b.location());
					JLabel j = generateAPlayerBlock(b);
					gameGrid.add(j);
					
					layeredPane.setLayer(gameBgGrid, 1);
					layeredPane.setLayer(gameGrid, 2);
					
					System.out.println("-----");
					System.out.println("size of list: " + AvailableSpaces.list().size());
					System.out.println("testBlock location: " + testBlock.location());
					System.out.println("newBlock location: " + b.location());
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent ke) {}
	}
}
