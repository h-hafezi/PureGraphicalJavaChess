package Game.Move;

import Game.Board.Board;
import Game.Game;
import Game.Piece.King;
import Game.Piece.Piece;
import Game.Tile.Tile;

import java.util.ArrayList;

import static Game.Move.MoveValidness.*;

public class Check {

    private static Board board = Board.getCurrentBoard();

    public static boolean isWhiteKingChecked() {
        int kingX = 0;
        int kingY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if (tile.containsWhiteKing()) {
                    kingX = i;
                    kingY = j;
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if ((i == kingX && j == kingY) || tile.isEmpty()) {
                    continue;
                }
                if (!tile.isEmpty()) {
                    Piece piece = tile.getPiece();
                    if (piece.isWhite()) {
                        continue;
                    }
                }
                if (isValid(i, j, kingX, kingY, true)) {
                    King wk = King.getWhiteKing();
                    assert wk != null;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isBlackKingChecked() {
        int kingX = 0;
        int kingY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if (tile.containsBlackKing()) {
                    kingX = i;
                    kingY = j;
                }

            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if ((i == kingX && j == kingY) || tile.isEmpty()) {
                    continue;
                }
                if (!tile.isEmpty()) {
                    Piece piece = tile.getPiece();
                    if (piece.isBlack()) {
                        continue;
                    }
                }
                if (isValid(i, j, kingX, kingY, true)) {
                    King bk = King.getWhiteKing();
                    assert bk != null;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isBlackCheckMate() {
        ArrayList<Tile> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTiles()[i][j];
                if (!tile.isEmpty()) {
                    if (tile.getPiece().isBlack()) {
                        arrayList.addAll(tile.getPossibleTiles());
                    }
                }
            }
        }
        return isBlackKingChecked() && arrayList.isEmpty() && Game.game.isBlackTurn();
    }

    public static boolean isWhiteCheckMate() {
        ArrayList<Tile> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTiles()[i][j];
                if (!tile.isEmpty()) {
                    if (tile.getPiece().isWhite()) {
                        arrayList.addAll(tile.getPossibleTiles());
                    }
                }
            }
        }
        return isWhiteKingChecked() && arrayList.isEmpty() && Game.game.isWhiteTurn();
    }

    //checks for draw

    public static boolean checkStalemate() {
        Game game = Game.game;

        ArrayList<Tile> arrayList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTiles()[i][j];
                if (!tile.isEmpty()) {
                    if (tile.getPiece().isWhite()) {
                        arrayList.addAll(tile.getPossibleTiles());
                    }
                }
            }
        }

        ArrayList<Tile> arrayList2 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTiles()[i][j];
                if (!tile.isEmpty()) {
                    if (tile.getPiece().isBlack()) {
                        arrayList2.addAll(tile.getPossibleTiles());
                    }
                }
            }
        }

        //the cases where there are only two kings left which is draw

        if (Board.getNumberOfPiecesLeft() == 2) {
            return true;
        }

        if (game.isWhiteTurn()) {
            return (game.isWhiteTurn() && arrayList.isEmpty() && !isWhiteKingChecked());
        } else if (game.isBlackTurn()) {
            return (game.isBlackTurn() && arrayList2.isEmpty() && !isBlackKingChecked());
        }

        return true;
    }

    //two methods below are used for castling to make sure that castling is all right and tile between rook and king aren't checked

    public static boolean isTileCheckedByBlacks(int x, int y) {
        int kingX = 0;
        int kingY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if (tile.containsWhiteKing()) {
                    kingX = i;
                    kingY = j;
                }
            }
        }

        Board board = Board.getCurrentBoard();
        new Move(board.getTileFromCoordination(kingX, kingY), board.getTiles()[x][y]);
        if (isWhiteKingChecked()) {
            Game.game.imaginaryUndo();
            return true;
        } else {
            Game.game.imaginaryUndo();
            return false;
        }
    }

    public static boolean isTileCheckedByWhites(int x, int y) {
        int kingX = 0;
        int kingY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if (tile.containsBlackKing()) {
                    kingX = i;
                    kingY = j;
                }
            }
        }

        Board board = Board.getCurrentBoard();
        new Move(board.getTileFromCoordination(kingX, kingY), board.getTiles()[x][y]);
        if (isBlackKingChecked()) {
            Game.game.imaginaryUndo();
            return true;
        } else {
            Game.game.imaginaryUndo();
            return false;
        }
    }
}
