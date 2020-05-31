package Game.Piece;

import Game.Board.Board;
import Game.Colour;

import javax.swing.*;

public class King extends Piece {
    private boolean hasBeenChecked;
    private boolean hasBeenCastled;

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

    public void setHasBeenCastled(boolean hasBeenCastled) {
        this.hasBeenCastled = hasBeenCastled;
    }

    public void setHasBeenChecked(boolean hasBeenChecked) {
        this.hasBeenChecked = hasBeenChecked;
    }

    public boolean hasBeenCastled() {
        return hasBeenCastled;
    }

    public boolean hasBeenChecked() {
        return hasBeenChecked;
    }

}
