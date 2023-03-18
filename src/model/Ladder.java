package model;

public class Ladder implements Transportable{
    private int id;

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
        if(node.getLadder().getId()==id && node.isHead() == false){
           // node.setPlayers(node.getPlayers()+j);//esta linea debe ser cambiada por que desconozco como se implementan los jugadores en las casillas
        }else{
            transport(node.getNext(),id,j);
        }

    }
}
