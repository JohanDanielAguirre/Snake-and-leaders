package model;

public class Player {

    private String symbol;
    private int score;

    public Player(String symbol) {
        this.symbol = symbol;
        score = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
