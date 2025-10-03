package TGame;

import java.util.Scanner;

public class Battle {
    private Characters player1;
    private Characters player2;
    private Scanner scan = new Scanner(System.in);
    private Layout layout = new Layout();

    public Battle(Characters p1, Characters p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    private void displayHealth() {
        System.out.printf("\n%s's Health: %d\tShield: %d%n", player1.getName(), player1.getHealth(), player1.getShield());
        System.out.printf("%s's Health: %d\tShield: %d%n", player2.getName(), player2.getHealth(), player2.getShield());
    }
    
    private void displaySkills(Characters p1, Characters p2) {
    System.out.println("\n" + p1.getName() + "'s Skills:\t\t" + p2.getName() + "'s Skills:");
    String[] s1 = p1.getSkillNames();
    String[] s2 = p2.getSkillNames();
    for (int i = 0; i < s1.length; i++) {
        System.out.printf("[%d] %-20s\t[%d] %s%n", (i+1), s1[i], (i+1), s2[i]);
    }
}


    public void start() {
        System.out.println("\n=== Battle Start! ===");

        
        
        while (player1.isAlive() && player2.isAlive()) {
            layout.clearScreen();
            displayHealth();
            displaySkills(player1, player2);
            
            // --- Player 1 turn ---
            
            
            System.out.println("\n" + player1.getName() + "'s turn. Choose skill (1-4): ");
            int choice1 = scan.nextInt();
            player1.useSkill(choice1, player2);
            player1.tickShield();
            player1.startTurn();

            if (!player2.isAlive()) break;

            
            
            // --- Player 2 turn ---
            if (player2.getName().equals("Computer")) {
                int choice2 = (int)(Math.random() * 4) + 1;
                System.out.println("\nComputer chose skill " + choice2);
                player2.useSkill(choice2, player1);
                player2.tickShield();
                player2.startTurn();
                
            } else {
                System.out.println("\n" + player2.getName() + "'s turn. Choose skill (1-4): ");
                int choice2 = scan.nextInt();
                player2.useSkill(choice2, player1);
                player2.tickShield();
                player2.startTurn();
            }

            player1.startTurn();
            player2.startTurn();

            layout.clearScreen();
        }
            
            

        System.out.println("\n=== Battle Over! ===");
        if (player1.isAlive()) System.out.println(player1.getName() + " wins!");
        else if (player2.isAlive()) System.out.println(player2.getName() + " wins!");
        else System.out.println("Both fighters fell!");
    }
}


