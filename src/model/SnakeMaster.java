package model;
//Esta es la controladora
public class SnakeMaster {

    private Board board;

    public SnakeMaster(int numTiles){
        board = new Board(numTiles);
        board.createBoard(numTiles);
    }




}


