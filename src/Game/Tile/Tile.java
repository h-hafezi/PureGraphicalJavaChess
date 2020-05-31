package Game.Tile;

import Game.Board.Board;
import Game.Colour;
import Game.Game;
import Game.Move.Move;
import Game.Move.MoveValidness;
import Game.Piece.King;
import Game.Piece.Piece;

import java.util.ArrayList;

public class Tile {
    private final Colour colour;
    private Piece piece;

    public Tile(Colour colour, Piece piece) {
        this.colour = colour;
        this.piece = piece;
    }

    public Tile(Tile tile) {
        this.colour = tile.getColour();
        this.piece = tile.getPiece();
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Colour getColour() {
        return colour;
    }

    public Colour getPieceColour() {
        return piece.getColour();
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this == Board.getCurrentBoard().getTileFromCoordination(i, j)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getY() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this == Board.getCurrentBoard().getTileFromCoordination(i, j)) {
                    return j;
                }
            }
        }
        return -1;
    }

    public boolean containsBlackKing() {
        return piece != null && (piece instanceof King) && piece.isBlack();
    }

    public boolean containsWhiteKing() {
        return piece != null && (piece instanceof King) && piece.isWhite();
    }

    public boolean containsKing() {
        return containsBlackKing() || containsWhiteKing();
    }

    public ArrayList<Tile> getPossibleTiles() {
        ArrayList<Tile> arrayList = new ArrayList<>();
        if (this.getPiece() == null) {
            return arrayList;
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Tile tile = Board.getCurrentBoard().getTileFromCoordination(i, j);
                    if (tile == this) {
                        continue;
                    }
                    if (MoveValidness.isAuthentic(this, tile)) {
                        Move m = new Move(this, tile);
                        if (!m.isInvalid()) {
                            arrayList.add(tile);
                        }
                        Game.game.imaginaryUndo();
                    }
                }
            }

        }
        return arrayList;

    }

    public String toString() {
        String abc = "ABCDEFGHI";
        return abc.substring(getX(), getX() + 1) + +(8 - this.getY());
    }
}

