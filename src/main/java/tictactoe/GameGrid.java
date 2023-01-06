package tictactoe;

import java.util.Iterator;
/* the creation of a parent class is what lets use the same TTT game
to provide both games */

public abstract class GameGrid extends boardgame.Grid{

/*
*Constructor method
*/
public GameGrid(int across, int down){
    super(across,down);
} 

/** 
** Check if horizontal win has occurred 
* @return boolean true if won horizontally, false if not
*/
public abstract boolean horizontalWin();

/** 
** Check if vertical win has occurred 
* @return boolean true if won vertically, false if not
*/
public abstract boolean verticalWin();

/**
** Check if diagonal win has occurred  
* @return boolean true if won diagonally, false if not
*/
public abstract boolean diagonalWin();

/** 
* Validation check for String input, throws exception if string not valid
*/
public  abstract void validateInput(String input) throws Exception;

/** 
* Validation check for loaction, throws exception if loaction not valid
*/
public  abstract void validateLocation(int across, int down)throws Exception;

/** 
** Check if board is full 
* @return boolean true if board is full, false if not
*/
public boolean isFull(){
    Iterator<String> iter = iterator();
    int count = 0;
        while(iter.hasNext()){
            if(!iter.next().equals("-")){
            count++;
            }
        }
    if(count == getWidth()*getHeight()){
        return true;
    }
    return false;

}




}