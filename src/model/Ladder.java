package model;

public class Ladder implements Transportable{
    private int id;

    private Tile end;

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
    private void transport(Tile node, int id, Player j){
        if(node.getTransport().toString().equals(id+"") && node.isHead()){
           node.removePlayer(j);
           end.addPLayers(j);
        }

    }

    public String toString(){
        return id +"";
    }

}
