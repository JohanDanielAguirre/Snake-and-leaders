package ui;

import model.SnakeMaster;

import java.util.Scanner;

public class Main {

    Scanner sc;
    SnakeMaster master;

    public Main(){
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        int op;

        do{
            op = main.Menu();
            main.executeOp(op);
       }while(op != 2);

    }

    public int Menu() {
        System.out.println("Bienvenid@ a Escaleras y serpientes\n" +
                "Que desea hacer?\n\n" +
                "[1] Jugar\n" +
                "[2] Salir\n\n");

        return sc.nextInt();
    }

    public void executeOp(int op){
        switch (op){
            case 1:
                initGame();
                break;
            case 2:
                System.out.println("Gracias por jugar :)");
                break;
        }
    }



    public void initGame(){
        System.out.println("Ingrese el ancho del tablero");
        int width = sc.nextInt();
        System.out.println("Ingrese el largo del tablero");
        int length = sc.nextInt();

        master = new SnakeMaster(width,length);

    }


}