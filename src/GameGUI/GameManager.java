package GameGUI;

import Game.Board.Board;
import Game.Colour;
import Game.Game;
import Game.Move.Check;
import Game.Move.Move;
import Game.Piece.Piece;
import Game.Tile.Tile;

import javax.swing.*;
import java.awt.*;

import static PreGame.BackgroundMusic.*;

public class GameManager {

    Board board = Board.getCurrentBoard();

    public void addMoveToList(Move m) {
        String string = "";
        if (Move.getAllMoves().size() <= 9) {
            string += " " + Move.getAllMoves().size() + " - ";
        } else {
            string += Move.getAllMoves().size() + " - ";
        }
        string += m.getPieceMoved().toString() + " from ";
        string += m.getFromTile().toString() + " to " + m.getToTile().toString();
        MainPage.demoList.addElement(string);
        MainPage.demoList.addElement("");
    }

    public void giveUp(){
        Game game=Game.game;
        game.giveUp();
        game.next_turn();
        if(game.isBlackTurn()){
            MainPage.setMessage("Black has won ");
        } else {
            MainPage.setMessage("White has won ");
        }
        deselect();
        game.win(game.getTurn());
        game.setFinished();
        updateBoard();
    }

    public void colourSelected(int i, int j) {
        JButton button = MainPage.chessBoardSquares[i][j];
        button.setBackground(new Color(4, 47, 102));
    }

    public void colourPossibleMoves(Tile tile) {
        for (Tile t : tile.getPossibleTiles()) {
            if (t == tile) {
                continue;
            }
            colourPossibleMoves(t.getX(), t.getY());
        }
    }

    public void colourPossibleMoves(int i, int j) {
        JButton button = MainPage.chessBoardSquares[i][j];
        button.setBackground(new Color(51, 204, 51));
    }

