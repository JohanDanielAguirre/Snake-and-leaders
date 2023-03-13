package model;

public class Board {

    private Tile root;

    public Board(int numberOfTiles){
        root = new Tile(1);
        createBoard(numberOfTiles);
    }

    public void createBoard(int numberTiles){
        addTile(root,1,numberTiles);
    }

    private void addTile(Tile pointer, int n, int numTiles){

        if(pointer != null){
            
        }

    }


    

    
}
