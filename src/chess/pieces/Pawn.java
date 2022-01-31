package chess.pieces;
import chess.ChessMatch;
import boardgame.*;
import chess.*;

public class Pawn extends ChessPiece {


	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		if (getColor() == Color.WHITE) {
			// Check 1 front tile
			p.setValues(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// Check 2 front tile + if its the first pawn move so it can be moved 2 tiles
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// Check diagonal tiles and check if there is a piece or not so it can move.
			
			// LEFT DIAGONAL
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// RIGHT DIAGONAL
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// #specialmove en passant white
			if (position.getRow() == 3) {
					Position left = new Position(position.getRow(), position.getColumn() - 1);
					if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
						matrix[left.getRow() - 1][left.getColumn()] = true;
					}
					Position right = new Position(position.getRow(), position.getColumn() + 1);
					if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
						matrix[right.getRow() - 1][right.getColumn()] = true;
					}
			}	
		}
		else {
			// Check 1 front tile
			p.setValues(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// Check 2 front tile + if its the first pawn move so it can be moved 2 tiles
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// Check diagonal tiles and check if there is a piece or not so it can move.
			
			// LEFT DIAGONAL
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			
			// RIGHT DIAGONAL
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				matrix[p.getRow()][p.getColumn()] = true;
			}
			// #specialmove en passant black
			if (position.getRow() == 4) {
					Position left = new Position(position.getRow(), position.getColumn() - 1);
					if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
						matrix[left.getRow() + 1][left.getColumn()] = true;
					}
					Position right = new Position(position.getRow(), position.getColumn() + 1);
					if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
						matrix[right.getRow() + 1][right.getColumn()] = true;
					}
			}			
			
		}
		return matrix;
	}
}
