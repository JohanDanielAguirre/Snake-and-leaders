package model;

public class Snake implements Transportable{

    private char id;
    private Tile tail;

    public void setTail(Tile tail) {
        this.tail = tail;
    }

    public Tile getTail() {
        return tail;
    }

    public Snake(int id){
        this.id = (char) id;
    }

    public char getId() {
        return id;
    }

    @Override
    public void transport(Tile actual, Player j) {
        transport(actual,id,j);
    }

    private void transport(Tile actual, char id, Player j){
        
        if(actual.isHead()){
            
            actual.removePlayer(j);
            tail.addPLayers(j);
            
        }

    }

    public String toString(){
        return id +"";
    }
}
