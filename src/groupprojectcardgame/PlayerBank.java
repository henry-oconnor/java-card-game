/*
 * Henry O'Connor
 */
package groupprojectcardgame;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author henoc
 * A player's bank for betting and buy-ins
 */
public class PlayerBank {
    private Integer total;
    private Stack<Chip> whiteChips;
    private Stack<Chip> redChips;
    private Stack<Chip> blueChips;
    private Stack<Chip> greenChips;
    private Stack<Chip> blackChips;
    
    private HashMap<Chip.Color,Stack<Chip>> bank;
            
    // buyin should be in multiples of 100
    // creates a reasonable chip distribution from buyin amount
    public PlayerBank(int buyin){
        double remaining = buyin;
        total = buyin;
        if(buyin > 500){
            blackChips = new Stack<>();
            bank.put(Chip.Color.BLACK, blackChips);
            while(remaining / buyin > .8){
                blackChips.push(new Chip(Chip.Color.BLACK));
                remaining -= 100;
            }
        }
        if(buyin > 100){
            greenChips = new Stack<>();
            bank.put(Chip.Color.GREEN, greenChips);
            while(remaining / buyin > .5){
                greenChips.push(new Chip(Chip.Color.GREEN));
                remaining -= 25;
            }
                
        }
        
        blueChips = new Stack<>();
        bank.put(Chip.Color.BLUE, blueChips);
        while(remaining / buyin > .25){
            blueChips.push(new Chip(Chip.Color.BLUE));
            remaining -= 10;
        }
        
        redChips = new Stack<>();
        bank.put(Chip.Color.RED, redChips);
        while(remaining / buyin > .1){
            redChips.push(new Chip(Chip.Color.RED));
            remaining -= 5;
        }
        
        whiteChips = new Stack<>();
        bank.put(Chip.Color.WHITE, whiteChips);
        while(remaining > 0){
            whiteChips.push(new Chip(Chip.Color.WHITE));
            remaining -= 1;
        }
                
            
    }
    
    /**
     * Takes a stack of chips and sorts them onto the correct player stacks
     * 
     * @param winnings 
     */
    public void takeWinnings(Stack<Chip> winnings){
        Chip temp;
        while(!winnings.empty()){
            temp = winnings.pop();
            total += temp.color.getValue();
            bank.get(temp.color).push(temp);
        }
    }
    
    /**
     * 
     * I'm thinking the user will click a chip or chip button, which will call this
     * function with the correct color and pop from the correct stack
     * 
     * @param c 
     * @return Chip
     */
    public Chip popChip(Chip.Color c){
        total -= c.getValue();
        return bank.get(c).pop();
    }
    
    /**
     * Returns a stack of chips of equal value to the input amount
     * 
     * @param amount
     * @return 
     */    
    
}
