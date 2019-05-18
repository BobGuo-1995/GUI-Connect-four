package com.company;

/**
 * Created by Bob Guo on 12/5/2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends javax.swing.JFrame {

    //panel for top buttons
    private JPanel ButtonPanel;
    //panel for game field
    private JPanel MainPanel;
    //bottom panel for stats
    private JPanel Stats;
    //label inside of stats panel, show whose turn it is now
    private JLabel TurnLabel;
    //7 buttons inside ButtonPanel, used to place chips
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    //label to show player1 name,
    private JLabel player1Name;
    //and text field to show number of wins. This text field is not editable.
    private JTextField player1stats;
    //label to show player2 name,
    private JLabel player2name;
    //and text field to show number of wins. This text field is not editable.
    private JTextField player2stats;
    //quit buttton
    private JButton quitButton;

    //chips arrays
    private Chip[][] chips = new Chip[6][7];
    //
    private static int filedWidth;
    private static int fieldHeight;
    //current turn
    public static int turn = 1;

    public GameView() {
        //fill arrays with values, used to reset field after one of players win the game
        fillArrays(chips);
        //create UI
        initComponents();
    }

    //create UI
    private void initComponents() {
        //initialize all variables
        ButtonPanel = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        Stats = new JPanel();
        TurnLabel = new JLabel();
        player1Name = new JLabel();
        player2name = new JLabel();
        player1stats = new JTextField();
        player2stats = new JTextField();
        quitButton = new JButton();
        //here column created main panel, and modified its paintComponent, so when it paint column can draw field and chips,
        //rather than just panel
        MainPanel = new JPanel(){
            //override means that it extends this method from its parent, but column want to rewrite it in my own way
            @Override
            protected void paintComponent(Graphics grphcs) {
                //its just call the method at parent, super meant parent object
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                filedWidth = getWidth();
                fieldHeight = getHeight();
                //here is main drawing method called, its draw chips and field
                drawChips(g2d, chips, getWidth(), getHeight());
            }
        };
        //what happen when close button pressed on window?
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //non resizable window
        setResizable(false);

        //add event for quit button, simple System.exit(0) will close program
        quitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.exit(0);
            }
        });
        //add event for each button, placeAt(int index) will place chip column specified index(column)
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(0);
            }
        });

        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(1);
            }
        });

        jButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(2);
            }
        });

        jButton4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(3);
            }
        });

        jButton5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(4);
            }
        });

        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(5);
            }
        });

        jButton7.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                PlaceAt(6);
            }
        });

        //create layout for main panel, tells how elements would be arranged
        javax.swing.GroupLayout MainPanelLayout = new GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );
        //define all texts
        jButton1.setText("Place");

        jButton2.setText("Place");

        jButton3.setText("Place");

        jButton4.setText("Place");

        jButton5.setText("Place");

        jButton6.setText("Place");

        jButton7.setText("Place");

        TurnLabel.setText("Player's " + Main.playerName1 + " turn");

        player1Name.setText(Main.playerName1);
        player1stats.setText("0");
        player2stats.setText("0");

        player2name.setText(Main.playerName2);

        quitButton.setText("Quit Game");

        //set textField to non editable
        player1stats.setEditable(false);
        player2stats.setEditable(false);

        //create layout for buttonsPanel, specify how they should be placed
        javax.swing.GroupLayout ButtonPanelLayout = new GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
                ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ButtonPanelLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        ButtonPanelLayout.setVerticalGroup(
                ButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ButtonPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3)
                                        .addComponent(jButton4)
                                        .addComponent(jButton5)
                                        .addComponent(jButton6)
                                        .addComponent(jButton7))
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        //create layout for stats layout, specify how elements inside should be placed
        javax.swing.GroupLayout StatsLayout = new GroupLayout(Stats);
        Stats.setLayout(StatsLayout);
        StatsLayout.setHorizontalGroup(
                StatsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(StatsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(StatsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(player2name)
                                        .addComponent(player1Name))
                                .addGap(18, 18, 18)
                                .addGroup(StatsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(StatsLayout.createSequentialGroup()
                                                .addComponent(player1stats, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(TurnLabel))
                                        .addComponent(player2stats, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(quitButton)
                                .addContainerGap())
        );
        StatsLayout.setVerticalGroup(
                StatsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(StatsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(StatsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(player1Name)
                                        .addComponent(player1stats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(quitButton)
                                        .addComponent(TurnLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(StatsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(player2name)
                                        .addComponent(player2stats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ButtonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Stats, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Stats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        //used to size the frame for its content to use prefferedSize
        pack();
    }

    //method to place chip at specified column col
    private void PlaceAt(int col) {
        //if we reached the top(6 chips stack), just quit the method
        if(!chips[5][col].empty){
            return;
        }

        //check next available chip to place
        for(int row = 0; row < 6; row++){
            //so if next one is empty, use it
            if(chips[row][col].empty){
                chips[row][col].empty = false;
                //set x position
                chips[row][col].x = col * (filedWidth / 7);
                //set row position
                chips[row][col].y = 320 - row * (fieldHeight / 6);
                //set its color
                if(turn == 1){
                    chips[row][col].color = Color.red;
                } else {
                    chips[row][col].color = Color.black;
                }
                //and just quit the loop, we'r done here
                break;
            }
        }

        //check for winner
        if(Winner(chips)){
            //repaint to show last 4'th chip
            MainPanel.repaint();
            if(turn == 1){
                //if its first player turn, then he won, show simple alert box,
                JOptionPane.showMessageDialog(null, "Player " + Main.playerName1 + " won.");
                //and increase his winNumber by one(get text, parse it to number, increase it by one, and convert at string again)
                player1stats.setText(String.valueOf((Integer.parseInt(player1stats.getText()) + 1)));
            } else {
                //same as above, but for player 2
                JOptionPane.showMessageDialog(null, "Player " + Main.playerName2 + " won.");
                player2stats.setText(String.valueOf((Integer.parseInt(player2stats.getText()) + 1)));
            }
            //reset the field
            fillArrays(chips);
            //current turn is player 1
            turn = 1;
        } else {
            //if we dont have a winner, just change the turn
            turn = -turn;
        }

        //change turnLabel to show whose turn it is now
        if(turn == 1){
            TurnLabel.setText("Player's " + Main.playerName1 + " turn");
        } else {
            TurnLabel.setText("Player's " + Main.playerName2 + " turn");
        }

        //well, repaint
        MainPanel.repaint();
    }

    //draw chips that are NOT empty
    private static void drawChips(Graphics2D g, Chip[][] chips, int w, int h){
        for(int column = 0; column < 6; column++){
            for(int row = 0; row < 7; row++){
                //if current chip is not empty(placed), then draw it
                if(!chips[column][row].empty){
                    //set current color according to chip's color
                    g.setColor(chips[column][row].color);
                    //draw chip
                    g.fillOval(chips[column][row].x + 12,
                            chips[column][row].y + 7, (fieldHeight / 8), (fieldHeight / 8));
                }
            }
        }
        //set color to black, for field
        g.setColor(Color.black);
        //draw field
        for(int a = 0; a < 8; a++){
            g.drawLine(a * (w / 7), 0, a * (w / 7), h);
        }
        for(int a = 0; a < 7; a++){
            g.drawLine(0, a * (h / 6), w, a * (h / 6));
        }
    }

    //used to reset chips
    private static void fillArrays(Chip[][] chips){
        for(int column = 0; column < 6; column++){
            for(int row = 0; row < 7; row++){
                chips[column][row] = new Chip();
            }
        }
    }

    public static boolean Winner(Chip[][] Board) {
        boolean CheckRow = false;
        boolean CheckCol = false;
        boolean Checkdialefttoright = false;
        boolean Checkdiarighttoleft= false;
        //check for row,the only thing it changes is the index of the columns
        //and checking the rows spot by spot and by comparing the others with the first one
        //will make sure we don't miss one
        for (int row = 0; row <= 5; row++)
        {
            for (int column = 0; column <= 3; column++)
            {//make sure the column is not out of boundary
                if (!Board[row][column].empty && !Board[row][column + 1].empty && !Board[row][column+ 2].empty && !Board[row][column + 3].empty){
                    if (Board[row][column].color == Board[row][column + 1].color && Board[row][column].color == Board[row][column+ 2].color && Board[row][column].color == Board[row][column + 3].color && Board[row][column] !=  null)
                    {
                        CheckRow = true;
                        break;
                    }
                }
            }
        }
        //using similar idea of checking for rows, we use the idea for checking for columns
        for (int r = 0 ; r <= 2; r++)
        {
            for (int c = 0; c <= 6; c++)
            {
                if (!Board[r][c].empty && !Board[r + 1][c].empty && !Board[r + 2][c].empty && !Board[r + 3][c].empty){
                    if (Board[r][c].color == Board[r + 1][c].color && Board[r][c].color == Board[r + 2][c].color && Board[r][c].color ==Board[r + 3][c].color && Board[r][c] != null)
                    {
                        CheckCol = true;
                        break;
                    }
                }
            }
        }
        //this was used check diagonal from left to right

        for (int r = 0; r <= 2; r++)
        {
            for (int c = 0; c <= 3; c++)
            {
                if (!Board[r][c].empty && !Board[r + 1][c + 1].empty && !Board[r + 2][c + 2].empty && !Board[r + 3][c + 3].empty){
                    if (Board[r][c].color == Board[r + 1][c + 1].color && Board[r][c].color == Board[r + 2][c + 2].color && Board[r][c].color == Board[r + 3][c + 3].color && Board[r][c] != null)
                    {
                        Checkdialefttoright = true;
                        break;
                    }
                }
            }
        }
        //check the diagonal from right to left
        for (int r = 0; r <= 2; r++)
        {
            for (int c = 3; c <= 6; c++)
            {
                if (!Board[r][c].empty && !Board[r + 1][c - 1].empty && !Board[r + 2][c - 2].empty && !Board[r + 3][c - 3].empty){
                    if (Board[r][c].color == Board[r + 1][c - 1].color && Board[r][c].color == Board[r + 2][c - 2].color && Board[r][c].color == Board[r + 3][c - 3].color && Board[r][c] != null)
                    {
                        Checkdiarighttoleft = true;
                        break;
                    }
                }

            }
        }
        //either one of this true will lead us to a winner
        if (CheckCol || Checkdialefttoright || Checkdiarighttoleft ||CheckRow )
            return true;
        else
            return false;
    }
}
