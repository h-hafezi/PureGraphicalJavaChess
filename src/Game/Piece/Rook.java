package Game.Piece;

import Game.Board.Board;
import Game.Colour;
import Game.Tile.Tile;

import javax.swing.*;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Colour colour) {
        super(colour);
    }

    ImageIcon blackIcon = new ImageIcon("src/main/resources/Pictures/pieces/br.png");
    ImageIcon whiteIcon = new ImageIcon("src/main/resources/Pictures/pieces/wr.png");

    @Override
    public ImageIcon getIcon() {
        if (this.isWhite()) {
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }

    public static ArrayList<Tile> getNotCapturedBlackRooksTiles() {
        ArrayList<Tile> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = Board.getCurrentBoard().getTileFromCoordination(i, j);
                if (tile.getPiece() == null) {
                    continue;
                }
                if (tile.getPiece() instanceof Rook && tile.getPiece().isBlack()) {
                    arrayList.add(tile);
                }
            }
        }
        return arrayList;
    }

    public static ArrayList<Tile> getNotCapturedWhiteRooksTiles() {
        ArrayList<Tile> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = Board.getCurrentBoard().getTileFromCoordination(i, j);
                if (tile.getPiece() == null) {
                    continue;
                }
                if (tile.getPiece() instanceof Rook && tile.getPiece().isWhite()) {
                    arrayList.add(tile);
                }
            }
        }
        return arrayList;
    }


}
