package tictactoe;

public class TicTacToe extends boardgame.BoardGame{ //implements boardgame.Saveable
    private int currentPlayer = 1;
    private String gameStateMessage;
    private boolean done = false;
    private static int gameType;
    /** 
    * Constructor method
    */
    public TicTacToe(int wide, int tall){
        super(wide, tall);

    }

    /** 
    * Static method to get game type
    */
    public static int getGameType() {
        return gameType;
    }

    /** 
    * Static method to set game type
    */
    public static void setGameType(int type) {
        TicTacToe.gameType = type;
    }


    /* methods added that aren't in BoardGame*/

    /** 
    * accessor method for current player
    * @return int returns the current player
    */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    /** 
    * mutator method for done
    */
    public void setGameOver(boolean state){
        done = state;
    }

/* overridden methods from BoardGame*/
@Override
    /** 
    * take player turn and update board
    * @return boolean returns true if turn done successfully, false otherwise
    */
    public  boolean takeTurn(int across, int down, String input){
        //validate input first
        try {
            grid().validateInput(input);
            grid().validateLocation(across, down);
            grid().setValue(across, down, input);
            return true;
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return false;
        }

    }

/* really don't need this method for the design we're using 
so it can be left as a stub*/
@Override
    public  boolean takeTurn(int across, int down, int input){

        return false;
    }

/* I needed this method to be public for 
this design */

@Override
    /** 
    * Set the game grid
    */
    public void setGrid(boardgame.Grid grid){ //used full package name instead of import
        super.setGrid(grid);
        setGameOver(false); //resets done boolean       //changed by me
    }


/*this method does a 'win check' every time it is called.
In this design it is used by the user interfaces to determine what
to do next */

@Override
    /** 
    * Check if game is finished
    * @return booelan returns true if game done, false otherwise
    */
    public boolean isDone(){        //needs fixing
        if (getWinner() != 0){
            setGameOver(true);
        }
        return done;
    }

/* get Winner needs to use the currentPlayer, the isFull() method (to identify tie game)
to decide what to send back.   Has some duplicate functionality with isDone()
because the original design was meant to allow lots of flexibility. */

@Override
    /** method to find that winner
    * @return int returns the player who won, or -1 for a draw, 0 for no win
    */
    public  int getWinner(){//needs fixing
        boolean won = false;
        if (!won) {
            won = grid().horizontalWin();
        }
        if (!won) {
            won = grid().verticalWin();
        }
        if (!won) {
            won = grid().diagonalWin();
        }

        if (!won) {
            if (grid().isFull()) {
                return -1;
            }
        }
        if (won) {
            return getCurrentPlayer();
        } else {
            return 0;
        }
    }

@Override
    /** 
    * accessor method
    * @return string return the current game state message
    */
    public String getGameStateMessage(){
        return gameStateMessage;

    }
/* private helper methods */
    /** 
    * Switch players for next turn
    */
    public void switchPlayer(){
        if(getCurrentPlayer() == 1){
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
    }


    private GameGrid grid(){
        /*because I need the grid frequently I wrote a method 
        to do the casting for me so I don't forget*/

        return (GameGrid) getGrid();
    }

    /* The gameStateMessage can be used by both the GUI
    and the TextUI to easily communicate the current state
    to the user.  Private methods to compose the desired message promote
    encapsulation */

    private void setGameStateMessage(String msg){
        gameStateMessage = msg;
    }

    /** 
    * Create a string for display
    * @return String which needs to be displayed
    */
    public String nextPlayerMessage(){
        String player = "Player 1";
        if(currentPlayer == 2){
            player = "Player 2";
        }
        return(player + " please indicate where you would like to put your token.");
    }

    private String gameOverMessage(){
        /*should compose a nice string about who won and/or tie game*/
        return("game over");
    }

    /** 
    * Get a string of the grid
    * @return String returns the string representation of the grif
    */
    public String gridString() {
        return grid().getStringGrid();
    }

/* static method to facilitate changing the grid type without adding coupling
This is used by the user interfaces to select the game*/
    /** 
    * Static method to set grid depending on game type
    * @return GameGrid returns type of Grid
    */
    public static GameGrid newGrid(int kind, int wide, int tall){
        if(kind == 1){
            setGameType(1);
            return new XOGrid(wide,tall);
        }else{
            setGameType(2);
            return new NumTTTGrid(wide,tall);
        }
    }

}