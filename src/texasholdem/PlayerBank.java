package texasholdem;

/*
 * Henry O'Connor
 */


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
        bank = new HashMap<>();
        double remaining = buyin;
        total = buyin;
        
        blackChips = new Stack<>();
        bank.put(Chip.Color.BLACK, blackChips);
        if(remaining > 500){
            while(remaining / buyin > .7){
                blackChips.push(new Chip(Chip.Color.BLACK));
                remaining -= 100;
            }
        }
        
        greenChips = new Stack<>();
        bank.put(Chip.Color.GREEN, greenChips);
        if(remaining > 100){
            while(remaining / buyin > .4){
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
        while(remaining / buyin > .05){
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
     * Takes a stack of chips and sorts them onto the correct player stacks,
     * increases total
     * 
     * @param winnings 
     */
    public void takeChips(Stack<Chip> newChips){
        Chip temp;
        while(!newChips.empty()){
            temp = newChips.pop();
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
     * Returns a stack of chips equal in value to betAmount
     * 
     * @param betAmount
     * @return 
     */
    public Stack<Chip> placeBet(int betAmount){
        Stack<Chip> callStack = new Stack<>();
        
        Chip.Color[] colors = Chip.Color.values();
        
        for(int i = colors.length - 1; i >= 0; i--){
            Stack<Chip> stk = bank.get(colors[i]);
            while(betAmount > colors[i].getValue() && !stk.isEmpty()){
                callStack.push(stk.pop());
                betAmount -= colors[i].getValue();
            }
        }
        
        return callStack;
    }
    
    
    /**
     * 
     * Returns stack of all player's chips
     * 
     * @return Stack<Chip>
     */    
    public Stack<Chip> allIn(){
        Stack<Chip> allMyMoney = new Stack<>();
        // iterates through all color stks and pops them onto new stk

        for(Chip.Color color: Chip.Color.values()){
            Stack<Chip> stk = bank.get(color);
            while(!stk.isEmpty()){
                allMyMoney.push(stk.pop());
            }
        }
        return allMyMoney;
    }
    
    public Integer getTotal() {
        return total;
    }
    
}