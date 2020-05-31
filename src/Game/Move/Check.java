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
                    wk.setHasBeenChecked(true);
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
                    bk.setHasBeenChecked(true);
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

    public static boolean checkStalemate() {
        Game game = Game.game;

        if (checkTheCeiling()) {
            return true;
        }

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

    public static boolean checkTheCeiling() {
        return Game.game.ceilingOfMoves < Move.getAllMoves().size();
    }

}
