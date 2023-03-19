package model;
import com.sun.jndi.toolkit.ctx.HeadTail;

import java.util.Random;

public class Board {

    Random objRandom = new Random();
    private Tile root;
    private Tile tail;

    private int numOfTiles;
    private int n;
    private int m;

    public Board(int n, int m) {
        this.numOfTiles = n * m;
        this.n = n;
        this.m = m;
    }

    public void createBoard(int numberTiles) {
        createBoard(numOfTiles, 0);
        addSnakes();
        addLaddersToTiles();
    }

    private void createBoard(int numOfTiles, int n) {

        if (n == numOfTiles) {
            return;
        }
        addTileAtTail(new Tile(n + 1));
        createBoard(numOfTiles, n + 1);
    }

    private void addTileAtTail(Tile node) {

        if (root == null) {
            root = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;

    }

    public void addSnakes() {
        int numSnakes = n/3;
        addSnakesToTiles(65, numSnakes);

    }

    private void addSnakesToTiles(int id, int snakesLeft) {
        boolean flag2 = true;
        int numRandomHead = 0;
        Tile tile = null;

        while(flag2){
            numRandomHead = objRandom.nextInt(numOfTiles);
            numRandomHead = numRandomHead <= 1? numRandomHead + 1 : numRandomHead;
            tile = findTile(root, numRandomHead);

            if (tile!= null && tile.getState().equals(StateSnakeOrLadder.FREE)) {
                flag2 = false;
            }
        }

        boolean flag1 = true;

        if (numRandomHead > 3 || numRandomHead < numOfTiles) {

            if (tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                tile.setSnake(new Snake(id));
                tile.setHead(true);
                tile.setState(StateSnakeOrLadder.OCCUPIEDSNAKE);
                Tile tileTail = null;
                int tailPlace = 0;

                while (flag1) {

                    tailPlace = objRandom.nextInt(numRandomHead);
                    tailPlace= tailPlace == 0? tailPlace + 2 : tailPlace;
                    tailPlace = tailPlace == 1? tailPlace + 1 : tailPlace;
                    tileTail = findTile(root, tailPlace);

                    if (tileTail!= null && tileTail.getState().equals(StateSnakeOrLadder.FREE)) {
                        flag1 = false;
                    }

                }

                if (tileTail.getState().equals(StateSnakeOrLadder.FREE) ) {

                    tileTail.setSnake(new Snake(id));
                    tileTail.setState(StateSnakeOrLadder.OCCUPIEDSNAKE);

                }

            }

        }
        if (snakesLeft > 1) addSnakesToTiles(id + 1, snakesLeft - 1);

    }

    public void addLaddersToTiles(){
        int numLadders = n/3;
        addLaddersToTiles(1,numLadders);
    }

    private void addLaddersToTiles(int id, int laddersleft){
        boolean flag2 = true;
        int numRandomHead = 0;
        Tile tile = findTile(root,numRandomHead);

        while(flag2){
            numRandomHead = (int) (Math.random() * (numOfTiles));
            numRandomHead = numRandomHead <= 1? numRandomHead + 1 : numRandomHead;
            tile = findTile(root,numRandomHead);
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)){
                flag2 = false;
            }
        }
        

        boolean flag1 = true;

        if (numRandomHead > 3 || numRandomHead < numOfTiles){
            
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                tile.setLadder(new Ladder(id));
                tile.setState(StateSnakeOrLadder.OCCUPIEDLADDER);

                int startPlace;

                Tile tileStart = null;

                while(flag1){
                    startPlace = objRandom.nextInt(numRandomHead);
                    startPlace = startPlace == 0? startPlace + 2 : startPlace;
                    startPlace = startPlace == 1? startPlace + 1 : startPlace;
                    tileStart = findTile(root,startPlace);

                    if (tileStart != null && tileStart.getState().equals(StateSnakeOrLadder.FREE)){
                        flag1 = false;
                    }
                }

                if(tileStart.getState().equals(StateSnakeOrLadder.FREE)){

                    tileStart.setLadder(new Ladder(id));
                    tileStart.setHead(true);
                    tileStart.setState(StateSnakeOrLadder.OCCUPIEDLADDER);

                }
            }
        }
        if (laddersleft > 1) {addLaddersToTiles(id+1,laddersleft-1);}
    }

    public Tile findTile(Tile tileFound, int numTile){

        if(tileFound!=null && tileFound.getNumberTile()!=numTile){
           return findTile(tileFound.getNext(),numTile);
        }else{
            return tileFound;
        }


    }

    public String printBoard(){
        String msg = "";
        msg = printBoard(n,1);
        return msg;
    }
    private String printBoard(int fila, int columna) {
       String msg = "";
        Tile tile = null;
        if (fila ==  0) {
            return "";
        }
        int numero;
        if (fila % 2 != 0) {
            numero = (fila - 1) * m + columna;
            tile = findTile(root,numero);
        } else {
            numero = fila * m - columna + 1;
            tile = findTile(root,numero);
        }

        msg+= "["+tile.getNumberTile()+"]";

        if (columna == m) {
            msg+="\n";
            msg += printBoard(fila-1, 1);
        } else {
            msg += printBoard(fila, columna+1);
        }

        return msg;
    }

    public String printSnake(){
        String msg = "";
        msg = printSnake(n,1);
        return msg;
    }

    private String printSnake(int fila, int columna) {

        String msg = "";
        Tile tile = null;
        
        if (fila ==  0) {
            return "";
        }
        int number; 
        if (fila % 2 != 0){
            number = (fila - 1) * m + columna;
            tile = findTile(root, number);
            if(tile.getSnake()!=null){
                msg+= "["+tile.getSnake().getId()+"]";
            }else{
                msg+= "["+" "+"]";
            }
        }else {
            number = fila * m - columna + 1;
            tile =findTile(root, number);
            if(tile.getSnake()!=null){
                msg+= "["+tile.getSnake().getId()+"]";
            }else{
                msg+= "["+" "+"]";
            }
        }


        if (columna == m) {
            msg+="\n";
            msg += printSnake(fila-1, 1);
        } else {
            msg += printSnake(fila, columna+1);
        }
        


        return msg;
    }
    public String printLadder(){
        String msg = "";
        msg = printLadder(n,1);
        return msg;
    }

    private String printLadder(int fila, int columna) {

        String msg = "";
        Tile tile = null;
        
        if (fila ==  0) {
            return "";
        }
        int number; 
        if (fila % 2 != 0){
            number = (fila - 1) * m + columna;
            tile = findTile(root, number);
            if(tile.getLadder()!=null){
                msg+= "["+tile.getLadder().getId()+"]";
            }else{
                msg+= "["+" "+"]";
            }
        }else {
            number = fila * m - columna + 1;
            tile =findTile(root, number);
            if(tile.getLadder()!=null){
                msg+= "["+tile.getLadder().getId()+"]";
            }else{
                msg+= "["+" "+"]";
            }
        }


        if (columna == m) {
            msg+="\n";
            msg += printLadder(fila-1, 1);
        } else {
            msg += printLadder(fila, columna+1);
        }
        


        return msg;

    }







    
}
