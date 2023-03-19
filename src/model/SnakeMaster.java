package model;
//Esta es la controladora
public class SnakeMaster {

    private Board board;

    public SnakeMaster(int n, int m){
        board = new Board(n,m);
        createBoard(n,m);
    }

    public void createBoard(int n, int m){
        board.createBoard((n*m));
        System.out.println("Hola    ");
    }


    public String print(){
        return board.printBoard();

    }
    public String print2(){
        return board.printSnake();

    }
    public String print3(){
        return board.printLadder();

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


