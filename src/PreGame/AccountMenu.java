package PreGame;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountMenu extends JFrame implements ActionListener {

    private boolean mutenessBool = false;
    private Icon notMuteIcon = new ImageIcon("src/main/resources/Pictures/Webp.net-resizeimage (4).png");
    private Icon muteIcon = new ImageIcon("src/main/resources/Pictures/Webp.net-resizeimage (2).png");
    private JButton muteness;
    private JButton newGameButton;

    private JMenuItem logoutMenuItem;
    private JMenuItem changePasswordItem;
    private JMenuItem deleteAccountItem;
    private JMenuItem exitItem;

    public AccountMenu() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("src/main/resources/Pictures/how-to-win-a-game-of-chess-in-two-moves.jpg")));
        setLayout(new FlowLayout());

        setTitle("chess game");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);

        ImageIcon img = new ImageIcon("src/main/resources/Pictures/chess_piece_king.png");
        this.setIconImage(img.getImage());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        changePasswordItem = new JMenuItem("change password");
        deleteAccountItem = new JMenuItem("delete account");
        logoutMenuItem = new JMenuItem("logout");
        exitItem = new JMenuItem("exit");
        changePasswordItem.addActionListener(this);
        deleteAccountItem.addActionListener(this);
        exitItem.addActionListener(this);
        logoutMenuItem.addActionListener(this);

        menu.add(changePasswordItem);
        menu.add(deleteAccountItem);
        menu.add(logoutMenuItem);
        menu.add(exitItem);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);


        JLabel newGameLabel = new JLabel("new game");
        newGameLabel.setVisible(true);
        newGameLabel.setForeground(Color.yellow);
        newGameLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        newGameLabel.setSize(300, 30);
        newGameLabel.setLocation(100, 30);
        c.add(newGameLabel);

        JLabel numberOfMoves = new JLabel("number of moves:");
        numberOfMoves.setBackground(Color.black);
        numberOfMoves.setVisible(true);
        numberOfMoves.setForeground(Color.RED);
        numberOfMoves.setFont(new Font("Arial", Font.PLAIN, 20));
        numberOfMoves.setSize(200, 20);
        numberOfMoves.setLocation(30, 130);
        c.add(numberOfMoves);

        JSpinner s = new JSpinner(new SpinnerNumberModel(10, 1, 150, 1));

        // set Bounds for spinner

        s.setBounds(250, 130, 50, 25);
        ((JSpinner.DefaultEditor) s.getEditor()).getTextField().setEditable(false);
        c.add(s);
        s.setVisible(true);

        JLabel rival = new JLabel("your rival:");
        rival.setBackground(Color.black);
        rival.setVisible(true);
        rival.setForeground(Color.RED);
        rival.setFont(new Font("Arial", Font.PLAIN, 20));
        rival.setSize(200, 20);
        rival.setLocation(30, 230);
        c.add(rival);


        JSpinner z = new JSpinner(new SpinnerListModel(Person.getAllUserName()));

        // set Bounds for spinner

        z.setBounds(250, 230, 50, 25);
        ((JSpinner.DefaultEditor) z.getEditor()).getTextField().setEditable(false);
        c.add(z);
        z.setVisible(true);

        JLabel color = new JLabel("your colour:");
        color.setBackground(Color.black);
        color.setVisible(true);
        color.setForeground(Color.RED);
        color.setFont(new Font("Arial", Font.PLAIN, 20));
        color.setSize(200, 20);
        color.setLocation(30, 330);
        c.add(color);


        JSpinner zzz = new JSpinner(new SpinnerListModel(new String[]{"black", "white"}));
        zzz.setBounds(246, 330, 60, 25);
        ((JSpinner.DefaultEditor) zzz.getEditor()).getTextField().setEditable(false);
        c.add(zzz);
        zzz.setVisible(true);


        newGameButton = new JButton("start the game");
        newGameButton.setVisible(true);
        newGameButton.setForeground(Color.yellow);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 25));
        newGameButton.setSize(250, 30);
        newGameButton.setLocation(50, 430);
        newGameButton.addActionListener(this);
        c.add(newGameButton);

        JLabel topPeople = new JLabel("top three");
        topPeople.setVisible(true);
        topPeople.setForeground(Color.yellow);
        topPeople.setFont(new Font("Arial", Font.PLAIN, 30));
        topPeople.setSize(300, 30);
        topPeople.setLocation(550, 30);
        c.add(topPeople);

        JLabel sortFactor = new JLabel("sort factor:");
        sortFactor.setBackground(Color.black);
        sortFactor.setVisible(true);
        sortFactor.setForeground(Color.RED);
        sortFactor.setFont(new Font("Arial", Font.PLAIN, 20));
        sortFactor.setSize(200, 20);
        sortFactor.setLocation(450, 130);
        c.add(sortFactor);

        JSpinner sss = new JSpinner(new SpinnerListModel(new String[]{"number of games", "number of wins", "number of point"}));
        sss.setBounds(600, 130, 200, 25);
        c.add(sss);
        sss.setVisible(true);

        JLabel first = new JLabel("first:");
        first.setBackground(Color.black);
        first.setVisible(true);
        first.setForeground(Color.RED);
        first.setFont(new Font("Arial", Font.PLAIN, 20));
        first.setSize(200, 20);
        first.setLocation(450, 230);
        c.add(first);

        JLabel firstWinner = new JLabel();
        firstWinner.setText(Person.getBestPlayers(sss.getValue().toString()).get(0).getUsername());
        firstWinner.setBackground(Color.black);
        firstWinner.setVisible(true);
        firstWinner.setForeground(Color.RED);
        firstWinner.setFont(new Font("Arial", Font.PLAIN, 20));
        firstWinner.setSize(200, 20);
        firstWinner.setLocation(550, 230);
        c.add(firstWinner);


        JLabel second = new JLabel("second:");
        second.setBackground(Color.black);
        second.setVisible(true);
        second.setForeground(Color.RED);
        second.setFont(new Font("Arial", Font.PLAIN, 20));
        second.setSize(200, 20);
        second.setLocation(450, 330);
        c.add(second);

        JLabel secondWinner = new JLabel();
        if (Person.getBestPlayers(sss.getValue().toString()).size() <= 1) {
            secondWinner.setText("------");
        } else {
            secondWinner.setText(Person.getBestPlayers(sss.getValue().toString()).get(1).getUsername());
        }

        secondWinner.setBackground(Color.black);
        secondWinner.setVisible(true);
        secondWinner.setForeground(Color.RED);
        secondWinner.setFont(new Font("Arial", Font.PLAIN, 20));
        secondWinner.setSize(200, 20);
        secondWinner.setLocation(550, 330);
        c.add(secondWinner);

        JLabel third = new JLabel("third:");
        third.setBackground(Color.black);
        third.setVisible(true);
        third.setForeground(Color.RED);
        third.setFont(new Font("Arial", Font.PLAIN, 20));
        third.setSize(200, 20);
        third.setLocation(450, 430);
        c.add(third);

        JLabel thirdWinner = new JLabel();
        if (Person.getBestPlayers(sss.getValue().toString()).size() <= 2) {
            thirdWinner.setText("------");
        } else {
            thirdWinner.setText(Person.getBestPlayers(sss.getValue().toString()).get(2).getUsername());
        }

        thirdWinner.setBackground(Color.black);
        thirdWinner.setVisible(true);
        thirdWinner.setForeground(Color.RED);
        thirdWinner.setFont(new Font("Arial", Font.PLAIN, 20));
        thirdWinner.setSize(200, 20);
        thirdWinner.setLocation(550, 430);
        c.add(thirdWinner);

        JButton t = new JButton("start the game");
        t.setVisible(true);
        t.setForeground(Color.yellow);
        t.setBackground(Color.BLACK);
        t.setFont(new Font("Arial", Font.PLAIN, 25));
        t.setSize(250, 30);
        t.setLocation(50, 430);
        c.add(t);

        muteness = new JButton(notMuteIcon);
        muteness.setVisible(true);
        muteness.setSize(44, 44);
        muteness.setLocation(820, 480);
        muteness.addActionListener(this);
        c.add(muteness);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changePasswordItem) {
            String new_password = JOptionPane.showInputDialog(this, "enter your new password");
            if (new_password != null) {
                Person.changePassWord(new_password);
                System.out.println(Person.getPersonLoged().getUsername());
                System.out.println("---" + new_password + "---");
            }
        } else if (e.getSource() == deleteAccountItem) {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure to want to delete your account?");
            if (a == JOptionPane.YES_OPTION) {
                Person.deleteAccount();
                this.setVisible(false);
                new LoginMenu();
            }
        } else if (e.getSource() == exitItem) {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure to want to exit?");
            if (a == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == logoutMenuItem) {
            Person.setPersonLoged(null);
            this.setVisible(false);
            new LoginMenu();
        } else if (e.getSource() == muteness) {
            if (mutenessBool) {
                muteness.setIcon(notMuteIcon);
                BackgroundMusic.backgroundMusic.start();
            } else {
                muteness.setIcon(muteIcon);
                BackgroundMusic.backgroundMusic.stop();
            }
            mutenessBool = !mutenessBool;
        } else if (e.getSource() == newGameButton) {
            this.setVisible(false);
            BackgroundMusic.backgroundMusic.stop();
            new GameGUI.MainPage(new Game(1, null, null,5));
        }
    }
}
