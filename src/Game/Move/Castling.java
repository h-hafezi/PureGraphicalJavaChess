package Game.Move;

import Game.Board.Board;
import Game.Piece.King;
import Game.Piece.Piece;
import Game.Piece.Rook;
import Game.Tile.Tile;

enum CastlingType {
    BLACK_RIGHT, BLACK_LEFT, WHITE_LEFT, WHITE_RIGHT;
}

public class Castling {

    public CastlingType castlingType;

    //we would specify its type first

    public Castling(Move move) {
        if (move.getToTile() == Board.getCurrentBoard().getTiles()[1][0]) {
            this.castlingType = CastlingType.BLACK_LEFT;
        } else if (move.getToTile() == Board.getCurrentBoard().getTiles()[6][0]) {
            this.castlingType = CastlingType.BLACK_RIGHT;
        } else if (move.getToTile() == Board.getCurrentBoard().getTiles()[1][7]) {
            this.castlingType = CastlingType.WHITE_LEFT;
        } else if (move.getToTile() == Board.getCurrentBoard().getTiles()[6][7]) {
            this.castlingType = CastlingType.WHITE_RIGHT;
        }
    }

    //for black king we should check the two coordination (0,0) and (7,0)

    public static boolean castlingForBlackKingLeft() {

        King king = King.getBlackKing();
        assert king != null;

        if (King.hasBlackBeenCastled()) {
            return false;
        }

        if (Board.getCurrentBoard().getTiles()[4][0].getPiece() != king) {
            return false;
        }

        //first making sure it hasn't been checked nor moved

        if (!King.hasBlackKingBeenChecked() && !king.hasMoved()) {

            //getting the piece at the position we want

            Piece piece1 = Board.getCurrentBoard().getTiles()[0][0].getPiece();

            if (piece1 instanceof Rook) {

                Rook rook = (Rook) piece1;
                if (!rook.hasMoved()) {
                    for (int i = 1; i <= 3; i++) {
                        if (Check.isTileCheckedByWhites(i, 0) || !Board.getCurrentBoard().getTiles()[i][0].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean castlingForWhiteKingLeft() {

        King king = King.getWhiteKing();
        assert king != null;

        if (King.hasWhiteBeenCastled()) {
            return false;
        }


        if (Board.getCurrentBoard().getTiles()[4][7].getPiece() != king) {
            return false;
        }

        //first making sure it hasn't been checked nor moved

        if (!King.hasWhiteKingBeenChecked() && !king.hasMoved()) {

            //getting the piece at the position we want

            Piece piece1 = Board.getCurrentBoard().getTiles()[0][7].getPiece();

            if (piece1 instanceof Rook) {

                Rook rook = (Rook) piece1;
                if (!rook.hasMoved()) {
                    for (int i = 1; i <= 3; i++) {
                        if (Check.isTileCheckedByBlacks(i, 7) || !Board.getCurrentBoard().getTiles()[i][7].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean castlingForBlackKingRight() {

        King king = King.getBlackKing();
        assert king != null;

        if (King.hasBlackBeenCastled()) {
            return false;
        }

        if (Board.getCurrentBoard().getTiles()[4][0].getPiece() != king) {
            return false;
        }

        //first making sure it hasn't been checked nor moved

        if (!King.hasBlackKingBeenChecked() && !king.hasMoved()) {

            //getting the piece at the position we want

            Piece piece1 = Board.getCurrentBoard().getTiles()[7][0].getPiece();

            if (piece1 instanceof Rook) {

                Rook rook = (Rook) piece1;
                if (!rook.hasMoved()) {
                    for (int i = 5; i <= 6; i++) {
                        if (Check.isTileCheckedByWhites(i, 0) || !Board.getCurrentBoard().getTiles()[i][0].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean castlingForWhiteKingRight() {

        King king = King.getWhiteKing();
        assert king != null;

        if (King.hasWhiteBeenCastled()) {
            return false;
        }

        if (Board.getCurrentBoard().getTiles()[4][7].getPiece() != king) {
            return false;
        }

        //first making sure it hasn't been checked nor moved

        if (!King.hasWhiteKingBeenChecked() && !king.hasMoved()) {

            //getting the piece at the position we want

            Piece piece1 = Board.getCurrentBoard().getTiles()[7][7].getPiece();

            if (piece1 instanceof Rook) {

                Rook rook = (Rook) piece1;

                if (!rook.hasMoved()) {
                    for (int i = 5; i <= 6; i++) {
                        if (Check.isTileCheckedByBlacks(i, 7) || !Board.getCurrentBoard().getTiles()[i][7].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    //according to type it moves the rook, the king is moved with with Move object

    public void moveCastle() {
        if (castlingType.equals(CastlingType.BLACK_LEFT)) {
            Piece piece = Board.getCurrentBoard().getTiles()[0][0].getPiece();
            Board.getCurrentBoard().getTiles()[0][0].setPiece(null);
            Board.getCurrentBoard().getTiles()[2][0].setPiece(piece);
        } else if (castlingType.equals(CastlingType.BLACK_RIGHT)) {
            Piece piece = Board.getCurrentBoard().getTiles()[7][0].getPiece();
            Board.getCurrentBoard().getTiles()[7][0].setPiece(null);
            Board.getCurrentBoard().getTiles()[5][0].setPiece(piece);
        } else if (castlingType.equals(CastlingType.WHITE_LEFT)) {
            Piece piece = Board.getCurrentBoard().getTiles()[0][7].getPiece();
            Board.getCurrentBoard().getTiles()[0][7].setPiece(null);
            Board.getCurrentBoard().getTiles()[2][7].setPiece(piece);
        } else if (castlingType.equals(CastlingType.WHITE_RIGHT)) {
            Piece piece = Board.getCurrentBoard().getTiles()[7][7].getPiece();
            Board.getCurrentBoard().getTiles()[7][7].setPiece(null);
            Board.getCurrentBoard().getTiles()[5][7].setPiece(piece);
        }
    }

    //when move is a type of Castling then, we undo it with this method rather than the normal method

    public void undo() {
        Board board = Board.getCurrentBoard();
        if (this.castlingType.equals(CastlingType.WHITE_LEFT)) {
            Rook rook = (Rook) board.getTiles()[2][7].getPiece();
            board.getTiles()[4][7].setPiece(King.getWhiteKing());
            board.getTiles()[0][7].setPiece(rook);
            board.getTiles()[1][7].setPiece(null);
            board.getTiles()[2][7].setPiece(null);
        } else if (this.castlingType.equals(CastlingType.WHITE_RIGHT)) {
            Rook rook = (Rook) board.getTiles()[5][7].getPiece();
            board.getTiles()[4][7].setPiece(King.getWhiteKing());
            board.getTiles()[7][7].setPiece(rook);
            board.getTiles()[6][7].setPiece(null);
            board.getTiles()[5][7].setPiece(null);
        } else if (this.castlingType.equals(CastlingType.BLACK_LEFT)) {
            Rook rook = (Rook) board.getTiles()[2][0].getPiece();
            board.getTiles()[4][0].setPiece(King.getBlackKing());
            board.getTiles()[0][0].setPiece(rook);
            board.getTiles()[1][0].setPiece(null);
            board.getTiles()[2][0].setPiece(null);
        } else if (this.castlingType.equals(CastlingType.BLACK_RIGHT)) {
            Rook rook = (Rook) board.getTiles()[5][0].getPiece();
            board.getTiles()[4][0].setPiece(King.getWhiteKing());
            board.getTiles()[7][0].setPiece(rook);
            board.getTiles()[6][0].setPiece(null);
            board.getTiles()[5][0].setPiece(null);
        }
    }

}
