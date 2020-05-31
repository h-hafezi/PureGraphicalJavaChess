package Game.Piece;

import Game.Colour;

import javax.swing.*;

public class Queen extends Piece {

    public  Queen(Colour colour) {
        super(colour);
    }

    ImageIcon blackIcon =new ImageIcon("src/main/resources/Pictures/pieces/bq.png");
    ImageIcon whiteIcon =new ImageIcon("src/main/resources/Pictures/pieces/wq.png");

    @Override
    public ImageIcon getIcon() {
        if(this.isWhite()){
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }
}
