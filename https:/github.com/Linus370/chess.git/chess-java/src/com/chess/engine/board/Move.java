package com.chess.engine.board;
import com.chess.engine.pieces.Piece;

public abstract class Move {
	final Board board;
	final Piece pieceMoved;
	final int destinationCoordinate;
	
	private Move(Board board, Piece pieceMoved, int destinationCoordinate){
		this.board = board;
		this.pieceMoved = pieceMoved;
		this.destinationCoordinate = destinationCoordinate;
	}
	
	public static final class MajorMove extends Move {
		public MajorMove(Board board, Piece pieceMoved, int destinationCoordinate){
			super(board, pieceMoved, destinationCoordinate);
		}
	}
	
	public static final class AttackMove extends Move {
		final Piece attackedPiece;
		
		public AttackMove(Board board, Piece pieceMoved, int destinationCoordinate, Piece attackedPiece){
			super(board, pieceMoved, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}
	}
}
