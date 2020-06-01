import Game.Game;
import PreGame.LoginMenu;

public class main {
    public static void main(String[] args) {
        new LoginMenu();
        new GameGUI.MainPage(new Game(1, null, null,100));
    }
}
