
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocation(0,0);
		setSize(surfaceWidth, surfaceHeight);

		surface = new JPanel();
		surface.setBackground(Color.GREEN);
		surface.setLayout(null);
		setContentPane(surface);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLocation(0,0);
		panel.setSize(400,400);
		panel.setLayout(null);
		surface.add(panel);
		
		block01 = new JLabel("1");
		block01.setHorizontalTextPosition(SwingConstants.CENTER);
		block01.setHorizontalAlignment(SwingConstants.CENTER);
		block01.setAlignmentX(Component.CENTER_ALIGNMENT);
		block01.setBounds(0, 0, 100, 100);
		block01.setFont(new Font("Tahoma", Font.BOLD, 25));
		block01.setOpaque(true);
		panel.add(block01);
	}

}
