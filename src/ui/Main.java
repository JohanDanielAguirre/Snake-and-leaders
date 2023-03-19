package ui;

import model.SnakeMaster;


import java.util.Scanner;

public class Main {
    //Relationship
    private SnakeMaster pc;
    private Scanner sc;
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
                    addPlayer();
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


            System.out.println("\n" +
                    "                     \n" +
                    "                     [0] Back to main menu \n" +
                    "                     \\");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option ){
                case 0:
                    mainMenu();
                    break;

            }
        }

    }
    private void operation() {
        System.out.println("Ingrese el alto del tablero");
        int height= sc.nextInt();
        System.out.println("Ingrese el ancho del tablero ");
        int width = sc.nextInt();
        pc = new SnakeMaster(height,width);
        showboard();
        System.out.println("No funciona si solo se muestra esta");
    }
    private void showboard(){
        System.out.println(pc.print());
        System.out.println(pc.print2());
        System.out.println(pc.print3());
    }
}
