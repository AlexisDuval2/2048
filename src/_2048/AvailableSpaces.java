
//=============================================================
//-------------------------------------------------------------
// Description: class that updates the list of available
// spaces where new blocks can appear
//-------------------------------------------------------------
//=============================================================

package _2048;

import java.awt.Point;
import java.util.Vector;

public class AvailableSpaces {

	//----------------------------------------------
	// variables
	//----------------------------------------------
	private static Vector<Point> list_s;

	//----------------------------------------------
	// initialize method
	//----------------------------------------------
	public static void initialize() {

		list_s = new Vector<Point>();

		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <=4; j++) {
				list_s.add(new Point(j, i));
			}
		}
	}

	//----------------------------------------------
	// update method
	//----------------------------------------------
	public static void update(Point oldLoction, Point newLocation) {
		list_s.remove(newLocation);
		list_s.add(oldLoction);
	}
	
	//----------------------------------------------
	// getter
	//----------------------------------------------
	public static Vector<Point> list() {return list_s;}
}
