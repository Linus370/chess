package com.chess.engine.board;

public class BoardUtils {

	public static final boolean[] FIRST = initilize(0);
	public static final boolean[] SECOND = initilize(1);
	public static final boolean[] SEVEN = initilize(6);
	public static final boolean[] EIGHT = initilize(7);
	
	public static final int NUM_OF_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	
	//Not needed...here to make sure no one instantiates me
	private BoardUtils() {
		throw new RuntimeException("No.");
	}
	
	
	public static boolean validCoordinate(int coordinate) {
		return coordinate >= 0 && coordinate < 64;
	}
	
	/*Method abstracted from Software Architecture & Design: Java Chess Programming Video #6 Introduction to the Move Class
	Determines if a piece is actually on a specific column using boolean array assigning each tile on each appropriate column with true
	When a piece position is inputed it will see if the tile is TRUE thus meaning it is in that column
	When researching for this project I tried to avoid using 2D arrays because they are annoying and difficult to grasp at times
	This video series has helped me tremendously in visualizing a 2D array without actually using a 2D array to construct a chess board
	Obvious this can easily be done with a 2D array as well*/
	private static boolean[] initilize(int number) {
		final boolean[] column = new boolean[NUM_OF_TILES];
		do {
			column[number] = true;
			number += NUM_TILES_PER_ROW;
		} while(number < NUM_OF_TILES);
			return column;
	}

}
