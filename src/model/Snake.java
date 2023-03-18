package model;

public class Snake implements Transportable{

    private char id;

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
    private void transport(Tile tile, char id, Player j){
        if(tile.getSnake().getId() == id && !tile.isHead()){
           //tile.setPlayers(tile.getPlayers()+j);//esta linea debe ser cambiada por que desconozco como se implementan los jugadores en las casillas
        }else{
            transport(tile.getPrevious(),id,j);
        }

    }
}
