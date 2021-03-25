package com.chess.engine.pieces;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;

import com.chess.engine.Side;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Bishop extends Piece{
	private final static int[] LEGAL_MOVE_COORDINATE = {-9, -7, 9, 7};
	
	Bishop(int piecePosition, Side side){
		super(piecePosition, side);
	}
	
	@Override
	public Collection<Move> legalMoves(final Board board){
		final List<Move> listOfLegalMoves = new ArrayList<>();
		for(int possibleMove : LEGAL_MOVE_COORDINATE) {
			int destinationCoordinate = this.piecePosition;
			while(BoardUtils.validCoordinate(destinationCoordinate)) {
				destinationCoordinate += possibleMove;
				if(BoardUtils.validCoordinate(destinationCoordinate)) {
					final Tile destinationCoordinateTile = board.getTile(destinationCoordinate);
					if(!destinationCoordinateTile.TileOccupied()) {
						listOfLegalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
					}
					else {
						final Piece pieceOnTile = destinationCoordinateTile.getPiece();
						final Side pieceSide = pieceOnTile.getSide();
						
						if(pieceSide != this.pieceSide) {
							listOfLegalMoves.add(new Move.AttackMove(board, this, destinationCoordinate, pieceOnTile));
						}
						break;
					}
				}
			}
		}
		
		return Collections.unmodifiableCollection(listOfLegalMoves);
	}
}
