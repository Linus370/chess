package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Side;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Knight extends Piece{

	private final static int[] LEGAL_MOVE_COORDINATE = {-17, -15, -10, -6, 6 , 10, 15, 17};
	
	Knight(int piecePosition, Side side) {
		super(piecePosition, side);
	}
	
	@Override
	public Collection<Move> legalMoves(final Board board){
		final List<Move> listOfLegalMoves = new ArrayList<>();
		for(final int possibleMove : LEGAL_MOVE_COORDINATE) {
			final int destinationCoordinate = this.piecePosition + possibleMove;
			if(BoardUtils.validCoordinate(destinationCoordinate)) {
				if(firstColumnExcluded(this.piecePosition, possibleMove) || secondColumnExcluded(this.piecePosition, possibleMove) || sevenColumnExcluded(this.piecePosition, possibleMove) || eightColumnExcluded(this.piecePosition, possibleMove)) {
					continue;
				}
				final Tile destinationCoordinateTile = board.getTile(destinationCoordinate);
				if(!destinationCoordinateTile.TileOccupied()) {
					listOfLegalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
				}
				else {
					final Piece pieceOnTile = destinationCoordinateTile.getPiece();
					final Side pieceSide = pieceOnTile.getSide();
					
					if(this.pieceSide != pieceSide) {
						listOfLegalMoves.add(new Move.AttackMove(board, this, destinationCoordinate, pieceOnTile));
					}
				}
			}
		}
		
		return Collections.unmodifiableCollection(listOfLegalMoves);
	}
	
	private static boolean firstColumnExcluded(final int piecePosition, final int destinationPosition) {
		return BoardUtils.FIRST[piecePosition] && ((destinationPosition == -17) || (destinationPosition == -10) || (destinationPosition == 6) || (destinationPosition == 15));
	}
	
	private static boolean secondColumnExcluded(final int piecePosition, final int destinationPosition) {
		return BoardUtils.SECOND[piecePosition] && ((destinationPosition == -10) || (destinationPosition == 6));
	}
	
	private static boolean sevenColumnExcluded(final int piecePosition, final int destinationPosition) {
		return BoardUtils.SEVEN[piecePosition] && ((destinationPosition == 10) || (destinationPosition == -6));
	}
	
	private static boolean eightColumnExcluded(final int piecePosition, final int destinationPosition) {
		return BoardUtils.EIGHT[piecePosition] && ((destinationPosition == 17) || (destinationPosition == 10) || (destinationPosition == -6) || (destinationPosition == -15));
	}

}
