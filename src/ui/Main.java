package ui;
import model.Board;
import model.Box;
import model.*;
import model.State;

import javax.swing.*;
import java.util.Scanner;
import java.util.Random;



public class Main {
    //Attribute
    private int height;
    private int width;
    //Relationship
    private PlayController pc;

    private static Scanner sc;
    private static boolean exit = false;
    //Builder
    public Main() {

        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.mainMenu();


    }

    private void mainMenu(){
        while (!exit){
            System.out.println( """
                                        
                    WELCOME TO
                    SNAKES AND LADDERS
                    ---------------------------------
                    PRESIONE
                                        
                    [1] jugar
                    [0] salir
                    ---------------------------------                    
                     \
                    """);
             String selectTemp = sc.nextLine();
             int select = Integer.parseInt(selectTemp);

             switch (select){
                 case 0:
                     exit = true;
                 break;
                 case 1:
                     menuAddPlayer();
                 break;
             }

        }
    }

    private void play(int turn,int nextturn) {

        int dice=0,fate=0;
        Random azar= new Random();

        System.out.println("turno del jugador "+ (turn+1));

        System.out.println(
                        "1. tirar dado \n" +
                        "2. ver el tablero \n"+
                                "3. salir");

         fate = sc.nextInt();
        switch (fate){
            case 1:
                dice= azar.nextInt(6)+1;
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

    private void menuAddPlayer(){

        while (!exit){

            System.out.println("""
                     
                     MENU SETTINGS 
                    +++++++++++++++++++++
                    
                     [1] Add Player
                     [2] Show players
                     [3] Start game 
                     [0] Back to main menu 
                    ++++++++++++++++++++++
                     \
                     
                    """);
            String optionTemp = sc.nextLine();
            int option = Integer.parseInt(optionTemp);


            switch (option ){
                case 0:
                    mainMenu();
                    break;
                case 1:
                   addPlayer();
                    break;
                case 2:
                    //pendiente metodo se mostrar jugadores

                    break;
                case 3:
                    System.out.println( """
                     
                     CREATING BOARD.....
                     \
                     
                    """);
                    operation();
                    break;

            }


        }
    }
    private  void addPlayer(){

        while (!exit){

            String[] player = new String[3];
            for (int i = 0; i< player.length;i++){
                System.out.println("player -> " + (i+1)
                                    + "\n select a simbol"
                                    +"\n  * ! O X % $ # + &");
                String simbol = sc.nextLine();
                int puntaje =0;
                int posicion = 0;

                //pendiente arbol para agragar usuario y puntaje



            }


            System.out.println("""

                     
                     [0] Back to main menu 
                     \
                    """);
            String optionTemp = sc.nextLine();
            int option = Integer.parseInt(optionTemp);

            switch (option ){
                case 0:
                    mainMenu();
                    break;

            }
        }

    }
    private void operation() {
        System.out.println("ingrese el alto del tablero");
        height= sc.nextInt();
        System.out.println("ingrese el ancho del tablero ");
        width = sc.nextInt();
        pc = new PlayController(height*width);
     //   pc.addPlayer("J1",1);
       // pc.addPlayer("J2",1);
        play(0,1);
    }
}