package ui;
import model.Board;
import model.Box;
import model.PlayController;
import model.State;

import java.util.Scanner;
public class Main {
    //Attribute
    private int height;
    private int width;
    //Relationship
    private PlayController pc;
    private Board bd;
    private Scanner sc;
    //Builder
    public Main() {
        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Main m = new Main();
        //m.test();
        m.operation();
    }
    private void operation() {
        System.out.println("ingrese el alto del tablero");
        height= sc.nextInt();
        System.out.println("ingrese el ancho del tablero ");
        width = sc.nextInt();
        pc = new PlayController(height*width);
        pc.addPlayer("J1",1);
        pc.addPlayer("J2",1);
        play(0,1);
    }
    private void play(int turn,int nextturn) {
        int dice=0,fate=0;
        System.out.println("turno del jugador "+ (turn+1));
        System.out.println(
                        "1. tirar dado \n" +
                        "2. ver el tablero \n"+
                                "3. salir");
         fate = sc.nextInt();
        switch (fate){
            case 1:
                dice=(int)(Math.random()*6+1);
                System.out.println("Dado: "+dice);
                if(!checkwin(pc.getPlayerPosition(turn),dice)){
					pc.setPositionOnBoard(turn,(pc.getPlayerPosition(turn)+dice));
                }else {
                    System.out.println("el jugador "+(turn+1)+ " gano la partida");
					return;
                }
            break;
			case 2:
				pc.print(height,width,0);
				  System.out.println();
			break;
            case 3: 
				System.out.println("el jugador "+(nextturn+1)+" gano la partida"); 
			return;
        }
		play(nextturn,turn);
    }
    private boolean checkwin(int playerPosition, int dice) {
		if((playerPosition+dice)>=(height*width)){
			return true;
		}else{
			return false;
		}
    }
}