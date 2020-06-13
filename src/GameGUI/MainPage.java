package GameGUI;

import Game.Move.Move;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
    private JPanel clock = new JPanel();
    private JPanel total = new JPanel();
    public static JButton blackTurn;
    public static JButton whiteTurn;
    public static JScrollPane scrollPane;
    public static String blackTime = "\n" + "---------black--------" + "\n" + "\n" + "       " + "20:20:20" + "    " + "\n" + "\n";
    public static String whiteTime = "\n" + "---------white--------" + "\n" + "\n" + "       " + "20:20:20" + "    " + "\n" + "\n";

    private Color backGroundColour = new Color(255, 255, 102);
    public static JButton[][] chessBoardSquares = new JButton[8][8];
    private static final String COLS = "ABCDEFGH";
    public static MainPage mainPage = null;

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
        demoList.addElement("   " + "double click on move to undo");
        demoList.addElement("    ");
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
                        demoList.addElement("   " + "double click on move to undo");
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

        scrollPane = new JScrollPane(list);
        Border border = new LineBorder(new Color(1, 100, 200), 2, true);
        scrollPane.setBorder(border);
        scrollPane.setBackground(backGroundColour);
        scrollPane.setPreferredSize(new Dimension(200, 284));
        scrollPane.setBounds(0, 0, 200, 360);

        move.add(scrollPane, gbc);
        move.setBackground(backGroundColour);
    }

    public final void initializeMenuBar() {
        GameManager gameManager = new GameManager();

        //adding the menu items

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("      game menu      ");
        JMenu muteMenu = new JMenu("      sound      ");
        JMenu clockMenu = new JMenu("            clock            ");

        JMenuItem mute = new JMenuItem("       mute     ");
        JMenuItem resetGame = new JMenuItem(" reset the game ");
        JMenuItem undo = new JMenuItem(" undo ");
        JMenuItem exit = new JMenuItem(" exit ");
        JMenuItem clock = new JMenuItem("       use a clock       ");

        menu.add(resetGame);
        menu.add(undo);
        muteMenu.add(mute);
        menu.add(exit);
        clockMenu.add(clock);

        //then adding action listeners

        resetGame.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(MainPage.this, "are you sure to want to reset?");
            if (a == JOptionPane.YES_OPTION) {

                if (game.isUsingClock()) {
                    game.setTime(game.getTime());
                }

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

            if (game.isUsingClock()) {
                setMessage("you cannot undo while playing with clock");
                return;
            }

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
                        demoList.addElement("" + i + "- " + m.toString());
                    }
                }
                gameManager.updateBoard();
                gameManager.deselect();

            } else {
                setMessage("there's no move to undo, it's now " + game.getTurn().toString() + " turn");
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

        clock.addActionListener(e -> {
            System.out.println(game.isUsingClock());
            if (game.isUsingClock()) {
                clock.setText("    use a clock    ");
                setNormalMode();
            } else {
                String[] options = new String[]{"5", "10", "15", "20"};
                int response = JOptionPane.showOptionDialog(null, "enter the total time in minutes?", "time",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (response == 0) {
                    game.setTime(5 * 60 * 1000);
                } else if (response == 1) {
                    game.setTime(10 * 60 * 1000);
                } else if (response == 2) {
                    game.setTime(15 * 60 * 1000);
                } else if (response == 3) {
                    game.setTime(20 * 60 * 1000);
                }
                clock.setText("  not use a clock   ");
                this.clock.setVisible(false);
                move.setVisible(true);
                setClockMode();
                gameManager.updateButtons();
            }
            game.setUsingClock(!game.isUsingClock());
        });

        menuBar.add(menu);
        menuBar.add(muteMenu);
        menuBar.add(clockMenu);
        this.setJMenuBar(menuBar);
    }

    public void setClockMode() {
        scrollPane.setVisible(false);
        total.remove(move);
        this.getContentPane().remove(move);
        move.setVisible(false);
        total.add(clock);
        clock.setVisible(true);
    }

    public void setNormalMode() {
        total.add(move);
        move.setVisible(true);
        this.getContentPane().remove(clock);
        total.remove(clock);
        clock.setVisible(false);
        scrollPane.setVisible(true);
    }

    public final void initializeTotalPage() {
        total.setLayout(new FlowLayout());
        total.setBackground(backGroundColour);
        total.add(gameBoard);
        total.add(move);
        clock.setVisible(false);
    }

    public final void initializeClock() {

        clock.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 5;

        //adding the icon
        JLabel icon = new JLabel("");
        icon.setSize(300, 300);
        icon.setIcon(new ImageIcon("src/resources/Pictures/chess-pngrepo-com (2).png"));
        clock.add(icon, gbc);

        JLabel junk1 = new JLabel("           ");
        gbc.gridy = 2;
        clock.add(junk1, gbc);

        JLabel junk2 = new JLabel("           ");
        gbc.gridy = 3;
        clock.add(junk2, gbc);

        JLabel title = new JLabel("           ");
        title.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 5;
        clock.add(title, gbc);

        JLabel junk3 = new JLabel("           ");
        gbc.gridy = 8;
        clock.add(junk3, gbc);

        JLabel junk4 = new JLabel("           ");
        gbc.gridy = 8;
        clock.add(junk4, gbc);

        blackTime = blackTime.replaceAll("\\n", "<br>");
        blackTime = blackTime.replaceAll(" ", "&nbsp;");
        blackTurn = new JButton("<html>" + blackTime.replaceAll("\\n", "<br>") + "</html>");
        blackTurn.setFont(new Font("Arial", Font.PLAIN, 18));
        blackTurn.setBackground(GameManager.normalTileColour);
        gbc.gridy = 9;
        clock.add(blackTurn, gbc);

        JLabel junk5 = new JLabel("           ");
        gbc.gridy = 10;
        clock.add(junk5, gbc);

        JLabel junk6 = new JLabel("           ");
        gbc.gridy = 11;
        clock.add(junk6, gbc);

        JLabel junk7 = new JLabel("           ");
        gbc.gridy = 12;
        clock.add(junk7, gbc);

        whiteTime = whiteTime.replaceAll("\\n", "<br>");
        whiteTime = whiteTime.replaceAll(" ", "&nbsp;");
        whiteTurn = new JButton("<html>" + whiteTime.replaceAll("\\n", "<br>") + "</html>");
        whiteTurn.setFont(new Font("Arial", Font.PLAIN, 18));
        whiteTurn.setBackground(new Color(240, 240, 240));
        gbc.gridy = 14;
        clock.add(whiteTurn, gbc);

        clock.setBackground(backGroundColour);
    }

    public MainPage(Game.Game game) {

        //setting image and icon of J frame

        this.setTitle("chess");
        ImageIcon img = new ImageIcon("src/resources/Pictures/chess_piece_king.png");
        this.setIconImage(img.getImage());

        MainPage.mainPage = this;
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
        initializeClock();
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
        int xxx;
        int yyy;
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
