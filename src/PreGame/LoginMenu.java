package PreGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class LoginMenu extends JFrame implements ActionListener {

    private boolean mutenessBool = false;
    private Icon notMuteIcon = new ImageIcon("src/resources/Pictures/Webp.net-resizeimage (4).png");
    private Icon muteIcon = new ImageIcon("src/resources/Pictures/Webp.net-resizeimage (2).png");
    private JButton muteness;

    private JLabel name;
    private JLabel RegisterTitle;
    private JLabel LoginTitle;
    private JPasswordField RegisterPasswordField;
    private JTextField RegisterNameField;
    private JTextField RegisterUsernameField;
    private JCheckBox RegisterTermsAndConditions;
    private JButton RegisterSubmit;
    private JButton RegisterReset;
    private JPasswordField LoginPasswordField;
    private JTextField LoginUsernameField;
    private JCheckBox LoginTermsAndConditions;
    private JButton LoginSubmit;
    private JButton LoginReset;
    private JMenuItem registerMenuItem;
    private JMenuItem loginMenuItem;
    private JMenuItem exitItem;
    private JLabel username;
    private JLabel mno;
    private JLabel username2;
    private JLabel mno2;

    // constructor, to initialize the components
    // with default values.

    public LoginMenu() {
        BackgroundMusic.backgroundMusic.start();

        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("src/resources/Pictures/how-to-win-a-game-of-chess-in-two-moves.jpg")));
        setLayout(new FlowLayout());

        setTitle("chess game");
        setBounds(300, 90, 650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon img = new ImageIcon("src/resources/Pictures/chess_piece_king.png");
        this.setIconImage(img.getImage());

        // Components of the Form
        Container c = getContentPane();
        c.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        registerMenuItem = new JMenuItem("register menu");
        loginMenuItem = new JMenuItem("login menu");
        exitItem = new JMenuItem("exit");
        registerMenuItem.addActionListener(this);
        loginMenuItem.addActionListener(this);
        exitItem.addActionListener(this);
        menu.add(loginMenuItem);
        menu.add(registerMenuItem);
        menu.add(exitItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        RegisterTitle = new JLabel("register");
        RegisterTitle.setVisible(true);
        RegisterTitle.setForeground(Color.RED);
        RegisterTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        RegisterTitle.setSize(300, 30);
        RegisterTitle.setLocation(250, 30);
        c.add(RegisterTitle);

        name = new JLabel("Name");
        name.setForeground(Color.RED);
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        RegisterNameField = new JTextField();
        RegisterNameField.setFont(new Font("Arial", Font.PLAIN, 15));
        RegisterNameField.setSize(190, 20);
        RegisterNameField.setLocation(200, 100);
        c.add(RegisterNameField);

        username = new JLabel("username");
        username.setForeground(Color.RED);
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setSize(100, 20);
        username.setLocation(100, 150);
        c.add(username);

        RegisterUsernameField = new JTextField();
        RegisterUsernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        RegisterUsernameField.setSize(190, 20);
        RegisterUsernameField.setLocation(200, 150);
        c.add(RegisterUsernameField);


        mno = new JLabel("Password");
        mno.setForeground(Color.RED);
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(100, 200);
        c.add(mno);

        RegisterPasswordField = new JPasswordField();
        RegisterPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        RegisterPasswordField.setSize(190, 20);
        RegisterPasswordField.setLocation(200, 200);
        c.add(RegisterPasswordField);

        RegisterTermsAndConditions = new JCheckBox("Accept Terms And Conditions.");
        RegisterTermsAndConditions.setForeground(Color.RED);
        RegisterTermsAndConditions.setBackground(Color.yellow);
        RegisterTermsAndConditions.setFont(new Font("Arial", Font.PLAIN, 15));
        RegisterTermsAndConditions.setSize(250, 20);
        RegisterTermsAndConditions.setLocation(150, 250);
        c.add(RegisterTermsAndConditions);

        RegisterSubmit = new JButton("Submit");
        RegisterSubmit.setFont(new Font("Arial", Font.PLAIN, 15));
        RegisterSubmit.setSize(100, 20);
        RegisterSubmit.setLocation(150, 300);
        RegisterSubmit.addActionListener(this);
        c.add(RegisterSubmit);

        RegisterReset = new JButton("Reset");
        RegisterReset.setFont(new Font("Arial", Font.PLAIN, 15));
        RegisterReset.setSize(100, 20);
        RegisterReset.setLocation(270, 300);
        RegisterReset.addActionListener(this);
        c.add(RegisterReset);

        setVisible(true);

        LoginTitle = new JLabel("login");
        LoginTitle.setVisible(false);
        LoginTitle.setForeground(Color.RED);
        LoginTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        LoginTitle.setSize(300, 30);
        LoginTitle.setLocation(250, 30);
        c.add(LoginTitle);

        username2 = new JLabel("username");
        username2.setForeground(Color.RED);
        username2.setFont(new Font("Arial", Font.PLAIN, 20));
        username2.setSize(100, 20);
        username2.setLocation(100, 100);
        username2.setVisible(false);
        c.add(username2);

        LoginUsernameField = new JTextField();
        LoginUsernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        LoginUsernameField.setSize(190, 20);
        LoginUsernameField.setLocation(200, 100);
        LoginUsernameField.setVisible(false);
        c.add(LoginUsernameField);


        mno2 = new JLabel("Password");
        mno2.setForeground(Color.RED);
        mno2.setFont(new Font("Arial", Font.PLAIN, 20));
        mno2.setSize(100, 20);
        mno2.setLocation(100, 150);
        mno2.setVisible(false);
        c.add(mno2);

        LoginPasswordField = new JPasswordField();
        LoginPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        LoginPasswordField.setSize(190, 20);
        LoginPasswordField.setLocation(200, 150);
        LoginPasswordField.setVisible(false);
        c.add(LoginPasswordField);

        LoginTermsAndConditions = new JCheckBox("Accept Terms And Conditions.");
        LoginTermsAndConditions.setForeground(Color.RED);
        LoginTermsAndConditions.setBackground(Color.yellow);
        LoginTermsAndConditions.setFont(new Font("Arial", Font.PLAIN, 15));
        LoginTermsAndConditions.setSize(250, 20);
        LoginTermsAndConditions.setLocation(150, 200);
        LoginTermsAndConditions.setVisible(false);
        c.add(LoginTermsAndConditions);

        LoginSubmit = new JButton("Submit");
        LoginSubmit.setFont(new Font("Arial", Font.PLAIN, 15));
        LoginSubmit.setSize(100, 20);
        LoginSubmit.setLocation(150, 250);
        LoginSubmit.addActionListener(this);
        LoginSubmit.setVisible(false);
        c.add(LoginSubmit);

        LoginReset = new JButton("Reset");
        LoginReset.setFont(new Font("Arial", Font.PLAIN, 15));
        LoginReset.setSize(100, 20);
        LoginReset.setLocation(270, 250);
        LoginReset.addActionListener(this);
        LoginReset.setVisible(false);
        c.add(LoginReset);

        muteness = new JButton(notMuteIcon);
        muteness.setVisible(true);
        muteness.setSize(44, 44);
        muteness.setLocation(570, 380);
        muteness.addActionListener(this);
        c.add(muteness);

    }

    public void actionPerformed(ActionEvent e) {

        //if register submit was pressed

        if (e.getSource() == RegisterSubmit) {
            if (RegisterTermsAndConditions.isSelected()) {
                if (Person.isTherePersonWithUsername(RegisterUsernameField.getText())) {
                    JOptionPane.showMessageDialog(this, "the username is already taken", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    Person.register(RegisterUsernameField.getText(), Arrays.toString(RegisterPasswordField.getPassword()), RegisterNameField.getText());
                    JOptionPane.showMessageDialog(this, "registered successfully", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    String def = "";
                    RegisterNameField.setText(def);
                    RegisterPasswordField.setText(def);
                    RegisterUsernameField.setText(def);
                    RegisterTermsAndConditions.setSelected(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "please accept the terms", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

        //resetting the fields

        else if (e.getSource() == RegisterReset) {
            String def = "";
            RegisterNameField.setText(def);
            RegisterPasswordField.setText(def);
            RegisterUsernameField.setText(def);
            RegisterTermsAndConditions.setSelected(false);
        }

        //resetting the fields

        else if (e.getSource() == LoginReset) {
            String def = "";
            LoginPasswordField.setText(def);
            LoginUsernameField.setText(def);
            LoginTermsAndConditions.setSelected(false);
        }

        //going to sign up menu

        else if (e.getSource() == registerMenuItem) {
            getRegisterMenu();
        }

        //going to login menu

        else if (e.getSource() == loginMenuItem) {
            getLoginMenu();
        }

        //leaving the programme

        else if (e.getSource() == exitItem) {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure to want to exit?");
            if (a == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        //checking the submitting login

        else if (e.getSource() == LoginSubmit) {
            if (!Person.isTherePersonWithUsername(LoginUsernameField.getText())) {
                JOptionPane.showMessageDialog(this, "invalid username", "Alert", JOptionPane.WARNING_MESSAGE);
            } else if (!Person.login(LoginUsernameField.getText(), Arrays.toString(LoginPasswordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "wrong password", "Alert", JOptionPane.INFORMATION_MESSAGE);
            } else {
                BackgroundMusic.backgroundMusic.stop();
                AccountMenu m = new AccountMenu();
                m.setVisible(true);
                setVisible(false);
                Person.login(LoginUsernameField.getText(), Arrays.toString(LoginPasswordField.getPassword()));
            }

        }

        //taking care of the mute icon

        else if (e.getSource() == muteness) {
            if (mutenessBool) {
                muteness.setIcon(notMuteIcon);
                BackgroundMusic.backgroundMusic.start();
            } else {
                muteness.setIcon(muteIcon);
                BackgroundMusic.backgroundMusic.stop();
            }
            mutenessBool = !mutenessBool;
        }

    }

    void getRegisterMenu() {
        RegisterTitle.setVisible(true);
        RegisterPasswordField.setVisible(true);
        RegisterNameField.setVisible(true);
        RegisterUsernameField.setVisible(true);
        RegisterTermsAndConditions.setVisible(true);
        RegisterSubmit.setVisible(true);
        RegisterReset.setVisible(true);
        name.setVisible(true);
        username.setVisible(true);
        mno.setVisible(true);

        LoginTitle.setVisible(false);
        LoginPasswordField.setVisible(false);
        LoginUsernameField.setVisible(false);
        LoginTermsAndConditions.setVisible(false);
        LoginSubmit.setVisible(false);
        LoginReset.setVisible(false);
        username2.setVisible(false);
        mno2.setVisible(false);
    }

    void getLoginMenu() {

        RegisterTitle.setVisible(false);
        RegisterPasswordField.setVisible(false);
        RegisterNameField.setVisible(false);
        RegisterUsernameField.setVisible(false);
        RegisterTermsAndConditions.setVisible(false);
        RegisterSubmit.setVisible(false);
        RegisterReset.setVisible(false);
        name.setVisible(false);
        username.setVisible(false);
        mno.setVisible(false);

        LoginTitle.setVisible(true);
        LoginPasswordField.setVisible(true);
        LoginUsernameField.setVisible(true);
        LoginTermsAndConditions.setVisible(true);
        LoginSubmit.setVisible(true);
        LoginReset.setVisible(true);
        username2.setVisible(true);
        mno2.setVisible(true);
    }

}
