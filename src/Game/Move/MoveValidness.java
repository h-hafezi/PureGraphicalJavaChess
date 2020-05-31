package Game.Move;

import Game.Board.Board;
import Game.Piece.*;
import Game.Tile.Tile;

import static java.lang.Math.*;

public class MoveValidness {

    private static Board board = Board.getCurrentBoard();

    public static boolean isAuthentic(Tile t1, Tile t2) {
        return isValid(t1.getX(), t1.getY(), t2.getX(), t2.getY(), false);
    }

    public static boolean isValid(int fromX, int fromY, int toX, int toY, boolean forCheck) {
        Tile fromTile = board.getTiles()[fromX][fromY];
        Tile toTile = board.getTileFromCoordination(toX, toY);
        Piece piece = fromTile.getPiece();
        if (piece == null) {
            return false;
        }

        //kings cannot be killed

        if (toTile.containsKing() && !forCheck) {
            return false;
        }

        //cannot kill piece of same colour

        else if (toTile.getPiece() != null) {
            if (toTile.getPiece().getColour().equals(piece.getColour())) {
                return false;
            }
        }

        //cannot move to the same spot

        else if (fromX == toX && fromY == toY) {
            return false;
        }

        if (piece instanceof King) {
            return isMoveValidForKing(fromX, fromY, toX, toY);
        } else if (piece instanceof Bishop) {
            return isMoveValidForBishop(fromX, fromY, toX, toY);
        } else if (piece instanceof Knight) {
            return isMoveValidForKnight(fromX, fromY, toX, toY);
        } else if (piece instanceof Rook) {
            return isMoveValidForRook(fromX, fromY, toX, toY);
        } else if (piece instanceof Pawn) {
            return isMoveValidForPawn(fromX, fromY, toX, toY);
        } else if (piece instanceof Queen) {
            return isMoveValidForQueen(fromX, fromY, toX, toY);
        }
        return false;
    }

    private static boolean isMoveValidForKing(int fromX, int fromY, int toX, int toY) {
        return abs(fromX - toX) <= 1 && abs(fromY - toY) <= 1;
    }

    private static boolean isMoveValidForBishop(int fromX, int fromY, int toX, int toY) {
        if (abs(fromX - toX) == abs(fromY - toY)) {
            if (((fromX - toX) * (fromY - toY)) > 0) {
                for (int i = min(fromX, toX) + 1; i < max(fromX, toX); i++) {
                    if (!board.getTileFromCoordination(i, (min(fromY, toY) + (i - min(fromX, toX)))).isEmpty()) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = min(fromX, toX) + 1; i < max(fromX, toX); i++) {
                    if (!board.getTileFromCoordination(i, (Math.max(fromY, toY) - (i - Math.min(fromX, toX)))).isEmpty()) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    private static boolean isMoveValidForRook(int fromX, int fromY, int toX, int toY) {
        if (fromX == toX) {
            for (int i = min(fromY, toY) + 1; i < max(fromY, toY); i++) {
                Tile tile = board.getTileFromCoordination(fromX, i);
                if (!tile.isEmpty()) {
                    return false;
                }
            }
            return true;
        } else if (fromY == toY) {
            for (int i = min(fromX, toX) + 1; i < max(fromX, toX); i++) {
                Tile tile = board.getTileFromCoordination(i, fromY);
                if (!tile.isEmpty()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }

    private static boolean isMoveValidForPawn(int fromX, int fromY, int toX, int toY) {

        Tile fromTile = board.getTileFromCoordination(fromX, fromY);
        Tile toTile = board.getTileFromCoordination(toX, toY);
        Piece piece = fromTile.getPiece();

        if (piece.isBlack()) {
            if (fromY - toY >= 0)
                return false;
        } else if (piece.isWhite()) {
            if (fromY - toY <= 0)
                return false;
        }

        if (fromX == toX) {
            if (abs(fromY - toY) == 1) {
                return toTile.isEmpty();
            } else if (abs(fromY - toY) == 2 && ((piece.isWhite() && fromY == 6) || (piece.isBlack() && fromY == 1))) {
                return toTile.isEmpty() && board.getTileFromCoordination((fromX), (fromY + toY) / 2).isEmpty();
            } else {
                return false;
            }
        } else if (abs(toX - fromX) == 1) {
            if (!EnPassant(fromX, fromY)) {
                return abs(fromY - toY) == 1 && !toTile.isEmpty();
            } else {
                return abs(fromY - toY) == 1 && toTile.isEmpty() && toX == Move.getLastMove().getToTile().getX();
            }
        } else {
            return false;
        }
    }

    private static boolean isMoveValidForQueen(int fromX, int fromY, int toX, int toY) {
        return (isMoveValidForBishop(fromX, fromY, toX, toY) || isMoveValidForRook(fromX, fromY, toX, toY));
    }

    private static boolean isMoveValidForKnight(int fromX, int fromY, int toX, int toY) {

        return (abs(fromX - toX) == 1 && (abs(fromY - toY)) == 2) || (abs(fromX - toX) == 2 && (abs(fromY - toY)) == 1);
    }

    public static boolean EnPassant(int x, int y) {
        if (Move.getAllMoves().size() >= 2) {
            Move m = Move.getAllMoves().get(Move.getAllMoves().size() - 1);
            if (m.getPieceMoved() instanceof Pawn) {
                if (abs(m.getToTile().getY() - m.getFromTile().getY()) == 2) {
                    return m.getToTile().getY() == y && abs(m.getToTile().getX() - x) == 1;
                }
            }
        }
        return false;
    }

    public static boolean castlingForBlackKing() {
        King king = King.getBlackKing();
        assert king != null;
        if (!king.hasBeenChecked() && !king.hasMoved()) {
            for (Tile tile : Rook.getNotCapturedBlackRooksTiles()) {
                if (!tile.getPiece().hasMoved()) {
                    for (int i = min(4, tile.getX()) + 1; i < max(4, tile.getX()); i++) {

                    }
                }
            }
        }
        return false;
    }
}
