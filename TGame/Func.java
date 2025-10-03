package TGame;

import java.util.Scanner;

public class Func {

    Scanner scan = new Scanner(System.in);
    Layout layout = new Layout();

    private Characters chooseCharacter(int choice) {
    switch (choice) {
        case 1:
            return new Lance(); // health = 100, shield = 10
        case 2:
            return new Khai(); // health = 120, shield = 5
        default:
            return new Khai(); // fallback
        }
    }


     public void pVsp() {
        

        System.out.print("Player 1, choose your character (1-8): ");
        Characters player1 = chooseCharacter(scan.nextInt());

        System.out.print("Player 2, choose your character (1-8): ");
        Characters player2 = chooseCharacter(scan.nextInt());

        layout.clearScreen();
        System.out.println("               " + player1.getName() + " Vs " + player2.getName());
        System.out.println();


        pressAnyKey();
        layout.clearScreen();
        
        Battle battle = new Battle(player1, player2);
        battle.start();
     } 

    void pVsc(){
        int choice1;
        String player1 = "";

            System.out.print("          Select your Character: ");
            choice1 = scan.nextInt();

            switch (choice1) {
            case 1: player1 = "Character 1"; break;
            case 2: player1 = "Character 2"; break;
            case 3: player1 = "Character 3"; break;
            case 4: player1 = "Character 4"; break;
            case 5: player1 = "Character 5"; break;
            case 6: player1 = "Character 6"; break;
            case 7: player1 = "Character 7"; break;
            case 8: player1 = "Character 8"; break;
            default: player1 = "Character 1"; 
        }

        System.out.print("        You've selected " + player1);

        pressAnyKey();
 
    }


    public void pressAnyKey() {
    System.out.println("         Press Enter to continue...");
    try {
        System.in.read();  
    } catch (Exception e) {
        e.printStackTrace();
    }
}

        
    

}

