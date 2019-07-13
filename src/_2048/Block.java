
//=============================================================
//-------------------------------------------------------------
// Description: model for a given block that moves around in
// the game grid
//-------------------------------------------------------------
//=============================================================

package _2048;

import java.awt.Point;
import java.util.Vector;
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
	public Block(Vector<Point> listOfEmptySpaces) {
		value = generateBlockValue();
		int randomIndex = generateRandomInt(0, listOfEmptySpaces.size());
		location = listOfEmptySpaces.get(randomIndex);
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
}
