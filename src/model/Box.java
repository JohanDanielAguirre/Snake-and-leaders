package model;
public class Box {
    //Attribute
    private int value;
    private State state;
    //Relationship
    private Box next;
    //Builder
    public Box(int value) {
        this.value = value;
        state = State.EMPTY;
    }
    //Getters and Setters
    public int getValue() {
        return value;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public Box getNext() {
        return next;
    }
    public void setNext(Box next) {
        this.next = next;
    }
}
