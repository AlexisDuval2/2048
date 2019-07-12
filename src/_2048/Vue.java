
package _2048;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class Vue extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel surface;
	private JPanel panel;
	private JLabel block01;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					Vue frame = new Vue();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vue() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocation(0,0);
		setSize(625,625);

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
		block01.setLocation(40,40);
		block01.setSize(100,100);
		block01.setOpaque(true);
		panel.add(block01);
	}

}
