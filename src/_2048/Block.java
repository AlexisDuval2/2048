
//=============================================================
//-------------------------------------------------------------
// Description: model for a given block that moves around in
// the game grid
//-------------------------------------------------------------
//=============================================================

package _2048;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class Block {

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private int value;
	private Point location;

	//----------------------------------------------
	// Constructor
	//----------------------------------------------
	public Block() {
		value = generateBlockValue();
		int randomIndex = generateRandomInt(0, AvailableSpaces.list().size());
		location = AvailableSpaces.list().get(randomIndex);
	}

	//----------------------------------------------
	// generateBlockValue method
	//----------------------------------------------
	public static int generateBlockValue() {

		int value = -1;

		int randomInt = generateRandomInt(0, 10);
		if (randomInt < 9) {value = 2;}
		else {value = 4;}

		return value;
	}

	//----------------------------------------------
	// generateRandomInt method
	//----------------------------------------------
	public static int generateRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	//----------------------------------------------
	// getters
	//----------------------------------------------
	public int value() {return value;}
	public Point location() {return location;}

	//----------------------------------------------
	// setters
	//----------------------------------------------
	public void setValue(int value) {this.value = value;}
	public void setLocation(Point location) {this.location = location;}
}
