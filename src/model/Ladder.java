package model;

public class Ladder implements Transportable{
    private int id;

    private Tile end;

    public void setEnd(Tile end) {
        this.end = end;
    }

    public Ladder(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
    @Override
    public void transport(Tile actual, Player j) {
        transport(actual,id,j);
    }
    private void transport(Tile actual, int id, Player j){
        if(actual.isHead()){
            
            actual.removePlayer(j);
            end.addPLayers(j);
            
        }

    }

    public String toString(){
        return id +"";
    }

}
