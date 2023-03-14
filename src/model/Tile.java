package model;

public class Tile {

    private int numberTile;

    private Tile next;
    private Tile previous;

    private Snake snake;
    private boolean isHead;
    private Ladder ladder;

    private StateSnakeOrLadder state;

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public StateSnakeOrLadder getState() {
        return state;
    }

    public void setState(StateSnakeOrLadder state) {
        this.state = state;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    private Player[] players;

    public Tile(int numberTile) {
        this.numberTile = numberTile;
        players = new Player[3];
        state = StateSnakeOrLadder.FREE;
    }

    public int getNumberTile() {
        return numberTile;
    }

    public void setNumberTile(int numberTile) {
        this.numberTile = numberTile;
    }

    public Tile getNext() {
        return next;
    }

    public void setNext(Tile next) {
        this.next = next;
    }

    public Tile getPrevious() {
        return previous;
    }

    public void setPrevious(Tile previous) {
        this.previous = previous;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
