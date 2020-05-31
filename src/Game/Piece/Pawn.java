package Game.Piece;

import Game.Colour;

import javax.swing.*;

public class Pawn extends Piece {

    public Pawn(Colour colour) {
        super(colour);
    }

    ImageIcon blackIcon =new ImageIcon("src/resources/Pictures/pieces/bp.png");
    ImageIcon whiteIcon =new ImageIcon("src/resources/Pictures/pieces/wp.png");

    @Override
    public ImageIcon getIcon() {
        if(this.isWhite()){
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }
}
