package tictactoe;


public class XOGrid extends GameGrid{

private static  String[] symbols ={"X","O"};

public XOGrid(int across, int down){
    super(across,down);
}
    
public boolean horizontalWin(){
    int i = 1;

    for (int j=1; j<getWidth()+1; j++) {
        if (getValue(j, j) != "-") {
            if ((getValue(i, j) == getValue(i+1, j)) && (getValue(i+1, j) == getValue(i+2, j))) {
                return true;
            }
        }
    }
return false;
}

public boolean verticalWin(){
    int j=1;

    for (int i=1; i<getHeight()+1; i++) {
        if (getValue(i, i) != "-") {
            if ((getValue(i, j) == getValue(i, j+1)) && (getValue(i, j+1) == getValue(i, j+2))) {
                return true;
            }
        }
    }
return false;
}

public boolean diagonalWin(){

    int i = 1;
    int j = 1;

    if (getValue(i+1,j+1) != "-") {
        if ((getValue(i,j) == getValue(i+1, j+1)) && (getValue(i+1,j+1) == getValue(i+2, j+2))) {
            return true;
        } else if ((getValue(i,j+2) == getValue(i+1, j+1)) && (getValue(i+1,j+1) == getValue(i+2, j))) {
            return true;
        }
    }
    return false;    
}

@Override
public void validateInput(String input) throws Exception{   //check exception stuff for this and nect one
    // if (input.length() > 1) {
    //     throw new Exception("invalid input");
    // }

    // if ((input != "1") || (input != "2"))   {
    //     throw new Exception("invlaid input");
    // }

    if (!((input.equals("X")) || (input.equals("O")))) {
        throw new Exception("invlaid input");
    }
}

@Override
public void validateLocation(int across, int down)throws Exception{
/* method should check to see if positions are out of bounds
as well as if the position is already full*/
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

// /* private helper methods*/

// private boolean rowCheck(int row){
//      boolean match = false;

//     return match;
// }

// private boolean columnCheck(int col){
//     return false;

// }

// private boolean diagonalCheck(){
//     return false;

// }




}