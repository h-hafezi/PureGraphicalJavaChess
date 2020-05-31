package Game.Piece;

import Game.Colour;

import javax.swing.*;

public abstract class Piece {
    protected Colour colour;
    protected boolean isKilled;
    protected boolean hasMoved;

    public Piece(Colour colour) {
        this.colour = colour;
        this.isKilled = false;
        hasMoved = false;
    }

    public abstract Icon getIcon();

    public boolean isWhite() {
        return colour.equals(Colour.WHITE);
    }

    public boolean isBlack() {
        return colour.equals(Colour.BLACK);
    }

    public Colour getColour() {
        return colour;
    }

    public String toString() {
        String string = "";
        if (this.isWhite()) {
            string += "White ";
        } else {
            string += "Black ";
        }
        if (this instanceof King) {
            string += "King";
        } else if (this instanceof Bishop) {
            string += "Bishop";
        } else if (this instanceof Knight) {
            string += "Knight";
        } else if (this instanceof Pawn) {
            string += "Pawn";
        } else if (this instanceof Queen) {
            string += "Queen";
        } else if (this instanceof Rook) {
            string += "Rook";
        }
        return string;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
