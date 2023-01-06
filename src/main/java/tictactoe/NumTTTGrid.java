package tictactoe;

import java.util.ArrayList;

/* specialized win check and validation methods for NumTTT */

public class NumTTTGrid extends GameGrid{

    private int[] count = new int[9];
    /** 
    * Constructor method
    */
    public NumTTTGrid(int across, int down){
        super(across,down);
        for (int i=0; i<9; i++) {
            count[i] = 0;
        }
    }
        
    /** 
    ** Check if horizontal win has occurred 
    * @return boolean true if won horizontally, false if not
    */
    public boolean horizontalWin(){
        int i = 1;
        for (int j=1; j<getWidth()+1; j++) {
            if ((getValue(i, j) != "-") && (getValue(i+1, j) != "-")
            && (getValue(i+2, j) != "-")){
                if ((Integer.parseInt(getValue(i, j)) + Integer.parseInt(getValue(i+1, j))
                + Integer.parseInt(getValue(i+2, j))) == 15) {
                    return true;
                }
            }
        }
        return false;
    }

    /** 
    ** Check if vertical win has occurred 
    * @return boolean true if won vertically, false if not
    */
    public boolean verticalWin(){
        int j=1;
        for (int i=1; i<getHeight()+1; i++) {
            if ((getValue(i, j) != "-") && (getValue(i, j+1) != "-")
            && (getValue(i, j+2) != "-")){
                if ((Integer.parseInt(getValue(i, j)) + Integer.parseInt(getValue(i, j+1))
                + Integer.parseInt(getValue(i, j+2))) == 15) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    ** Check if diagonal win has occurred  
    * @return boolean true if won diagonally, false if not
    */
    public boolean diagonalWin(){
        int i = 1;
        int j = 1;
    
        if ((getValue(i,j) != "-") && (getValue(i+1,j+1) != "-") 
        && (getValue(i+2,j+2) != "-")) {
            if ((Integer.parseInt(getValue(i,j)) + Integer.parseInt(getValue(i+1, j+1))
            + Integer.parseInt(getValue(i+2, j+2))) == 15) {
                return true;
            }
        } else if ((getValue(i,j+2) != "-") && (getValue(i+1,j+1) != "-") 
        && (getValue(i+2,j) != "-")) {
            if ((Integer.parseInt(getValue(i,j+2)) + Integer.parseInt(getValue(i+1, j+1))
            + Integer.parseInt(getValue(i+2, j))) == 15) {
                return true;
            }
        }
    return false;    
    }

    /** 
    * Validation check for String input, throws exception if string not valid
    */
    public void validateInput(String input)throws Exception{ 
        int num = Integer.parseInt(input);
        if (!((num > 0) && (num < 10))) {
            throw new Exception("Invalid input");
        } else {
            count[num-1]++;
            if (count[num-1] > 1) {
                throw new Exception("Invalid input");
            }
        }
    }

    /** 
    * Validation check for loaction, throws exception if loaction not valid
    */
    public void validateLocation(int across, int down)throws Exception{
        if (across > getWidth()) {
            throw new Exception("invalid across input");
        }
    
        if (down > getHeight()) {
            throw new Exception("invlaid down input");
        }
    
        if (getValue(across, down) != "-") {
            throw new Exception("Position already full");
        }
        
    }




}