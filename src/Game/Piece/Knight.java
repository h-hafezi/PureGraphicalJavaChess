package Game.Piece;

import Game.Colour;

import javax.swing.*;

public class Knight extends Piece {

    public Knight(Colour colour) {
        super(colour);
    }


    ImageIcon blackIcon =new ImageIcon("src/main/resources/Pictures/pieces/bn.png");
    ImageIcon whiteIcon =new ImageIcon("src/main/resources/Pictures/pieces/wn.png");

    @Override
    public ImageIcon getIcon() {
        if(this.isWhite()){
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }

}
