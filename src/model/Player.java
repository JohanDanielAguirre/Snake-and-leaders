package model;
public class Player {

    //Attributes

    private int position;
    private int puntaje;
    private String simbolo;

    public Player( int puntaje, String simbolo) {
        this.position = position;
        this.puntaje = puntaje;
        this.simbolo = simbolo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

}
