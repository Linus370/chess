package com.chess.engine.pieces;
import java.util.Collection;

import com.chess.engine.Side;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

public abstract class Piece {
	protected final int piecePosition;
	protected final Side pieceSide;
	
	Piece(final int piecePosition, final Side side) {
		this.piecePosition = piecePosition;
		this.pieceSide = side;
	}
	
	public Side getSide() {
		return this.pieceSide;
	}
	
	public abstract Collection<Move> legalMoves(final Board board);
}
