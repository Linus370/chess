package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;

public abstract class Tile {
	
	final int coordinate;
	
	private static final Map<Integer, Empty> EMPTY_TILES = createEmptyTiles();
	private static Map<Integer, Empty> createEmptyTiles(){
		final Map<Integer, Empty> emptyTileMap = new HashMap<>();
		for(int i = 0; i < 64; i++) {
			emptyTileMap.put(i, new Empty(i));
		}
		
		return emptyTileMap;
	}
	
	public Tile createATile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new Occupied(coordinate, piece) : EMPTY_TILES.get(coordinate);
	}
	
	private Tile(int coordinate){
		this.coordinate = coordinate;
	}
	
	public boolean TileOccupied() {
		return false;
	}

	public abstract boolean occupied();
	
	public abstract Piece getPiece();
	
	public static final class Empty extends Tile{
		Empty(final int coordinate){
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

		@Override
		public boolean occupied() {
			return false;
		}
	}
	
	public static final class Occupied extends Tile{
		private final Piece thePiece;
		
		Occupied(int coordinate, Piece thePiece){
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

		@Override
		public boolean occupied() {
			return false;
		}
	}
}
