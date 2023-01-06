package tictactoe;
import java.util.Scanner;

public class TextUI{
    private TicTacToe game = new TicTacToe(3,3);    
    private Scanner input = new Scanner(System.in);
    private int acrossVal;
    private int downVal;
    private int playAgain = 1;

    /** 
    * Constructor method
    */
    public TextUI(int gameType){
        game = new TicTacToe(3,3);
        /*could print out a welcome message here or set a gameType
        variable that let us decide which message to print */
        game.setGrid(TicTacToe.newGrid(1,3,3));
    }

    /** 
    * Method to play the game. Handle turn and playing again and winner
    */
    public void play(){
        while (playAgain == 1) {
            game.setGrid(TicTacToe.newGrid(1,3,3));
            game.switchPlayer();
            while(!game.isDone()) {
                game.switchPlayer();
                doTurn();      // try {        // } catch (Exception e) {      // }
            }
            if (game.getWinner() == -1) {
                displayDraw();          
            } else if (game.getWinner() != 0) {
                displayWinner();        
            }
            playAgain = inputPlayAgain();
            while (playAgain == -1) {
                inputError();
                playAgain = inputPlayAgain();
            }
        }
    }

    private void displayDraw() {
        System.out.println("Game has ended in a draw\n");
    }

    private void displayWinner() {
        System.out.format("Winner is: Player %d, %s\n\n", game.getCurrentPlayer(), getToken());
    }

    private void inputError() {
        System.out.println("Invalid user input");
    }

    private void doTurn() {
        System.out.println(game.gridString());
        System.out.println(game.nextPlayerMessage());
        getPosition();
        if (acrossVal == 0) {
            game.setGameOver(true);
            return;
        }
        while (!game.takeTurn(acrossVal,downVal,getToken())) {
            inputError();
            System.out.println(game.gridString());
            System.out.println(game.nextPlayerMessage());
            getPosition();
            if (acrossVal == 0) {
                game.setGameOver(true);
                break;
            }
        }  
    }
    private void getPosition() {
        /*this method needs some validation and error checking*/
        try {
            System.out.println("across? (0 to quit)");
            acrossVal = Integer.parseInt(input.nextLine());             //input.nextInt();
            //input.nextLine(); //to get rid of hard return
            System.out.println("down?");
            downVal = Integer.parseInt(input.nextLine());           //input.nextInt(); //to get rid or hard return;
        } catch (Exception e) {
            acrossVal = -10;
        }

    }
    

    private int inputPlayAgain() {
        try {
            System.out.println("Enter 1 to play again, any other number otherwise");
            return (Integer.parseInt(input.nextLine()));
        } catch (Exception e) {
            return -1;
        }

    }
    private String getToken(){      //returns the player symbol
        if (game.getCurrentPlayer() == 1) {
            return "X";
        } else {
            return "O";
        }
    }




    /* main method to run the UI */
    public static void main(String[] args){
        TextUI tester = new TextUI(1); //textUI for XO game
        tester.play();
    }
}