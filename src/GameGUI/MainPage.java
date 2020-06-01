package GameGUI;

import Game.Move.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainPage extends JFrame implements ActionListener {
    static DefaultListModel<String> demoList;
    static JLabel message;

    private static Game.Game game;
    private JPanel board = new JPanel(new BorderLayout(3, 3));
    private JPanel gameBoard = new JPanel();
    private JPanel move = new JPanel();
    private JPanel total = new JPanel();
    private Color backGroundColour = new Color(255, 255, 102);

    public static JButton[][] chessBoardSquares = new JButton[8][8];

    private static final String COLS = "ABCDEFGH";

    public final void initializeBoard() {

        gameBoard.setBackground(backGroundColour);
        JPanel chessBoard = new JPanel(new GridLayout(0, 9));
        board.add(chessBoard);

        // create the chess board squares

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(new Color(68, 181, 211));
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fill the chess board

        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                if (jj == 0) {
                    JLabel jLabel = new JLabel("" + (8 - ii), SwingConstants.CENTER);
                    jLabel.setOpaque(true);
                    jLabel.setBackground(backGroundColour);
                    chessBoard.add(jLabel);
                }
                chessBoard.add(chessBoardSquares[jj][ii]);
            }
        }

        //adding the label in the grid

        JLabel label = new JLabel("");
        chessBoard.add(label);

        //setting the background colour

        label.setOpaque(true);
        label.setBackground(backGroundColour);

        // fill the top row

        for (int ii = 0; ii < 8; ii++) {
            JLabel jLabel = new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER);
            jLabel.setOpaque(true);
            jLabel.setBackground(backGroundColour);
            chessBoard.add(jLabel);
        }

    }

    public final void initializeGameBoard() {

        gameBoard.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        message = new JLabel("");
        message.setText("         " + "white should start the game");

        JLabel junk1 = new JLabel("     ");
        JLabel junk2 = new JLabel("     ");
        JLabel junk3 = new JLabel("     ");
        JLabel junk4 = new JLabel("     ");
        JLabel junk5 = new JLabel("     ");

        gbc.gridy = 1;
        gameBoard.add(junk1, gbc);

        gbc.gridy = 2;
        gameBoard.add(junk2, gbc);

        gbc.gridy = 3;
        gameBoard.add(message, gbc);

        gbc.gridy = 4;
        gameBoard.add(junk3, gbc);

        gbc.gridy = 5;
        gameBoard.add(junk4, gbc);

        gbc.gridy = 6;
        gameBoard.add(junk5, gbc);

        gbc.gridy = 8;
        gameBoard.add(board, gbc);
    }

    public final void initializeMove() {
        move.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        //adding the icon
        JLabel icon = new JLabel("");
        icon.setSize(300, 300);
        icon.setIcon(new ImageIcon("src/resources/Pictures/chess-pngrepo-com (2).png"));
        move.add(icon, gbc);

        JLabel junk1 = new JLabel("           ");
        gbc.gridy = 2;
        move.add(junk1, gbc);

        JLabel junk2 = new JLabel("           ");
        gbc.gridy = 3;
        move.add(junk2, gbc);

        JLabel title = new JLabel("                  moves                  ");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 5;
        move.add(title, gbc);

        JLabel junk3 = new JLabel("           ");
        gbc.gridy = 8;
        move.add(junk3, gbc);

        JLabel junk4 = new JLabel("           ");
        gbc.gridy = 8;
        move.add(junk4, gbc);

        gbc.gridy = 10;
        gbc.weightx = 1;
        gbc.weighty = 11;

        //adding the Jlist in order to show the moves

        demoList = new DefaultListModel<>();
        demoList.addElement("  double click on move to undo");
        demoList.addElement("  ");
        JList<String> list = new JList<>(demoList);

        //adding action listener the list so when the move is double clicked, then it will be undone

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                //when it's double clicked

                if (e.getClickCount() == 2) {

                    //asking to make sure whether to undo or not

                    int a = JOptionPane.showConfirmDialog(MainPage.this, "are you sure to want to undo to this move?");
                    if (a == JOptionPane.YES_OPTION) {
                        game.undo(Move.getAllMoves().size() + 2 - list.getSelectedIndex());
                        GameManager gameManager = new GameManager();
                        demoList.removeAllElements();
                        demoList.addElement("  double click on move to undo");
                        demoList.addElement("  ");
                        int i = 0;
                        for (Move m : Move.getAllMoves()) {
                            i++;
                            if (i <= 9) {
                                demoList.addElement(" " + i + " - " + m.toString());
                            } else {
                                demoList.addElement("" + i + " - " + m.toString());
                            }
                        }
                        message.setText("undone successfully");
                        gameManager.updateBoard();
                        gameManager.deselect();
                    }
                }
            }
        };

        list.addMouseListener(mouseListener);

        //adding the J list to a JScrollPane and making the setting

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(backGroundColour);
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setBounds(0, 0, 250, 400);

        move.add(scrollPane, gbc);
        move.setBackground(backGroundColour);
    }

    public final void initializeMenuBar() {
        GameManager gameManager = new GameManager();

        //adding the menu items

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("      game menu      ");
        JMenu muteMenu = new JMenu("      sound    ");
        JMenuItem mute = new JMenuItem("    mute    ");
        JMenuItem resetGame = new JMenuItem(" reset the game ");
        JMenuItem undo = new JMenuItem(" undo ");
        JMenuItem giveUp = new JMenuItem(" give up ");
        JMenuItem exit = new JMenuItem(" exit ");

        menu.add(resetGame);
        menu.add(undo);
        menu.add(giveUp);
        muteMenu.add(mute);
        menu.add(exit);

        //then adding action listeners

        resetGame.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(MainPage.this, "are you sure to want to reset?");
            if (a == JOptionPane.YES_OPTION) {
                game.undo(Move.getAllMoves().size());
                demoList.removeAllElements();
                demoList.addElement("  double click on move to undo");
                demoList.addElement("  ");
                int i = 0;
                message.setText("          start again");
                gameManager.updateBoard();
                gameManager.deselect();
            }
            game.resetTheGame();
        });

        undo.addActionListener(e -> {
            if (game.undo()) {
                setMessage("undo successfully, now it's again " + game.getTurn().toString() + " turn");
                demoList.removeAllElements();
                demoList.addElement("  double click on move to undo");
                demoList.addElement("  ");
                int i = 0;
                for (Move m : Move.getAllMoves()) {
                    i++;
                    if (i <= 9) {

                        demoList.addElement(" " + i + " - " + m.toString());
                    } else {
                        demoList.addElement("" + i + " - " + m.toString());
                    }
                }
                gameManager.updateBoard();
                gameManager.deselect();

            } else {
                setMessage("there's no move to undo, it's now " + game.getTurn().toString() + " turn");
            }
        });

        giveUp.addActionListener(e -> {
            if (game.isFinished()) {
                int a = JOptionPane.showConfirmDialog(this, "Are you sure to want to give up");
                if (a == JOptionPane.YES_OPTION) {
                    gameManager.giveUp();
                }
            } else {
                setMessage("the game is already finished");
            }
        });

        exit.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure to want to exit the game?!");
            if (a == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        mute.addActionListener(e -> {
            game.setMuteness(!game.getMuteness());
            if (game.getMuteness()) {
                mute.setText("  unmute  ");
            } else {
                mute.setText("    mute    ");
            }
        });

        menuBar.add(menu);
        menuBar.add(muteMenu);
        this.setJMenuBar(menuBar);
    }

    public final void initializeTotalPage() {
        total.setLayout(new FlowLayout());
        total.setBackground(backGroundColour);
        total.add(gameBoard);
        total.add(move);
    }

    public MainPage(Game.Game game) {

        //setting image and icon of J frame

        this.setTitle("chess");
        ImageIcon img = new ImageIcon("src/resources/Pictures/chess_piece_king.png");
        this.setIconImage(img.getImage());

        MainPage.game = game;
        this.setSize(5500, 3500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationByPlatform(true);

        initializeBoard();
        initializeGameBoard();
        initializeMove();
        initializeTotalPage();
        initializeMenuBar();

        this.getContentPane().add(total);
        this.pack();
        this.setVisible(true);

        GameManager GameManager = new GameManager();
        GameManager.updateBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoardSquares[i][j].addActionListener(this);
            }
        }

        message.setFont(new Font("Arial", Font.PLAIN, 30));
    }

    public static Game.Game getGame() {
        return game;
    }

    public static void setMessage(String text) {
        message.setText("         " + text);
    }

    //setting action event for tiles, we have already set it up for menu items and our components

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int xxx = 0;
        int yyy = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (actionEvent.getSource() == chessBoardSquares[i][j]) {
                    xxx = i;
                    yyy = j;
                    GameManager method = new GameManager();
                    method.select(xxx, yyy);
                }
            }
        }
    }

}
