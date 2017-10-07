/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

import java.util.Stack;

/**
 *
 * @author henoc
 */
public class ChipTest {
    static final int BUYIN_VALUE = 1000;
    
    public static void main(String args[]){
        
        
        Chip c = new Chip(Chip.Color.BLACK);
        
        PlayerBank bank = new PlayerBank(BUYIN_VALUE);
        
        Stack<Chip> allInStk = bank.allIn();
        
        int chipCount = 0;
        while(!allInStk.isEmpty()){
            System.out.print(++chipCount + ": ");
            System.out.println(allInStk.pop().toString());
        }
    }
}
