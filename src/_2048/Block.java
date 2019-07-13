
//=============================================================
//-------------------------------------------------------------
// Description: model for a given block that moves around in
// the game grid
//-------------------------------------------------------------
//=============================================================

package _2048;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Block {

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private int value;
	private Point location;
	private JLabel image;
	public boolean canMoveUp;
	public boolean canMoveDown;
	public boolean canMoveLeft;
	public boolean canMoveRight;

	//----------------------------------------------
	// Constructor
	//----------------------------------------------
	public Block() {

		value = generateBlockValue();

		int randomIndex = generateRandomInt(0, AvailableSpaces.list().size());
		location = AvailableSpaces.list().get(randomIndex);
		AvailableSpaces.update(null, location);
		
		image = generateBlockImage();

		canMoveUp = location.y > 1;
		canMoveDown = location.y < 4;
		canMoveLeft = location.x > 1;
		canMoveRight = location.x < 4;
	}

	//----------------------------------------------
	// generateBlockValue method
	//----------------------------------------------
	private static int generateBlockValue() {

		int value = -1;

		int randomInt = generateRandomInt(0, 10);
		if (randomInt < 9) {value = 2;}
		else {value = 4;}

		return value;
	}

	//----------------------------------------------
	// generateRandomInt method
	//----------------------------------------------
	private static int generateRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	//----------------------------------------------
	// generateBlockImage method
	//----------------------------------------------
	private JLabel generateBlockImage() {
		
		JLabel image = new JLabel(String.valueOf(value));
		
		image.setLocation(GUI.convertBlockLocation(location));
		image.setSize(Const.blockSize, Const.blockSize);
		image.setBackground(Const.playerBlockBgColor);
		image.setFont(Const.mainFont);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setOpaque(true);
		
		return image;
	}

	//----------------------------------------------
	// getters
	//----------------------------------------------
	public int value() {return value;}
	public Point location() {return location;}
	public JLabel image() {return image;}

	//----------------------------------------------
	// setters
	//----------------------------------------------
	public void setValue(int value) {this.value = value;}
}
