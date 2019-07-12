
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
import java.awt.Component;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	//----------------------------------------------
	// constants
	//----------------------------------------------
	private static final long serialVersionUID = 1L;
	private static final int surfaceWidth = 625;
	private static final int surfaceHeight = 625;
	private static final Color surfaceBgColor = Color.GREEN;
	private static final int panelWidth = 400;
	private static final int panelHeight = 400;
	private static final Color panelBgColor = Color.BLACK;
	private static final int blockSize = 100;
	private static final Font mainFont = new Font("Tahoma", Font.BOLD, 25);

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private JPanel surface;
	private JPanel panel;
	private JLabel block01;

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
		panel.setBackground(panelBgColor);
		panel.setLocation((surfaceWidth - panelWidth) / 2, 100);
		panel.setSize(panelWidth, panelHeight);
		panel.setLayout(null);
		surface.add(panel);
		
		block01 = new JLabel("1");
		block01.setHorizontalTextPosition(SwingConstants.CENTER);
		block01.setHorizontalAlignment(SwingConstants.CENTER);
		block01.setAlignmentX(Component.CENTER_ALIGNMENT);
		block01.setLocation(0,0);
		block01.setSize(blockSize, blockSize);
		block01.setFont(mainFont);
		block01.setOpaque(true);
		panel.add(block01);
	}

}
