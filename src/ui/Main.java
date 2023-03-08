package ui;
import model.Board;
import model.Box;
import model.PlayController;
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
    private Board bd;
    private static Scanner sc;
    private static boolean exit = false;
    //Builder
    public Main() {
        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.mainMenu();
        //m.test();
       // m.operation();
       // m.menuAddPlayer();

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
    private void mainMenu(){
        while (!exit){
            System.out.println( """
                     
                    [1] jugar
                    [0] salir
                    
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
    private void menu(){
        while (!exit){
            System.out.println( """
                     
                    [1] Tirar dados
                    [2] ver Tablero
                    [3] salir
                     \
                    """);
            String optionTemp = sc.nextLine();
            int option = Integer.parseInt(optionTemp);
            switch (option){
                case 1 :
                    break;
                case 2:
                    break;
                case 3:
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

                     [1] Add Player
                     [2] Show players
                     [0] Back to main menu \
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

                    break;
            }


        }
    }
    private  void addPlayer(){

        while (!exit){
            System.out.println("select an user");
            String name = sc.nextLine();
            String[] users = name.split("");


            System.out.println("""

                     [1] Add  other Player
                     [0] Back to main menu 
                     \
                    """);
            String optionTemp = sc.nextLine();
            int option = Integer.parseInt(optionTemp);

            switch (option ){
                case 0:
                    mainMenu();
                    break;
                case 1:

                    break;
            }
        }




    }

}