    public void colourCheckedKing() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = Board.getCurrentBoard().getTileFromCoordination(i, j);
                if (tile.containsBlackKing() && Check.isBlackKingChecked()) {
                    JButton button = MainPage.chessBoardSquares[i][j];
                    button.setBackground(new Color(200, 0, 50));
                } else if (tile.containsWhiteKing() && Check.isWhiteKingChecked()) {
                    JButton button = MainPage.chessBoardSquares[i][j];
                    button.setBackground(new Color(200, 0, 50));
                }
            }
        }
    }

    public void removeIconImage(int i, int j) {
        JButton button = MainPage.chessBoardSquares[i][j];
        button.setIcon(null);
    }

    public void addIconImage(int i, int j, Piece piece) {
        JButton button = MainPage.chessBoardSquares[i][j];
        button.setIcon(piece.getIcon());
    }

    //it changes to gui board according to the current board

    public void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTileFromCoordination(i, j);
                if (tile.getPiece() != null) {
                    addIconImage(i, j, tile.getPiece());
                } else {
                    removeIconImage(i, j);
                }
            }
        }
    }

    public void select(int i, int j) {

        Game game = MainPage.getGame();
        System.out.println(game.isAnyTileSelected());
        Tile tile = board.getTileFromCoordination(i, j);

        //I don't even know why i added this

        if (i < 0 || j < 0) {
            return;
        }

        //first we check the cases where a piece is already selected

        if (game.isAnyTileSelected()) {

            //if one a piece of same colour is clicked we would deselect

            if (tile.getPiece() != null && tile.getPieceColour().equals(game.getTurn())) {
                MainPage.setMessage("deselected, this is " + game.getTurn().toString() + " turn");
                this.deselect();
            }

            //now if the piece clicked is null or has different colour

            else if (game.get_selected_tile().getPossibleTiles().contains(board.getTileFromCoordination(i, j))) {

                Move m = new Move(game.get_selected_tile(), tile);
                addMoveToList(m);

                deselect();
                updateBoard();
                colourCheckedKing();

                boolean EnPassent = false;

                //checking for checkmate and checkStalemate
                if (game.getTurn().equals(Colour.WHITE)) {
                    MainPage.setMessage("White player moved, now it's Black's turn");
                    if (m.hasEnPassant()) {
                        EnPassent = true;
                        MainPage.setMessage("En Passent just happened");
                        if (!Game.game.getMuteness()) {
                            killPiece.start();
                        }
                    }
                } else {
                    MainPage.setMessage("Black player moved, now it's White's turn");
                    if (m.hasEnPassant()) {
                        EnPassent = true;
                        MainPage.setMessage("En Passent just happened");
                        if (!Game.game.getMuteness()) {
                            killPiece.start();
                        }
                    }
                }

                if (Check.isBlackCheckMate()) {
                    if (!Game.game.getMuteness()) {
                        checkMate.start();
                    }
                    game.setFinished();
                    MainPage.setMessage("White has won");
                } else if (Check.isWhiteCheckMate()) {
                    if (!Game.game.getMuteness()) {
                        checkMate.start();
                    }
                    game.setFinished();
                    MainPage.setMessage("Black has won");
                } else if (Check.checkStalemate()) {
                    if (!Game.game.getMuteness()) {
                        checkMate.start();
                    }
                    game.setFinished();
                    MainPage.setMessage("no one won, it's a draw");

                } else if (!Check.isBlackKingChecked() && !Check.isWhiteKingChecked()) {
                    if (m.getPieceKilled() == null) {
                        if (!Game.game.getMuteness() && !EnPassent) {
                            movePiece.start();
                        }
                    } else {
                        if (!Game.game.getMuteness() && !EnPassent) {
                            killPiece.start();
                        }
                    }
                } else if (Check.isBlackKingChecked() || Check.isWhiteKingChecked()) {
                    if (!Game.game.getMuteness()) {
                        check.start();
                    }
                }

                //checking whether a pawn has reached the end or not and if it has then it can be transformed

                if (m.checkTransform()) {

                    String[] options = new String[]{"Queen", "Bishop", "Knight", "Rook"};
                    int response = JOptionPane.showOptionDialog(null, "what would you change it to?", "transform piece",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (response == 0) {
                        m.transform("Queen");
                    } else if (response == 1) {
                        m.transform("Bishop");
                    } else if (response == 2) {
                        m.transform("Knight");
                    } else if (response == 3) {
                        m.transform("Rook");
                    }
                    updateBoard();
                    colourCheckedKing();

                    //maybe after changing check or checkmate happens so we should consider that too

                    if (Check.isBlackCheckMate()) {
                        if (!Game.game.getMuteness()) {
                            checkMate.start();
                        }
                        game.setFinished();
                        MainPage.setMessage("White has won");
                    } else if (Check.isWhiteCheckMate()) {
                        if (!Game.game.getMuteness()) {
                            checkMate.start();
                        }
                        game.setFinished();
                        MainPage.setMessage("Black has won");
                    } else if (Check.checkStalemate()) {
                        if (!Game.game.getMuteness()) {
                            checkMate.start();
                        }
                        game.setFinished();
                        MainPage.setMessage("no one won, it's a draw");
                    } else if (Check.isBlackKingChecked() || Check.isWhiteKingChecked()) {
                        if (!Game.game.getMuteness()) {
                            check.start();
                        }
                    }

                }

            }
        }

        //now we check the case that no piece is selected

        else {

            //setting message box for some cases

            if (game.isFinished()) {
                if (tile.getPiece() == null) {
                    MainPage.setMessage("select a square with some piece on it!");
                } else if (game.getTurn().equals(tile.getPiece().getColour()) && tile.getPossibleTiles().size() != 0) {
                    MainPage.setMessage("selected successfully");
                } else if (!game.getTurn().equals(tile.getPiece().getColour())) {
                    MainPage.setMessage("you can only select your own pieces");
                }
            }

            //the case of actually selecting a piece


            if (game.isFinished()) {
                if (tile.getPiece() != null && game.getTurn().equals(tile.getPiece().getColour())) {
                    if (tile.getPossibleTiles().size() == 0) {
                        MainPage.setMessage("no it no possible move for that piece");
                        return;
                    }
                    game.select_tile(i, j);
                    colourPossibleMoves(tile);
                    colourSelected(i, j);
                }
            }
        }
    }

    //letting the select piece to be null and getting the chess squares back to their normal colours
    //and the exception is the checked king that has to stay the same

    public void deselect() {
        Game game = MainPage.getGame();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = MainPage.chessBoardSquares[i][j];
                if (button.getBackground().getRed() != 68 && button.getBackground().getRed() != 240) {
                    if ((i + j) % 2 == 0) {
                        button.setBackground(new Color(240, 240, 240));
                    } else {
                        button.setBackground(new Color(68, 181, 211));
                    }
                }
                if (board.getTileFromCoordination(i, j).containsWhiteKing() && Check.isWhiteKingChecked()) {
                    button.setBackground(new Color(200, 0, 50));
                } else if (board.getTileFromCoordination(i, j).containsBlackKing() && Check.isBlackKingChecked()) {
                    button.setBackground(new Color(200, 0, 50));
                }
            }
        }
        game.deselect();
    }

}
