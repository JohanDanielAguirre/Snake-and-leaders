package model;
//Esta es la controladora
public class SnakeMaster {

    private Board board;

    public SnakeMaster(int n, int m){
        board = new Board(n,m);
        createBoard(n,m);
    }

    public void createBoard(int n, int m){
        board.addSnakes();
        board.addLaddersToTiles();
        board.createBoard((n*m));
    }


    public String print(){
        return board.printBoard();
    }
    /*
    private boolean checkwin(int playerPosition, int dice) {
        if((playerPosition+dice)>=(height*width)){
            return true;
        }else{
            return false;
        }
    }

     */

    

    




}


