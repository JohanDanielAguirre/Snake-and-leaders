package model;

public class Tile {

    private int numberTile;

    private Tile next;
    private Tile previous;

    private Snake snakeHead;
    private Snake snakeTail;

    private Ladder start;
    private Ladder end;

    public Snake getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(Snake snakeHead) {
        this.snakeHead = snakeHead;
    }

    public Snake getSnakeTail() {
        return snakeTail;
    }

    public void setSnakeTail(Snake snakeTail) {
        this.snakeTail = snakeTail;
    }

    public Ladder getStart() {
        return start;
    }

    public void setStart(Ladder start) {
        this.start = start;
    }

    public Ladder getEnd() {
        return end;
    }

    public void setEnd(Ladder end) {
        this.end = end;
    }

    private Player[] players;

    public Tile(int numberTile) {
        this.numberTile = numberTile;
        players = new Player[3];
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
