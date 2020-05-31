package Game.Board;

import Game.Colour;
import Game.Move.Move;
import Game.Piece.*;
import Game.Tile.Tile;

public class Board {
    private static Board currentBoard;
    private Tile[][] tiles = new Tile[8][8];

    public Board(boolean flg) {
        if (flg) {
            this.initialise();
            currentBoard = this;
        } else {
            this.initialise();
        }
    }


    public void initialise() {

        tiles[0][0] = new Tile(Colour.WHITE, new Rook(Colour.BLACK));
        tiles[1][0] = new Tile(Colour.BLACK, new Knight(Colour.BLACK));
        tiles[2][0] = new Tile(Colour.WHITE, new Bishop(Colour.BLACK));
        tiles[3][0] = new Tile(Colour.BLACK, new Queen(Colour.BLACK));
        tiles[4][0] = new Tile(Colour.WHITE, new King(Colour.BLACK));
        tiles[5][0] = new Tile(Colour.BLACK, new Bishop(Colour.BLACK));
        tiles[6][0] = new Tile(Colour.WHITE, new Knight(Colour.BLACK));
        tiles[7][0] = new Tile(Colour.BLACK, new Rook(Colour.BLACK));

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                tiles[i][1] = new Tile(Colour.WHITE, new Pawn(Colour.BLACK));
            } else {
                tiles[i][1] = new Tile(Colour.BLACK, new Pawn(Colour.BLACK));
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                if ((i + j) % 2 == 0) {
                    tiles[i][j] = new Tile(Colour.WHITE, null);
                } else {
                    tiles[i][j] = new Tile(Colour.BLACK, null);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                tiles[i][6] = new Tile(Colour.WHITE, new Pawn(Colour.WHITE));
            } else {
                tiles[i][6] = new Tile(Colour.BLACK, new Pawn(Colour.WHITE));
            }
        }

        tiles[0][7] = new Tile(Colour.BLACK, new Rook(Colour.WHITE));
        tiles[1][7] = new Tile(Colour.WHITE, new Knight(Colour.WHITE));
        tiles[2][7] = new Tile(Colour.BLACK, new Bishop(Colour.WHITE));
        tiles[3][7] = new Tile(Colour.WHITE, new Queen(Colour.WHITE));
        tiles[4][7] = new Tile(Colour.BLACK, new King(Colour.WHITE));
        tiles[5][7] = new Tile(Colour.WHITE, new Bishop(Colour.WHITE));
        tiles[6][7] = new Tile(Colour.BLACK, new Knight(Colour.WHITE));
        tiles[7][7] = new Tile(Colour.WHITE, new Rook(Colour.WHITE));
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public static Board getCurrentBoard() {
        return currentBoard;
    }

    public static void setCurrentBoard(Board board) {
        currentBoard = board;
    }

    public Tile getTileFromCoordination(int x, int y) {
        return tiles[x][y];
    }

    public static int getNumberOfPiecesLeft() {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (currentBoard.tiles[i][j].getPiece() != null) {
                    result++;
                }
            }
        }
        return result;
    }
}
