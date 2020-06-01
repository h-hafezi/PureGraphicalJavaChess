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

    ImageIcon blackIcon = new ImageIcon("src/resources/Pictures/pieces/br.png");
    ImageIcon whiteIcon = new ImageIcon("src/resources/Pictures/pieces/wr.png");

    @Override
    public ImageIcon getIcon() {
        if (this.isWhite()) {
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }

}
