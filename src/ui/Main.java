package ui;

import model.Board;


import java.util.Scanner;

public class Main {
    
    private Board pc;
    private Scanner sc;
    private static boolean exit = false;
   
    public Main() {

        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.mainMenu();


    }

    private void mainMenu(){
        while (!exit){
            System.out.println( "WELCOME TO\n" +
                    "  SNAKES AND LADDERS\n" +
                    "  ---------------------------------\n" +
                    "  PRESIONE\n" +
                    "  \n" +
                    "  [1] jugar\n" +
                    "  [0] salir\n" +
                    "  ---------------------------------- \n" +
                    " \\");
            int select = sc.nextInt();
            sc.nextLine();

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




    private void menuAddPlayer(){

        while (!exit){

            System.out.println("  \n" +
                    "  MENU SETTINGS \n" +
                    "  +++++++++++++++++++++\n" +
                    " \n" +
                    "  [1] Add Player\n" +
                    "  [2] Show players\n" +
                    "  [3] Start game \n" +
                    "  [0] Back to main menu \n" +
                    "   ++++++++++++++++++++++\n" +
                    "                     \n" +
                    "                     ");

            int option = sc.nextInt();
            sc.nextLine();


            switch (option ){
                case 0:
                    mainMenu();
                    break;
                case 1:
                    //addPlayer();
                    break;
                case 2:
                    //pendiente metodo se mostrar jugadores

                    break;
                case 3:
                    System.out.println("  \n" +
                            " CREATING BOARD.....\n" +
                            "  \n" +
                            " ");
                    operation();
                    do {
                        
                    } while (!exit);


                    
                    break;

            }


        }
    }

    private void operation() {
        System.out.println("Ingrese el alto del tablero");
        int height= sc.nextInt();
        System.out.println("Ingrese el ancho del tablero ");
        int width = sc.nextInt();
        pc = new Board(height,width); 
            
    }

    
}
