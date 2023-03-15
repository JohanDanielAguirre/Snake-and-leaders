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
    public void transport() {

    }
}
