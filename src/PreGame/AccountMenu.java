package PreGame;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AccountMenu extends JFrame implements ActionListener {
    private JSpinner numberOfMovesSpinner;
    private JSpinner rivalSpinner;
    private boolean mutenessBool = false;
    private Icon notMuteIcon = new ImageIcon("src/resources/Pictures/Webp.net-resizeimage (4).png");
    private Icon muteIcon = new ImageIcon("src/resources/Pictures/Webp.net-resizeimage (2).png");
    private JButton muteness;
    private JButton newGameButton;
    private JList colourList;
    private JSpinner numberOfUndoSpinner;

    private JMenuItem logoutMenuItem;
    private JMenuItem changePasswordItem;
    private JMenuItem deleteAccountItem;
    private JMenuItem exitItem;

    public AccountMenu() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("src/resources/Pictures/how-to-win-a-game-of-chess-in-two-moves.jpg")));
        setLayout(new FlowLayout());

        BackgroundMusic.backgroundMusic.start();

        setTitle("chess game");
        setBounds(300, 90, 900, 630);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);

        ImageIcon img = new ImageIcon("src/resources/Pictures/chess_piece_king.png");
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

        numberOfMovesSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 150, 1));

        // set Bounds for spinner

        numberOfMovesSpinner.setBounds(250, 130, 50, 25);
        ((JSpinner.DefaultEditor) numberOfMovesSpinner.getEditor()).getTextField().setEditable(false);
        c.add(numberOfMovesSpinner);
        numberOfMovesSpinner.setVisible(true);


        JLabel rival = new JLabel("your rival:");
        rival.setBackground(Color.black);
        rival.setVisible(true);
        rival.setForeground(Color.RED);
        rival.setFont(new Font("Arial", Font.PLAIN, 20));
        rival.setSize(200, 20);
        rival.setLocation(30, 230);
        c.add(rival);


        rivalSpinner = new JSpinner(new SpinnerListModel(Person.getAllUserName()));

        // set Bounds for spinner

        rivalSpinner.setBounds(250, 230, 50, 25);
        ((JSpinner.DefaultEditor) rivalSpinner.getEditor()).getTextField().setEditable(false);
        c.add(rivalSpinner);
        rivalSpinner.setVisible(true);

        JLabel color = new JLabel("your colour:");
        color.setBackground(Color.black);
        color.setVisible(true);
        color.setForeground(Color.RED);
        color.setFont(new Font("Arial", Font.PLAIN, 20));
        color.setSize(200, 20);
        color.setLocation(30, 330);
        c.add(color);


        DefaultListModel demoList = new DefaultListModel<>();
        demoList.addElement("black");
        demoList.addElement("white");
        colourList = new JList<>(demoList);
        colourList.setBounds(246, 330, 60, 36);
        c.add(colourList);
        colourList.setVisible(true);

        JLabel numberOfUndo = new JLabel("number of undos:");
        numberOfUndo.setBackground(Color.black);
        numberOfUndo.setVisible(true);
        numberOfUndo.setForeground(Color.RED);
        numberOfUndo.setFont(new Font("Arial", Font.PLAIN, 20));
        numberOfUndo.setSize(200, 20);
        numberOfUndo.setLocation(30, 430);
        c.add(numberOfUndo);

        numberOfUndoSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));

        // set Bounds for spinner

        numberOfUndoSpinner.setBounds(250, 430, 50, 25);
        ((JSpinner.DefaultEditor) numberOfUndoSpinner.getEditor()).getTextField().setEditable(false);
        c.add(numberOfUndoSpinner);
        numberOfUndoSpinner.setVisible(true);


        newGameButton = new JButton("start the game");
        newGameButton.setVisible(true);
        newGameButton.setForeground(Color.yellow);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 25));
        newGameButton.setSize(250, 30);
        newGameButton.setLocation(50, 508);
        newGameButton.addActionListener(this);
        c.add(newGameButton);

        JLabel topPeople = new JLabel("top three");
        topPeople.setVisible(true);
        topPeople.setForeground(Color.yellow);
        topPeople.setFont(new Font("Arial", Font.PLAIN, 30));
        topPeople.setSize(300, 30);
        topPeople.setLocation(550, 30);
        c.add(topPeople);

        JLabel sortFactor = new JLabel("these are the greatest three in the game");
        sortFactor.setBackground(Color.black);
        sortFactor.setVisible(true);
        sortFactor.setForeground(Color.RED);
        sortFactor.setFont(new Font("Arial", Font.PLAIN, 20));
        sortFactor.setSize(400, 20);
        sortFactor.setLocation(450, 130);
        c.add(sortFactor);

        JLabel first = new JLabel("first:");
        first.setBackground(Color.black);
        first.setVisible(true);
        first.setForeground(Color.RED);
        first.setFont(new Font("Arial", Font.PLAIN, 20));
        first.setSize(200, 20);
        first.setLocation(450, 230);
        c.add(first);

        JLabel firstWinner = new JLabel();
        firstWinner.setText(Person.getBestPlayers().get(0).getUsername());
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
        if (Person.getBestPlayers().size() <= 1) {
            secondWinner.setText("------");
        } else {
            secondWinner.setText(Person.getBestPlayers().get(1).getUsername());
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
        if (Person.getBestPlayers().size() <= 2) {
            thirdWinner.setText("------");
        } else {
            thirdWinner.setText(Person.getBestPlayers().get(2).getUsername());
        }

        thirdWinner.setBackground(Color.black);
        thirdWinner.setVisible(true);
        thirdWinner.setForeground(Color.RED);
        thirdWinner.setFont(new Font("Arial", Font.PLAIN, 20));
        thirdWinner.setSize(200, 20);
        thirdWinner.setLocation(550, 430);
        c.add(thirdWinner);

        muteness = new JButton(notMuteIcon);
        muteness.setVisible(true);
        muteness.setSize(44, 44);
        muteness.setLocation(820, 500);
        muteness.addActionListener(this);
        c.add(muteness);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changePasswordItem) {
            String new_password = JOptionPane.showInputDialog(this, "enter your new password");
            if (new_password != null) {
                StringBuilder s = new StringBuilder("[");
                for (int i = 0; i < new_password.length(); i++) {
                    if (i != new_password.length() - 1)
                        s.append(new_password.charAt(i)).append(", ");
                    else
                        s.append(new_password.charAt(i)).append("]");
                }
                System.out.println(s.toString());
                Person.changePassWord(s.toString());
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
        }

        //starting a new game

        else if (e.getSource() == newGameButton) {
            if (colourList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "please select a colour", "Alert", JOptionPane.WARNING_MESSAGE);
            } else if (colourList.getSelectedValue() == "black") {
                this.setVisible(false);
                BackgroundMusic.backgroundMusic.stop();
                new GameGUI.MainPage(new Game((Integer) numberOfUndoSpinner.getValue(), (String) rivalSpinner.getValue(), Person.getPersonLoged().getUsername(), (Integer) numberOfMovesSpinner.getValue()));
            } else if (colourList.getSelectedValue() == "white") {
                new GameGUI.MainPage(new Game((Integer) numberOfUndoSpinner.getValue(), Person.getPersonLoged().getUsername(), (String) rivalSpinner.getValue(), (Integer) numberOfMovesSpinner.getValue()));
                this.setVisible(false);
                BackgroundMusic.backgroundMusic.stop();
            }
        }

    }

}
