package model;
import java.util.Random;

public class Board {

    Random objRandom = new Random();
    private Tile root;
    private Tile tail;

    private int numOfTiles;
    private int n;
    private int m;

    public Board(int n, int m){
        this.numOfTiles = n*m;
    }
    public void createBoard(int numberTiles){
        createBoard(numOfTiles,0);
    }
    private void createBoard(int numOfTiles, int n){

        if (n == numOfTiles){
            return;
        }
        addTileAtTail(new Tile(n+1));
        createBoard(numOfTiles,n+1);
    }

    private void addTileAtTail(Tile node){

        if(root == null){
            root = node;
        }else{
            tail.setNext(node);
            node.setPrevious(tail);
        }
        tail = node;

    }

    public void addSnakes(){
        int numSnakes = numOfTiles/10;
        addSnakesToTiles(65,numSnakes);

    }

    private void addSnakesToTiles(int id, int snakesLeft){

        int numRandomHead = (int) (Math.random() * numOfTiles);

        if (numRandomHead > 3 || numRandomHead < numOfTiles){
            Tile tile = findTile(root,numRandomHead);
            if(tile != null && tile.getState().equals(StateSnakeOrLadder.FREE)) {

                tile.setSnake(new Snake(id));
                tile.setHead(true);
                int tailPlace = objRandom.nextInt(numRandomHead) + 1;
                Tile tileTail = findTile(root,tailPlace);
                if(tileTail != null && tileTail.getState().equals(StateSnakeOrLadder.FREE)){

                    tileTail.setSnake(new Snake(id));

                }

            }

        }
        if (snakesLeft != 0) addSnakesToTiles(id+1,snakesLeft-1);

    }

    public Tile findTile(Tile tileFound, int numTile){

        if(tileFound!=null && tileFound.getNumberTile()!=numTile){
            findTile(tileFound.getNext(),numTile);

        }

        return tileFound;
    }




    
}
