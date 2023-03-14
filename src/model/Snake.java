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
    public void transport() {

    }
}
