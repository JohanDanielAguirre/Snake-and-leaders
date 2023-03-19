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
                    game();;
                    break;

            }


        }
    }

    public void game(){
        int n = 0;
        int op = 0;
        int dice = 0;
        pc.StartGame();
        do{

            
            System.out.println("\n\n"+pc.printBoard()+ "\n\n");
            op =  menuGame(n);

           

           switch (op) {
            case 1:
                dice = throwDice();
                pc.movePlayer(dice,pc.getPlayers()[n].getSimbolo());
                break;
            case 2: 

                System.out.println(pc.printSnake());
                break;
            
            case 3: 
                System.out.println(pc.printLadder());
                break;
           }

           n++;

           if(n > 3){
                n = 0;
           }
        }while(!pc.checkwin(pc.getPlayers()[n].getPosition().getNumberTile(),dice));
        pc.EndGame();

        pc.calculateScore(pc.getPlayers()[n]);

        
    }

    public int menuGame(int n){
        int op = 0;

        System.out.println("Turno del jugador " + pc.getPlayers()[n] + "\n" + 
        "[1] Tirar dado\n" + 
        "[2] Ver escaleras\n"  + 
        "[3] Ver serpientes\n");

        op = sc.nextInt();
        sc.nextLine();

        return op;
    }

    private void operation() {
        System.out.println("Ingrese el alto del tablero");
        int height= sc.nextInt();
        System.out.println("Ingrese el ancho del tablero ");
        int width = sc.nextInt();
        pc = new Board(height,width); 
        System.out.println(pc.printBoard());
        System.out.println(pc.printSnake());
        System.out.println(pc.printLadder());
            
    }

    public int throwDice(){

        return (int) (Math.random() * 6) + 1;

    }

    
}
