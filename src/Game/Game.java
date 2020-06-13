package Game;

import Game.Board.Board;
import Game.Move.Move;
import Game.Tile.Tile;

public class Game {
    public static Game game;
    private boolean isFinished = false;
    private boolean hasGiveUp = false;
    private boolean muteness = false;
    private Tile selected_tile;
    private Colour turn;
    private int time;
    public int white_left_time;
    public int black_left_time;
    private boolean usingClock = false;

    public Game() {
        new Board(true);
        selected_tile = null;
        this.turn = Colour.WHITE;
        game = this;
    }

    public void next_turn() {
        if (turn.equals(Colour.WHITE)) {
            turn = Colour.BLACK;
        } else {
            turn = Colour.WHITE;
        }
    }

    public void select_tile(int i, int j) {
        selected_tile = Board.getCurrentBoard().getTileFromCoordination(i, j);
    }

    public void deselect() {
        selected_tile = null;
    }

    public boolean isAnyTileSelected() {
        return selected_tile != null;
    }

    public Tile get_selected_tile() {
        return selected_tile;
    }

    public Colour getTurn() {
        return turn;
    }

    public boolean undo() {
            isFinished = false;
            if (Move.getAllMoves().size() != 0) {
                Move move = Move.getLastMove();
                if (move.hasEnPassant()) {
                    move.getEnPassantTile().setPiece(move.getEnPassantPiece());
                }
                if (move.isCastling()) {
                    move.getCastling().undo();
                } else {
                    move.getToTile().setPiece(move.getPieceKilled());
                    move.getFromTile().setPiece(move.getPieceMoved());
                }
                selected_tile = null;
                next_turn();
                Move.getAllMoves().remove(Move.getAllMoves().size() - 1);

                return true;
            }
            return false;
    }

    public void imaginaryUndo() {
        Move move = Move.getLastMove();
        if (move.hasEnPassant()) {
            move.getEnPassantTile().setPiece(move.getEnPassantPiece());
        }

        move.getToTile().setPiece(move.getPieceKilled());
        move.getFromTile().setPiece(move.getPieceMoved());
        next_turn();
        Move.getAllMoves().remove(Move.getAllMoves().size() - 1);
    }


    public void undo(int n) {
        for (int i = 0; i < n; i++) {
            undo();
        }
    }

    public void setMuteness(boolean muteness) {
        this.muteness = muteness;
    }

    public boolean getMuteness() {
        return muteness;
    }

    public void resetTheGame() {
        isFinished = false;
        muteness = false;
        selected_tile = null;
        turn = Colour.WHITE;
    }

    public boolean isBlackTurn() {
        return turn.equals(Colour.BLACK);
    }

    public boolean isWhiteTurn() {
        return turn.equals(Colour.WHITE);
    }

    public boolean isFinished() {
        return !isFinished;
    }

    public void setFinished() {
        this.isFinished = true;
    }

    public void giveUp() {
        isFinished = true;
        muteness = false;
        selected_tile = null;
    }

    public void setUsingClock(boolean usingClock) {
        this.usingClock = usingClock;
    }

    public boolean isUsingClock() {
        return usingClock;
    }

    public void setTime(int time) {
        this.time = time;
        this.black_left_time = time;
        this.white_left_time = time;
    }

    public int getTime() {
        return time;
    }

}
