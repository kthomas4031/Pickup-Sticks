/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NIM;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kyle Thomas
 */
public class Game {
    private int total, option;
    Scanner inpu = new Scanner(System.in);
    AdComp scaryComp = new AdComp();
    
    public void initializeGame(){
        int choice;
        do{
        System.out.println("Welcome to this lovely game of sticks!\n");
                
        System.out.println("Possibilities of Life:\n" +
                    "   (1)Play Against A Friend.\n" +
                    "   (2)Play Against A Random, Really Dumb Computer.\n" +
                    "   (3)Play With The Trained, Awesome Computer.\n" +
                    "Which option do you wish to partake in(1-3)? ");
        option = inpu.nextInt();
        switch (option){
                        case 1:  twoPlayer();
                                 break;

                        case 2:  randomAI();
                                 break;

                        case 3:  trainedAI();
                                 break;

                        default: System.out.println("Improper input. Try again.");
                                 break;
            }
        
            System.out.println("Would you like to play again? (1 for yes, anything else for no) ");
            choice = inpu.nextInt();
        }while (choice == 1);
    }
    
    public void twoPlayer(){
        System.out.println("Player 1, enter your label: ");
        Player player1 = new Player(inpu.next());
        System.out.println("Player 2, enter your identification: ");
        Player player2 = new Player(inpu.next());
        
        System.out.println("How many little sticks would you like on the table to start the game (10-500)? ");
            total = inpu.nextInt();
            while (total > 500 || total < 10){
                System.out.println("Learn to read. Enter a number between 10 and 500: ");
                total = inpu.nextInt();
            }
        
        Player currentPlayer;
        currentPlayer = player1;
        
        int takey;
        
        while (total > 0){
            total = currentPlayer.turn(total, currentPlayer);
            
            if (total <= 0){
                System.out.println(currentPlayer.getName() + ", you have lost the game");
                break;
            }
            
            if (currentPlayer == player1){
                currentPlayer = player2;
            } else
                currentPlayer = player1;
        }
    }
    
    public void randomAI(){
        System.out.println("Player, enter your name: ");
        Player player = new Player(inpu.next());
        
        int randy;
        Random rand = new Random();
        
        System.out.println("How many little sticks would you like on the table to start the game (10-500)? ");
            total = inpu.nextInt();
            while (total > 500 || total < 10){
                System.out.println("Learn to read. Enter a number between 10 and 500: ");
                total = inpu.nextInt();
            }
            
        while (total > 0){
            total = player.turn(total, player);
            
            if (total <= 0){
                System.out.println(player.getName() + ", you have lost");
                break;
            }
                        
            if (total != 1 && total <= 6)
                randy = total-1;
            else if(total == 1)
                randy=1;
            else
                randy = rand.nextInt(5)+1;
            total = total - randy;
            System.out.println("The computer takes " + randy + " sticks.");
            
            if (total <= 0){
                System.out.println("Congratulations " + player.getName() + ". You have achieved tranquility.");
                break;
            }
            
        }
    }

    public void trainedAI(){
        do{
        System.out.println("\nYou've been granted access to the Awesome Computer!\n" +
                "   (1) Train the Computer\n" +
                "   (2) Play against the computer\n" +
                "   (3) Show me the stats!\n" +
                "What Would you like to do? ");
        option = inpu.nextInt();
        switch (option){
            case 1: 
                AdComp trainerBot = new AdComp();
                scaryComp.trainAI(trainerBot);
                break;            
            case 2: 
                scaryComp.playAI();
                break;            
            case 3:
                scaryComp.printList();
                break;                
            default: 
                System.out.println("Improper input. Try again.");
                break;
        }
        }while(option != 1 && option != 2);
        
        do{
            System.out.println("\nWould you like to play against the final boss now? \n" +
                                "   (1) Yes, I hold no fear\n" +
                                "   (2) No, I'm Scared\n" +
                                "   (3) Show me the stats!\n");
            option = inpu.nextInt();
            switch (option){
                case 1:
                    scaryComp.playAI();
                    break;
                case 2:
                    System.out.println("What a wimp.");
                    break;
                case 3:
                    scaryComp.printList();
                    break;
                default:
                    System.out.println("There's literally only 3 options how do you mess that up?");
            }
        }while(option != 1 && option != 2);
        
        System.out.println("Do you wish to waste your time again? (1 for yes): ");
        if (inpu.nextInt() == 1){
            trainedAI();
        }
        
    }
    
    
    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }
    
}
