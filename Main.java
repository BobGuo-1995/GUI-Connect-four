package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

    private JLabel jLabel1;
    private JTextField player1name;
    private JLabel player1nameLabel;
    private JTextField player2name;
    private JLabel player2nameLabel;
    private JButton startGameButton;

    //store players names
    public static String playerName1;
    public static String playerName2;

    //executed when class instance created
    public Main() {
        //create UI
        initComponents();
    }

    //create UI
    private void initComponents() {
        //initialize all variables
        jLabel1 = new JLabel();
        player1nameLabel = new JLabel();
        player1name = new JTextField();
        player2nameLabel = new JLabel();
        startGameButton = new JButton();
        player2name = new JTextField();

        //what happen when close button pressed on window?
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //non resizable window
        setResizable(false);

        //set all texts
        jLabel1.setText("Connect Four Game");

        player1nameLabel.setText("Player 1 name");

        player2nameLabel.setText("Player 2 name");

        startGameButton.setText("Start Game");

        //add event to startButton
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        //create layout to place all elements
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(143, 143, 143)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(76, 76, 76)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(player1nameLabel)
                                                        .addComponent(player1name)
                                                        .addComponent(player2nameLabel)
                                                        .addComponent(player2name)
                                                        .addComponent(startGameButton, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))))
                                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(player1nameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player1name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(player2nameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player2name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(startGameButton)
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }

    //start button event
    private void startGameButtonActionPerformed(ActionEvent evt) {
        //get names and start the game
        playerName1 = player1name.getText();
        playerName2 = player2name.getText();
        //create new window, and make it visible
        new GameView().setVisible(true);
        //and disable current one
        this.setEnabled(false);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            //create frame
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}