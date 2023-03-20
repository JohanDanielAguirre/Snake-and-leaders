package model;
import java.time.*;
import java.util.Random;

public class Board {

    /**
     *objRandom, is used for creating random number 
     */
    Random objRandom;

    private Instant start= null;
    private Instant end = null;
    private Clock clock = Clock.systemDefaultZone();
    
    /**
     *root is the node Tile that represents the first Tile
     */
    private Tile root;

    private Player[] players; 
   
    public Player[] getPlayers() {
        return players;
    }

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
    public Board(int n, int m,int entity,int numSnakes) {
        this.numOfTiles = n * m;
        this.n = n;
        this.m = m;
        objRandom = new Random();
        createBoardTotal(numOfTiles,entity,numSnakes);
        players = new Player[3];
    }

    /**
     * Call the private method createBoard
     * @param numberTiles total of Tiles to be put in the board
     */
    public void createBoardTotal(int numberTiles,int numLadder,int numSnakes) {
        createBoard(numOfTiles,0);
        addSnakes(numSnakes);
        addLaddersToTiles(numLadder);
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
    public void addSnakes(int numSnakes) {
        
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
            numRandomHead = (int) (Math.random() * (numOfTiles));
            if(numRandomHead == 0){
                numRandomHead = 1;
            }
            numRandomHead = numRandomHead <= 1? numRandomHead + 2 : numRandomHead;
            tile = findTile(root, numRandomHead);

            if (tile!= null && tile.getState().equals(StateSnakeOrLadder.FREE)) {
                flag2 = false;
            }
        }

        boolean flag1 = true;

        if (numRandomHead >= 3 && numRandomHead < numOfTiles) {

            if (tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                Snake snakeHead = new Snake(id);
                tile.setTransport(snakeHead);;
                tile.setHead(true);
                tile.setState(StateSnakeOrLadder.OCCUPIEDSNAKE);
                Tile tileTail = null;
                int tailPlace = 0;

                while (flag1) {

                    tailPlace = objRandom.nextInt(numRandomHead + 1);
                    tailPlace= tailPlace == 0? tailPlace + 2 : tailPlace;
                    tailPlace = tailPlace == 1? tailPlace + 1 : tailPlace;
                    tileTail = findTile(root, tailPlace);

                    if (tileTail!= null && tileTail.getState().equals(StateSnakeOrLadder.FREE)) {
                        flag1 = false;
                    }

                }

                if (tileTail.getState().equals(StateSnakeOrLadder.FREE) ) {
                    
                    tileTail.setTransport(snakeHead);
                    tileTail.setState(StateSnakeOrLadder.OCCUPIEDSNAKE);
                    snakeHead.setTail(tileTail);
                    

                }

            }

        }
        if (snakesLeft > 1) addSnakesToTiles(id + 1, snakesLeft - 1);

    }

    /**
     * Name: addLaddersToTiles
     * call the private method addLaddersToTiles
     */
    public void addLaddersToTiles(int numLadders){
        
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
            if(numRandomHead == 0){
                numRandomHead = 1;
            }
            numRandomHead = numRandomHead <= 1? numRandomHead + 1 : numRandomHead;
            tile = findTile(root,numRandomHead);
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)){
                flag2 = false;
            }
        }
        

        boolean flag1 = true;

        if (numRandomHead > 3 || numRandomHead < numOfTiles){
            
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                Ladder ladderEnd = new Ladder(id);
                tile.setTransport(ladderEnd);
                tile.setState(StateSnakeOrLadder.OCCUPIEDLADDER);
                ladderEnd.setEnd(tile);

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

                    
                    tileStart.setTransport(ladderEnd);
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

        if(tile.getPlayers().isEmpty()){
            msg+= "["+tile.getNumberTile()+"]";
        }else{
            msg+= "["+tile.getNumberTile()+tile.printPlayers()+"]";
        }
        

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
            if(tile.getTransport()!=null && tile.getTransport() instanceof Snake){
                msg+= "["+tile.getTransport().toString()+"]";
            }else{
                msg+= "["+" "+"]";
            }
        }else {
            number = fila * m - columna + 1;
            tile =findTile(root, number);
            if(tile.getTransport()!=null && tile.getTransport() instanceof Snake){
                msg+= "["+tile.getTransport().toString()+"]";
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
            if(tile.getTransport()!=null && tile.getTransport() instanceof Ladder){
                msg+= "["+tile.getTransport().toString()+"]";
            }else{
                msg+= "["+" "+"]";
            }
        }else {
            number = fila * m - columna + 1;
            tile =findTile(root, number);
            if(tile.getTransport()!=null && tile.getTransport() instanceof Ladder){
                msg+= "["+tile.getTransport().toString()+"]";
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

    public void movePlayer(int dice, String id){
        
        Player player = findPlayer(id, 0);
        int tiles2Move = 0;
        Tile destiny = null;

        if(player != null){
            tiles2Move = dice + player.getPosition().getNumberTile();
            destiny = findTile(root, tiles2Move);
        }

        if(!checkwin(player.getPosition().getNumberTile())){
        
            if(tiles2Move > 0){
                player.setPosition(destiny);
            }
            if(player != null && player.getPosition().isHead()){
                player.getPosition().getTransport().transport(destiny, player);
            }
        }
        
    }

    public Player findPlayer(String symbol, int n){

        if(n > players.length-1){
            return null;
        }else if(players[n].getSimbolo().equals(symbol)){
            return players[n];
        }else{
            return findPlayer(symbol, n+1);
        }
    }

    public boolean checkwin(int playerPosition) {
        return (playerPosition)>=(m*n);
    }

    public void StartGame(){
        start = clock.instant();
    }

    public void EndGame(){
        end = clock.instant();
    }

    public void calculateScore(Player winner){
       int score = (600-((int)Duration.between(start, end).getSeconds())) * 10;
        winner.setScore(score);
    }

    public void addPlayer(String name,int n) {
        players[n] = new Player(name);
        players[n].setPosition(root);
        root.addPLayers(players[n]);
    }
    
}
