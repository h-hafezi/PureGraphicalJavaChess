package Game.Piece;

import Game.Colour;

import javax.swing.*;
import java.net.URL;

public class Bishop extends Piece {
    public Bishop(Colour colour) {
        super(colour);
    }

    ImageIcon blackIcon =new ImageIcon("src/resources/Pictures/pieces/bb.png");
    ImageIcon whiteIcon =new ImageIcon("src/resources/Pictures/pieces/wb.png");

    @Override
    public ImageIcon getIcon() {
        if(this.isWhite()){
            return whiteIcon;
        } else {
            return blackIcon;
        }
    }
}
