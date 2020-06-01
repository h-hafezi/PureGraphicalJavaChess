package Game.Piece;

import Game.Board.Board;
import Game.Colour;
import Game.Move.Move;

import javax.swing.*;

public class King extends Piece {

    public King(Colour colour) {
        super(colour);
    }

    ImageIcon blackIcon = new ImageIcon("src/resources/Pictures/pieces/bk.png");
    ImageIcon whiteIcon = new ImageIcon("src/resources/Pictures/pieces/wk.png");

    @Override
    public ImageIcon getIcon() {
        if (this.isWhite()) {
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }

    public static King getBlackKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.getCurrentBoard().getTileFromCoordination(i, j).containsBlackKing()) {
                    return (King) Board.getCurrentBoard().getTileFromCoordination(i, j).getPiece();
                }
            }
        }
        return null;
    }

    public static King getWhiteKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.getCurrentBoard().getTileFromCoordination(i, j).containsWhiteKing()) {
                    return (King) Board.getCurrentBoard().getTileFromCoordination(i, j).getPiece();
                }
            }
        }
        return null;
    }

    //instead of keeping booleans in our object when we want to check whether it has been castled or checked we do with Move.getAllMoves()

    public static boolean hasBlackBeenCastled() {
        for (Move move : Move.getAllMoves()) {
            if (move.isCastling() && move.getPieceMoved().isBlack()) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasWhiteBeenCastled() {
        for (Move move : Move.getAllMoves()) {
            if (move.isCastling() && move.getPieceMoved().isWhite()) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasWhiteKingBeenChecked() {
        for (Move move : Move.getAllMoves()) {
            if (move.isWhiteKingCheck()) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasBlackKingBeenChecked() {
        for (Move move : Move.getAllMoves()) {
            if (move.isBlackKingCheck()) {
                return true;
            }
        }
        return false;
    }
}
