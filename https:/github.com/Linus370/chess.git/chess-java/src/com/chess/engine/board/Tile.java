package com.chess.engine.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;

public abstract class Tile {
	
	final int coordinate;
	
	private static final Map<Integer, Empty> EMPTY_TILES_CACHE = createEmptyTiles();
	private static Map<Integer, Empty> createEmptyTiles(){
		final Map<Integer, Empty> emptyTileMap = new HashMap<>();
		for(int i = 0; i < 64; i++) {
			emptyTileMap.put(i, new Empty(i));
		}
		//or 'return emptyTileMap'
		//but then someone can change the emptyTileMap if there is say a bug
		return Collections.unmodifiableMap(emptyTileMap);
	}
	
	//The only method that will be accessed to create a tile whether it be occupied or empty
	//Also found this cool way to do if else statements using a 'question mark' operator :)
	public Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new Occupied(coordinate, piece) : EMPTY_TILES_CACHE.get(coordinate);
	}
	
	private Tile(int coordinate){
		this.coordinate = coordinate;
	}
	
	public boolean TileOccupied() {
		return false;
	}
	
	public abstract Piece getPiece();
	
	public static final class Empty extends Tile{
		private Empty(final int coordinate){
			super(coordinate);
		}
		
		@Override
		public boolean TileOccupied() {
			return false;
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}

	}
	
	public static final class Occupied extends Tile{
		private final Piece thePiece;
		
		private Occupied(int coordinate, final Piece thePiece){
			super(coordinate);
			this.thePiece = thePiece;
		}
		
		@Override
		public boolean TileOccupied() {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.thePiece;
		}

	}
}
