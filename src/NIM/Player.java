/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NIM;

import java.util.Scanner;

/**
 *
 * @author Kyle Thomas
 */
public class Player {
    private String name;
    private Scanner inpu = new Scanner(System.in);
    private int takey;

    public Player() {
        name = "Loser";
    }

    public Player(String names) {
        name = names;
    }

    public int turn(int total, Player player){
        System.out.println("There are " + total + " sticks on the table.\n" +
                    player.getName() + ": How many sticks do you want to take (1-5)? ");
        
            takey = inpu.nextInt();
            
            while (takey > 5 || takey < 1){
                System.out.println("Invalid input. How many sticks would you like to take (1-5)? ");
                takey = inpu.nextInt();
            }
        total -= takey;
        
        return total;
    }

    public Scanner getInpu() {
        return inpu;
    }

    public int getTakey() {
        return takey;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInpu(Scanner inpu) {
        this.inpu = inpu;
    }

    public void setTakey(int takey) {
        this.takey = takey;
    }
    
    
}
