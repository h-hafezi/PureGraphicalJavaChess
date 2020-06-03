package Game.Move;

import Game.Board.Board;
import Game.Game;
import Game.Piece.*;
import Game.Tile.Tile;

import java.util.ArrayList;

public class Move {
    private static ArrayList<Move> allMoves = new ArrayList<>();
    private boolean EnPassantBool = false;
    private Piece EnPassantPiece = null;
    private Tile EnPassantTile = null;
    private Piece pieceMoved;
    private Tile fromTile;
    private Tile toTile;
    private Piece pieceKilled;
    private boolean isInvalid = false;
    private Castling castling = null;
    private boolean blackKingCheck = false;
    private boolean whiteKingCheck = false;

    public Move(Tile fromTile, Tile toTile) {
        this.pieceMoved = fromTile.getPiece();
        this.fromTile = fromTile;
        this.toTile = toTile;
        this.pieceKilled = toTile.getPiece();

        if ((pieceMoved instanceof Pawn) && toTile.getX() != fromTile.getX() && toTile.isEmpty()) {
            EnPassantBool = true;
            this.EnPassantPiece = getLastMove().getToTile().getPiece();
            this.EnPassantTile = getLastMove().getToTile();
            getLastMove().getToTile().setPiece(null);
        }

        toTile.setPiece(fromTile.getPiece());
        fromTile.setPiece(null);
        Game.game.next_turn();
        if (Game.game.isWhiteTurn()) {
            if (Check.isBlackKingChecked()) {
                isInvalid = true;
            }
        } else if (Game.game.isBlackTurn()) {
            if (Check.isWhiteKingChecked()) {
                isInvalid = true;
            }
        }

        allMoves.add(this);

        if (Check.isBlackKingChecked()) {
            this.blackKingCheck = true;
        } else if (Check.isWhiteKingChecked()) {
            this.whiteKingCheck = true;
        }
    }

    public Move(Tile fromTile, Tile toTile, boolean flg) {
        this.pieceMoved = fromTile.getPiece();
        this.fromTile = fromTile;
        this.toTile = toTile;
        this.pieceKilled = toTile.getPiece();

        if ((pieceMoved instanceof Pawn) && toTile.getX() != fromTile.getX() && toTile.isEmpty()) {
            EnPassantBool = true;
            this.EnPassantPiece = getLastMove().getToTile().getPiece();
            this.EnPassantTile = getLastMove().getToTile();
            getLastMove().getToTile().setPiece(null);
        }

        if ((pieceMoved instanceof King) && (Math.abs(fromTile.getX() - toTile.getX()) >= 2)) {
            this.castling = new Castling(this);
            castling.moveCastle();
        }

        toTile.setPiece(fromTile.getPiece());
        fromTile.setPiece(null);
        Game.game.next_turn();
        if (Game.game.isWhiteTurn()) {
            if (Check.isBlackKingChecked()) {
                isInvalid = true;
            }
        } else if (Game.game.isBlackTurn()) {
            if (Check.isWhiteKingChecked()) {
                isInvalid = true;
            }
        }

        allMoves.add(this);

        if (Check.isBlackKingChecked()) {
            this.blackKingCheck = true;
        } else if (Check.isWhiteKingChecked()) {
            this.whiteKingCheck = true;
        }
    }

    public Tile getFromTile() {
        return fromTile;
    }

    public Tile getToTile() {
        return toTile;
    }

    public Piece getPieceKilled() {
        return pieceKilled;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public static ArrayList<Move> getAllMoves() {
        return allMoves;
    }

    public static Move getLastMove() {
        return allMoves.get(allMoves.size() - 1);
    }

    public String toString() {
        String string = pieceMoved.toString() + " from " + fromTile.toString() + " to " + toTile.toString();
        if (this.getPieceKilled() != null) {
            string += ", killed " + getPieceKilled().toStringShortFormatted();
            return string;
        } else if (this.isCastling()) {
            return string + ", castling";
        } else if (this.hasEnPassant()) {
            return string + ", EnPassent";
        } else {
            return string + ", no killing";
        }
    }

    public boolean checkTransform() {
        if (pieceMoved instanceof Pawn) {
            return toTile.getY() == 7 || toTile.getY() == 0;
        }
        return false;
    }

    //we put has been transformed true for exceptional cases castling etc, in fact it's a matter of precaution

    public void transform(String piece) {
        if (checkTransform()) {
            switch (piece) {
                case "Bishop":
                    toTile.setPiece(new Bishop(toTile.getPieceColour()));
                    toTile.getPiece().setHasMoved(true);
                    break;
                case "Queen":
                    toTile.setPiece(new Queen(toTile.getPieceColour()));
                    toTile.getPiece().setHasMoved(true);
                    break;
                case "Knight":
                    toTile.setPiece(new Knight(toTile.getPieceColour()));
                    toTile.getPiece().setHasMoved(true);
                    break;
                case "Rook":
                    toTile.setPiece(new Rook(toTile.getPieceColour()));
                    toTile.getPiece().setHasMoved(true);
                    break;
            }
        }
    }

    public boolean hasEnPassant() {
        return EnPassantBool;
    }

    public Piece getEnPassantPiece() {
        return EnPassantPiece;
    }

    public Tile getEnPassantTile() {
        return EnPassantTile;
    }

    public boolean isInvalid() {
        return isInvalid;
    }

    public boolean isBlackKingCheck() {
        return blackKingCheck;
    }

    public boolean isWhiteKingCheck() {
        return whiteKingCheck;
    }

    public boolean isCastling() {
        return castling != null;
    }

    public Castling getCastling() {
        return castling;
    }
}
