
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
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	//----------------------------------------------
	// constants
	//----------------------------------------------
	private static final long serialVersionUID = 1L;
	private static final int surfaceWidth = 625;
	private static final int surfaceHeight = 625;
	private static final Color surfaceBgColor = Color.BLUE;
	private static final int panelWidth = 425;
	private static final int panelHeight = 425;
	private static final Color panelBgColor = Color.ORANGE;
	private static final int blockSize = 100;
	private static final Color blockBgColor = Color.BLACK;
	private static final Color blockColor = Color.GRAY;
	private static final Font mainFont = new Font("Tahoma", Font.BOLD, 25);

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private JPanel surface;
	private JPanel panel;

	//----------------------------------------------
	// main
	//----------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				}
				catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	//----------------------------------------------
	// Vue class
	//----------------------------------------------
	public GUI() {

		setTitle("2048");
		setLocation(0,0);
		setSize(surfaceWidth, surfaceHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		surface = new JPanel();
		surface.setBackground(surfaceBgColor);
		surface.setLayout(null);
		setContentPane(surface);
		
		panel = new JPanel();
		panel.setLocation((surfaceWidth - panelWidth) / 2, 100);
		panel.setSize(panelWidth, panelHeight);
		panel.setBackground(panelBgColor);
		panel.setLayout(null);
		surface.add(panel);
		
		int blockNb = 1;
		for (int lineNb = 1; lineNb <= 4; lineNb++) {
			for (int columnNb = 1; columnNb <= 4; columnNb++) {
				panel.add(drawABlock(Integer.toString(blockNb), lineNb, columnNb));
				blockNb++;
			}
		}
		
		Listener l = new Listener();
		addKeyListener(l);
	}
	
	//----------------------------------------------
	// designABlock method
	//----------------------------------------------
	private static JLabel drawABlock(String blockNb, int lineNb, int columnNb) {
		
		JLabel block = new JLabel(blockNb);
		
		lineNb--;
		columnNb--;
		
		block.setLocation(columnNb * (blockSize + 5) + 5, lineNb * (blockSize + 5) + 5);
		block.setSize(blockSize, blockSize);
		block.setBackground(blockBgColor);
		block.setForeground(blockColor);
		block.setHorizontalAlignment(SwingConstants.CENTER);
		block.setFont(mainFont);
		block.setOpaque(true);
		
		return block;
	}
	
	//----------------------------------------------
	// Listener class
	//----------------------------------------------
	public class Listener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent ke) {}

		@Override
		public void keyReleased(KeyEvent ke) {
			
			if (ke.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("up");
			}
			else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("down");
			}
			else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("left");
			}
			else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("right");
			}
		}

		@Override
		public void keyTyped(KeyEvent ke) {}
	}
}
