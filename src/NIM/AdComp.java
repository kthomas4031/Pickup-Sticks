/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NIM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kyle
 */
public class AdComp {
    private int total;
    private List<ArrayList<Integer>> masterList;
    private int [] tempList;
    private Scanner inpu = new Scanner(System.in);
    

    public AdComp() {
        masterList = new ArrayList<>();
        
        //Make it so that if the # of sticks is less than 6, the computer will only take the # of sticks to leave it at 1
        for (int i = 0; i < 6 ; i++){
            ArrayList<Integer> arrayList = new ArrayList<>();
            //If there's only 1 stick left, the computer will take it.
            if (i == 0)
                arrayList.add(1);
            else
                arrayList.add(i);
            masterList.add(arrayList);
        }
        
        //Add the 1-5 option for each other instance of sticks remaining
        for (int i = 6; i < 500 ; i++){
            ArrayList<Integer> arrayList = new ArrayList<>();
            
            arrayList.addAll(Arrays.asList(1, 2, 3, 4, 5));
            masterList.add(arrayList);
        }
    }

    public void printList(){
      StringBuilder result = new StringBuilder();
      for(int i = 0; i < masterList.size(); i++){
          result.append((i+1) + ": ");
          for(int j = 0; j < masterList.get(i).size(); j++){
              result.append(masterList.get(i).get(j));
          }
          result.append("\n");
      }
        System.out.println(result);
    }
    
    public void trainAI(AdComp trainer){
        int takey;
        Random rand = new Random();
        
        
        System.out.println("How many games would you like the AI to train? ");
        int games = inpu.nextInt();
        
        for (int i = 0 ; i < games ; i++){
            //Create temporary 2D ArraYList for both computers that will be wiped and reset on each iteration of the training games
            tempList = new int[500];
            
            trainer.tempList = new int[500];

            //total will reset to max number of stick allotted in the game each iteration
            total = 500;            
            //Conduct turns as you would in a normal game, just without any output to the user
            while (total > 0){
                //Gets random number based on the number of options from array within the masterlist based on number of sticks remaining(minus 1 to get proper index)
                takey = masterList.get(total-1).get(rand.nextInt(masterList.get(total-1).size()));
                
                //add random number to index
                tempList[total-1]=takey;
                total -= takey;
                
                //Create win condition (technically lose condition for this computer)for each game that will teach the computer the winning moves and end the game
                if (total < 1){
                    learn(trainer.tempList);
                    break;
                }
                
                //Opponent computer's turn (Takes from same masterlist because why not?[I don't feel like adding more dots])
                //Gets random number based on the number of options from array within the masterlist based on number of sticks remaining(minus 1 to get proper index)
                takey = masterList.get(total-1).get(rand.nextInt(masterList.get(total-1).size()));
                
                //add random number to index
                trainer.tempList[total-1]=takey;
                total -= takey;
                
                //Win condition now makes the computer learn from the current
                if (total < 1){
                    learn(tempList);
                    break;
                }
            }
        }
    }
    
    public void playAI(){
        System.out.println("Player, enter your name: ");
        Player player = new Player(inpu.next());        
        int takey, randy;
        Random rand = new Random();
        
        System.out.println("How many sticks do you wish to start with? ");
        total = inpu.nextInt();
        
        while (total > 0){

                total = player.turn(total, player);
            if (total <= 0){
                System.out.println(player.getName() + ", you have been defeated. You can't win against this.");
                break;
            }
            
            takey = masterList.get(total-1).get(rand.nextInt(masterList.get(total-1).size()));
            total -= takey;
            System.out.println("The computer takes " + takey + " sticks.");
            
            if (total <= 0){
                System.out.println(player.getName() + "... how? I don't believe it. You've won? Maybe you should train the AI a bit more before playing. Cheater.");
                break;
            }
        }
    }
    
    public void learn(int [] tempList){
        for (int i=0;i < 500;i++){
            if (tempList[i] > 0)
                masterList.get(i).add(tempList[i]);
        }
    }
    
    
    

    public int[] getTempList() {
        return tempList;
    }

    public void setTempList(int[] tempList) {
        this.tempList = tempList;
    }
    
    public Scanner getInpu() {
        return inpu;
    }

    public void setInpu(Scanner inpu) {
        this.inpu = inpu;
    }
 
    public List<ArrayList<Integer>> getMasterList() {
        return masterList;
    }

    public void setMasterList(List<ArrayList<Integer>> masterList) {
        this.masterList = masterList;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
       
}
