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
    private int white_undo_left;
    private int black_undo_left;
    private String white_username;
    private String black_username;
    private int initial_undo_number;

    public int ceilingOfMoves;

    public Game(int number_of_undo, String white_username, String black_username, int ceilingOfMoves) {
        new Board(true);
        this.white_username = white_username;
        this.black_username = black_username;
        this.black_undo_left = number_of_undo;
        this.white_undo_left = number_of_undo;
        this.initial_undo_number = number_of_undo;
        selected_tile = null;
        this.turn = Colour.WHITE;
        game = this;
        if (ceilingOfMoves <= 0) {
            this.ceilingOfMoves = Integer.MAX_VALUE;
        } else {
            this.ceilingOfMoves = ceilingOfMoves;
        }
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
        if (!hasGiveUp) {
            isFinished = false;
            if (Move.getAllMoves().size() != 0) {
                Move move = Move.getLastMove();
                if (move.hasEnPassant()) {
                    move.getEnPassantTile().setPiece(move.getEnPassantPiece());
                }
                move.getToTile().setPiece(move.getPieceKilled());
                move.getFromTile().setPiece(move.getPieceMoved());
                selected_tile = null;
                next_turn();
                Move.getAllMoves().remove(Move.getAllMoves().size() - 1);

                if (isBlackTurn()) {
                    black_undo_left--;
                } else {
                    white_undo_left--;
                }
                return true;
            }
            return false;
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
        white_undo_left = initial_undo_number;
        black_undo_left = initial_undo_number;
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

    public void win(Colour colour) {

    }

    public void draw() {

    }

    public void giveUp() {
        isFinished = false;
        muteness = false;
        selected_tile = null;
    }


}
