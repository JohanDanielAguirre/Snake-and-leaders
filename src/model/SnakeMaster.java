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

    




}


