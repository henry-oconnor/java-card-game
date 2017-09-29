/*
 * Henry O'Connor
 */
package groupprojectcardgame;

import java.util.Stack;

/**
 *
 * @author henoc
 * A player's bank for betting and buy-ins
 */
public class Bank {
    int total;
    private Stack<Chip> whiteChips;
    private Stack<Chip> redChips;
    private Stack<Chip> blueChips;
    private Stack<Chip> greenChips;
    private Stack<Chip> blackChips;
            
    // buyin should be in multiples of 100
    // creates a reasonable chip distribution from buyin amount
    public Bank(int buyin){
        double remaining = buyin;
        total = buyin;
        if(buyin > 500){
            blackChips = new Stack<>();
            while(remaining / buyin > .8){
                blackChips.push(new Chip(Chip.Color.BLACK));
                remaining -= 100;
            }
        }
        if(buyin > 100){
            greenChips = new Stack<>();
            while(remaining / buyin > .5){
                greenChips.push(new Chip(Chip.Color.GREEN));
                remaining -= 25;
            }
                
        }
        blueChips = new Stack<>();
        while(remaining / buyin > .25){
            blueChips.push(new Chip(Chip.Color.BLUE));
            remaining -= 10;
        }
                
        redChips = new Stack<>();
        while(remaining / buyin > .1){
            redChips.push(new Chip(Chip.Color.RED));
            remaining -= 5;
        }
        
        whiteChips = new Stack<>();
        while(remaining > 0){
            whiteChips.push(new Chip(Chip.Color.WHITE));
            remaining -= 1;
        }
                
            
    }
    
    
    // takeWinnings()
    
            
    
}
