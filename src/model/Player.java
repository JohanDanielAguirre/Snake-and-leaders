package model;

public class Player {

    //Attributes

    private int score;
    private String simbolo;

    public Player(String simbolo) {
        this.simbolo = simbolo;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int puntaje) {
        this.score = puntaje;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

}