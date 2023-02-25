package model;
public class Player {
    //Attributes
    private int position;
    private String name;
    //Builder
    public Player(String name,int position) {
        this.position = position;
        this.name = name;
    }
    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
