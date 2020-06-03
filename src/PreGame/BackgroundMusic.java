package PreGame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class BackgroundMusic {
    public static BackgroundMusic backgroundMusic = new BackgroundMusic("src\\resources\\Audio\\13. Jim Brickman - Little Star.mp3");
    public static BackgroundMusic movePiece = new BackgroundMusic("src\\resources\\Audio\\moveWithOutKill.mp3");
    public static BackgroundMusic check = new BackgroundMusic("src\\resources\\Audio\\Ckeck.mp3");
    public static BackgroundMusic checkMate = new BackgroundMusic("src\\resources\\Audio\\checkmate.mp3");
    public static BackgroundMusic killPiece = new BackgroundMusic("src\\resources\\Audio\\kill.mp3");

    private String filename;
    private static Player player;

    Thread playMusic;
    Thread background;

    public BackgroundMusic(String filename) {
        this.filename = filename;
    }

    public void play() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            e.printStackTrace();
        }
    }

    public void start() {
        if (this == backgroundMusic) {
            play();
            background = new Thread(new backgroundMusicThread());
            background.start();
        } else {
            play();
            playMusic = new Thread(new PlayMusic());
            playMusic.start();
        }
    }

    public void stop() {
        close();
        playMusic = null;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

    static class PlayMusic implements Runnable {

        public void run() {
            try {
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class backgroundMusicThread implements Runnable {

        public void run() {
            try {
                player.play();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
