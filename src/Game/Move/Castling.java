package Game.Move;

import Game.Piece.King;
import Game.Piece.Rook;
import Game.Tile.Tile;

public class Castling {

    private Tile fromTileRook;
    private Tile toTileRook;
    private Tile fromTileKing;
    private Tile toTileKing;
    private King king;
    private Rook rook;
    private Move move;

    public Castling( Tile fromTileKing, Tile toTileKing, Move move) {
        this.fromTileKing = fromTileKing;
        this.toTileKing = toTileKing;
        this.move = move;
    }

    public void undo(){
        fromTileRook.setPiece(rook);
        fromTileKing.setPiece(king);
        toTileRook.setPiece(null);
        toTileKing.setPiece(null);
    }


}
