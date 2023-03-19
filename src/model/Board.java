package model;

import java.util.Random;

public class Board {

    /**
     *objRandom, is used for creating random number 
     */
    Random objRandom;
    
    /**
     *root is the node Tile that represents the first Tile
     */
    private Tile root;
   
    /**
     *tail is the node Tile that represents the last Tile
     */
    private Tile tail;


    /**
     *number of Tiles in the board
     */
    private int numOfTiles;
    
    /**
     * n is the number of rows in the board
     */
    private int n;
    /**
     *m is the number of columns in the board
     */
    private int m;

    /**
     * Constructor method of the class Board
     * @param n the number of rows
     * @param m the number of columns
     */
    public Board(int n, int m) {
        this.numOfTiles = n * m;
        this.n = n;
        this.m = m;
        objRandom = new Random();
        createBoard(numOfTiles);
    }

    /**
     * Call the private method createBoard
     * @param numberTiles total of Tiles to be put in the board
     */
    public void createBoard(int numberTiles) {
        createBoard(numOfTiles, 0);
        addSnakes();
        addLaddersToTiles();
    }

    /**
     * Name : createBoard
     * create the board
     * @param numOfTiles the number of tile to be created
     * @param n counter to put the number in the Tile
     */
    private void createBoard(int numOfTiles, int n) {

        if (n == numOfTiles) {
            return;
        }
        addTileAtTail(new Tile(n + 1));
        createBoard(numOfTiles, n + 1);
    }

    /**
     * Name : addTileAtTile
     * @param node add a node Tile in the last position of the list
     */
    private void addTileAtTail(Tile node) {

        if (root == null) {
            root = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;

    }
    /**
     * Name: addSnakes
     * call the private method addSnakesToTiles
     */
    public void addSnakes() {
        int numSnakes = m/3;
        addSnakesToTiles(65, numSnakes);

    }

    /**
     * Name : addSnakesTiles
     * create the snakes in the board
     * pre: board != null
     * pos : snakes created in board and stored in Tile
     * @param id : the id of the snake
     * @param laddersleft the number of snakes left to be created
     */
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

    /**
     * Name: addLaddersToTiles
     * call the private method addLaddersToTiles
     */
    public void addLaddersToTiles(){
        int numLadders = m/3;
        addLaddersToTiles(1,numLadders);
    }

    /**
     * Name : addLaddersTiles
     * create the ladders in the board
     * pre: board != null
     * pos : ladders created in board and stored in Tile
     * @param id : the id of the ladder
     * @param laddersleft the number of ladders left to be created
     */
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

    /**
     * Name: findTile
     * @param tileFound the tile found by search
     * @param numTile the id which is going to be compared to the search
     * @return the tile found 
     */
    public Tile findTile(Tile tileFound, int numTile){

        if(tileFound!=null && tileFound.getNumberTile()!=numTile){
           return findTile(tileFound.getNext(),numTile);
        }else{
            return tileFound;
        }


    }
   /**
     * Name : printBoard
     * call the private method printBoard
     * @return msg with the board printed
     */
    public String printBoard(){
        String msg = "";
        msg = printBoard(n,1);
        return msg;
    }
      /**
     * Name : printBoard
     * print the board with the palyers in it
     * pre : board != null
     * pos : print board
     * @param fila int: number of rows
     * @param columna int: number of columns
     * @return msg with the board printed
     */
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

    /**
     * Name : printSnake
     * call the private method printSnake
     * @return msg with the board printed
     */
    public String printSnake(){
        String msg = "";
        msg = printSnake(n,1);
        return msg;
    }

    /**
     * Name : printSnaker
     * print the board only showing the snakes
     * pre : board != null
     * pos : print board
     * @param fila int: number of rows
     * @param columna int: number of columns
     * @return msg with the board printed
     */
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

    /**
     * Name: printLadder
     * call the private method printLadder, shows the board without number but ladders
     * @return msg with the board printed 
     */
    public String printLadder(){
        String msg = "";
        msg = printLadder(n,1);
        return msg;
    }

    
    /**
     * Name : printLadder
     * print the board only showing the ladders
     * pre : board != null
     * pos : print board
     * @param fila int: number of rows
     * @param columna int: number of columns
     * @return msg with the board printed
     */
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
