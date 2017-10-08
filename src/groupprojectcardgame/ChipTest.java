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
    static final int BUYIN_VALUE = 200;
    
    public static void main(String args[]){
        
        PlayerBank p1Bank = new PlayerBank(BUYIN_VALUE);
        
        int chipCount = 0;
        Stack<Chip> betStk = p1Bank.placeBet(100);
        while(!betStk.isEmpty()){
            System.out.print(++chipCount + ": ");
            System.out.println(betStk.pop().toString());
        }
        
        Stack<Chip> allInStk = p1Bank.allIn();
        
        chipCount = 0;
        while(!allInStk.isEmpty()){
            System.out.print(++chipCount + ": ");
            System.out.println(allInStk.pop().toString());
        }
    }
}
