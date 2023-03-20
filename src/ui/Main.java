package ui;

import model.Board;
import model.ABB;


import java.util.Scanner;

public class Main {
    
    private Board pc;
    private ABB scores;
    private Scanner sc;
    private static boolean exit = false;
   
    public Main() {

        sc = new Scanner(System.in);
        scores = new ABB();
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
                    operation();
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
                    "  [2] Show players by score\n" +
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
                    if(scores.getRoot() != null){
                        System.out.println(scores.inorder());
                    }else{
                        System.out.println("No existen jugadores en la scoreBoard");
                    }

                    break;
                case 3:
                    System.out.println("  \n" +
                            " CREATING BOARD.....\n" +
                            "  \n" +
                            " ");
                            
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
                System.out.println("Dado:" + dice);
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

           if(n > 2){
                n = 0;
           }
        }while(!pc.checkwin(pc.getPlayers()[n].getPosition().getNumberTile()));
        pc.EndGame();

        pc.calculateScore(pc.getPlayers()[n]);

        scores.addNode(pc.getPlayers()[n]);



        
    }

    public int menuGame(int n){
        int op = 0;

        System.out.println("Turno del jugador " + pc.getPlayers()[n].getSimbolo() + "\n" + 
        "[1] Tirar dado\n" + 
        "[2] Ver Serpientes\n"  + 
        "[3] Ver Escaleras\n");

        op = sc.nextInt();
        sc.nextLine();

        return op;
    }

    private void operation() {
        System.out.println("Ingrese el alto del tablero");
        int height= sc.nextInt();
        System.out.println("Ingrese el ancho del tablero ");
        int width = sc.nextInt();
        boolean flag = false;
        int snakes = 0;
        int entitiesLadder = 0;
        while(!flag){
            System.out.println("Ingrese el numero de Escaleras ");
            entitiesLadder = sc.nextInt();
            System.out.println("Ingrese el numero de Serpientes");
            snakes = sc.nextInt();

            if(((entitiesLadder + snakes) * 2)<(height*width)){
                flag = true;
            }else{
                System.out.println("Ingrese numeros validos. Son demasiadas escaleras o serpientes");
            }


        }
        

        pc = new Board(height,width,entitiesLadder,snakes); 
    
    }

    public int throwDice(){

        return (int) (Math.random() * 6) + 1;

    }

    private void addPlayer(){
        int n = 0;
        do{
            System.out.println("player -> " + (n+1)
            + "\n select a simbol"
            +"\n  * ! O X % $ # + &");
            String symbol = sc.nextLine();
            pc.addPlayer(symbol, n);

            n++;


        }while(n<3);
       
    }

    
}
