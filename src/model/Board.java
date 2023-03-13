package model;

public class Board {

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

    private void addSnakesToTiles(int numberOfTiles,char id){

        int numRandom = (int) Math.random() * numberOfTiles;

        if (numRandom > 1 || numRandom < numberOfTiles){
            Tile tile = findTile(root,numRandom);
            if(tile != null && tile.getSnake().equals(StateSnakeOrLadder.OCCUPIEDLADDER)&&tile.getSnake().equals(StateSnakeOrLadder.OCCUPIEDSNAKE)) {
                tile.setSnake(new Snake('A'));
            }
        }

    }

    public Tile findTile(Tile tileFound, int numTile){

        if(tileFound!=null && tileFound.getNumberTile()!=numTile){
            findTile(tileFound.getNext(),numTile);

        }

        return tileFound;
    }




    
}
