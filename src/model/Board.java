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
        int numSnakes = numOfTiles/3;
        addSnakesToTiles(65, numSnakes);

    }

    private void addSnakesToTiles(int id, int snakesLeft) {
        boolean flag2 = true;
        int numRandomHead = 0;
        Tile tile = null;

        while(flag2){
            numRandomHead = (int) (Math.random() * (numOfTiles-1));
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
        int numLadders = numOfTiles/3;
        addLaddersToTiles(1,numLadders);
    }

    private void addLaddersToTiles(int id, int laddersleft){
        int numRandomHead = (int) (Math.random() * (numOfTiles)) + 1;

        boolean flag = true;

        if (numRandomHead > 3 || numRandomHead < numOfTiles){
            Tile tile = findTile(root,numRandomHead);
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                tile.setLadder(new Ladder(id));
                tile.setState(StateSnakeOrLadder.OCCUPIEDLADDER);

                int startPlace;

                Tile tileStart = null;

                while(flag){
                    startPlace = objRandom.nextInt(numRandomHead);
                    startPlace = startPlace == 0? startPlace + 2 : startPlace;
                    tileStart = findTile(root,startPlace);
                    if (tileStart != null && tileStart.getState().equals(StateSnakeOrLadder.FREE)){
                        flag = false;
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

        if (fila ==  0) {
            return "";
        }
        int numero;
        if (fila % 2 != 0) {
            numero = (fila - 1) * m + columna;
        } else {
            numero = fila * m - columna + 1;
        }

        msg+= "["+numero+"]";

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
        msg = printSnake(n,1,root,1);
        return msg;
    }

    private String printSnake(int fila, int columna,Tile pointer,int counter) {

        String msg = "";
        if(pointer != null){
            if (fila ==  0) {
                return "" + counter;
            }
            if(pointer.getSnake()!=null){
                msg+= "["+pointer.getNumberTile()+pointer.getSnake().getId()+"]";
            }else{
                msg+= "["+" "+"]";
            }
            if (columna == m) {
                msg+="\n";
                msg += printSnake(fila+1, 1,pointer.getNext(),counter+1);
            } else {
                msg += printSnake(fila, columna+1,pointer.getNext(),counter+1);
            }
        }


        return msg;
    }
    public String printLadder(){
        String msg = "";
        msg = printLadder(n,1,root);
        return msg;
    }

    private String printLadder(int fila, int columna,Tile pointer) {

        String msg = "";

        if(pointer != null){
            if (fila == 0) {
                return "";
            }
            if (pointer.getLadder() != null) {
                msg += "[" + pointer.getLadder().getId() + "]";
            } else {
                msg += "[" + " " + "]";
            }
            if (columna == m) {
                msg += "\n";
                msg +=  printLadder(fila+1, 1, pointer.getNext());
            } else {
                msg +=  printLadder(fila, columna+1,pointer.getNext());
            }

        }

        return msg;

    }







    
}
