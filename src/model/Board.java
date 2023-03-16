package model;
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
        int numSnakes = numOfTiles / 10;
        addSnakesToTiles(65, numSnakes);

    }

    private void addSnakesToTiles(int id, int snakesLeft) {

        int numRandomHead = (int) (Math.random() * numOfTiles);
        boolean flag = true;

        if (numRandomHead > 3 || numRandomHead < numOfTiles) {
            Tile tile = findTile(root, numRandomHead);
            if (tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                tile.setSnake(new Snake(id));
                tile.setHead(true);
                tile.setState(StateSnakeOrLadder.OCCUPIEDSNAKE);
                Tile tileTail = null;
                int tailPlace = 0;

                while (flag) {
                    tailPlace = objRandom.nextInt(numRandomHead);
                    tileTail = findTile(root, tailPlace);
                    if (tileTail.getState().equals(StateSnakeOrLadder.FREE)) {
                        flag = false;
                    }

                }

                if (tileTail.getState().equals(StateSnakeOrLadder.FREE)) {

                    tileTail.setSnake(new Snake(id));
                    tileTail.setState(StateSnakeOrLadder.OCCUPIEDSNAKE);

                }

            }

        }
        if (snakesLeft != 0) addSnakesToTiles(id + 1, snakesLeft - 1);

    }

    public void addLaddersToTiles(){
        int numLadders = numOfTiles/10;
        addLaddersToTiles(1,numLadders);
    }

    private void addLaddersToTiles(int id, int laddersleft){
        int numRandomHead = (int) (Math.random() * numOfTiles);

        boolean flag = true;
        if (numRandomHead > 3 || numRandomHead < numOfTiles){
            Tile tile = findTile(root,numRandomHead);
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                tile.setLadder(new Ladder(id));
                tile.setState(StateSnakeOrLadder.OCCUPIEDLADDER);

                int startPlace = 0;

                Tile tileStart = null;

                while(flag){
                    startPlace = objRandom.nextInt(numRandomHead);
                    tileStart = findTile(root,startPlace);

                    if (tile.getState().equals(StateSnakeOrLadder.FREE)){
                        flag = false;
                    }
                }

                if(tileStart != null && tileStart.getState().equals(StateSnakeOrLadder.FREE)){

                    tileStart.setLadder(new Ladder(id));
                    tileStart.setHead(true);
                    tileStart.setState(StateSnakeOrLadder.OCCUPIEDLADDER);

                }
            }
        }
        if (laddersleft != 0) {addLaddersToTiles(id+1,laddersleft-1);}
    }

    public Tile findTile(Tile tileFound, int numTile){

        if(tileFound!=null && tileFound.getNumberTile()!=numTile){
            findTile(tileFound.getNext(),numTile);

        }

        return tileFound;
    }




    
}
