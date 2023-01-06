package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.GameUI;
import boardgame.ui.PositionAwareButton;


public class TTTView extends JPanel{
    
    private JLabel messageLabel1;
    private JLabel messageLabel2;
    private TicTacToe game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    private GameUI root;
    private int gameChoice;

    /** 
    * Constructor method
    */
    public TTTView(int wide, int tall, GameUI gameFrame, int gameType){
        // call the superclass constructor
        super();    
        setLayout(new BorderLayout());
        root = gameFrame;
        gameChoice = gameType;
        // instantiate the controller
        setGameController(new TicTacToe(wide,tall));   
        // make a new label to store messages
        if (gameChoice == 1) {
            messageLabel1 = new JLabel("Welcome to TicTacToe");
        } else {
            messageLabel1 = new JLabel("Welcome to Numerical TicTacToe");
        }
        messageLabel2 = new JLabel("Player 1's chance");

        add(messageLabel1, BorderLayout.NORTH);  // Messages go on top   
        add(messageLabel2, BorderLayout.AFTER_LAST_LINE); 
        add(makeNewGameButton(),BorderLayout.EAST);
        add(makeButtonGrid(tall,wide), BorderLayout.CENTER);
        newGame(gameType);
    }

    /** 
    * mutator method
    */
    public void setGameController(TicTacToe controller){
        this.game = controller;
    }

    private JButton makeNewGameButton(){            //CHECK
        JButton button = new JButton("New Game");
        button.addActionListener(e->emptyGrid());   //FIX
        return button;
    }

    private JPanel makeButtonGrid(int tall, int wide){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y=0; y<wide; y++){
            for (int x=0; x<tall; x++){ 
                //Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1); //made the choice to be 1-based
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                        int valid = enterNumber(e);
                                        checkGameState(valid);
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

/* controller methods start here */

    private void checkGameState(int valid){
        int selection= 0;
        JOptionPane gameOver = new JOptionPane();
        if(game.isDone()){
            int winner = game.getWinner();
            if (winner == -1) {
                selection = JOptionPane.showConfirmDialog(null,
            "Game ended in a Draw.\nDo you want to PlayAgain", "DRAW?", JOptionPane.YES_NO_OPTION);
            } else if (winner == 1) {
            selection = JOptionPane.showConfirmDialog(null,
            "Player 1 won.\nDo you want to PlayAgain", "Player 1 win?", JOptionPane.YES_NO_OPTION);
            } else {
                selection = JOptionPane.showConfirmDialog(null,
            "Player 2 won.\nDo you want to PlayAgain", "Player 2 win?", JOptionPane.YES_NO_OPTION); 
            }
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame(gameChoice);
            }
        } else {
            if (valid == 1) {
                game.switchPlayer();
            }
            displayTurn();
            // int player = game.getCurrentPlayer();        // if (player == 1){
            //     messageLabel2.setText("Player 1's chance");                // } else {
            //     messageLabel2.setText("Player 2's chance");                 // }
        }
    }
    private void displayTurn() {
        int player = game.getCurrentPlayer();
        if (player == 1){
            messageLabel2.setText("Player 1's chance");                
        } else {
            messageLabel2.setText("Player 2's chance");                 
        }
    }   

    /** 
    * Method to update the view of GUI
    */
    protected void updateView(){
        //update the labels on the buttons according to the model
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown()));
            }
        }
    }
    /** 
    * Method to start a new game
    */
    protected void newGame(int gameType){   
        game.setGrid(TicTacToe.newGrid(gameType, 3, 3));
        updateView();
    }

    /** 
    * Method to clear/empty the grid for a game
    */
    protected void emptyGrid() {
        game.newGame();
        updateView();
    }
    
    private int enterNumber(ActionEvent e){
        //get input from user
        int valid = 0;
        if (gameChoice == 1){
            valid = gameXOturn(e);
        }else {
            valid = numTTTTurn(e);
        }
        return valid;
    }

    private int gameXOturn(ActionEvent e) {
        int valid = 0;
        String num;
        if (game.getCurrentPlayer() == 1) {
            num = "X";
        } else {
            num = "O";
        }
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if(game.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
            clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
            valid = 1;
        }
        return valid;
    }

    private int numTTTTurn(ActionEvent e) {
        String num;
        int valid = 0;
        num = JOptionPane.showInputDialog("Please input a value");
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if (game.getCurrentPlayer() == 1){
            if ((Integer.parseInt(num) % 2) == 1){
                if(game.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
                    clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
                    valid = 1;
                }   
            }
        } else {                 
            if ((Integer.parseInt(num) % 2) == 0){
                if(game.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
                    clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
                    valid = 1;
                }   
            }
        }
        return valid;
    }


}
