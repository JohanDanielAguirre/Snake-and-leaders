package model;

import java.util.ArrayList;

public class Tile {

    private int numberTile;

    private Tile next;
    private Tile previous;

    private Transportable transport;
    
    public Transportable getTransport() {
        return transport;
    }

    public void setTransport(Transportable transport) {
        this.transport = transport;
    }

    private boolean isHead;

    private StateSnakeOrLadder state;
    private ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }

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

    public Tile(int numberTile) {
        this.numberTile = numberTile;
        state = StateSnakeOrLadder.FREE;
        players = new ArrayList<>();
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

    public void addPLayers(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public String printPlayers(){
        return printPLayers(0);
    }

    private String printPLayers(int n){
        String msg = "";

        if(players.size()<n){
            msg += players.get(n).getSimbolo();
            msg += printPLayers(n+1);
        }

        return msg;
    }


   
}
