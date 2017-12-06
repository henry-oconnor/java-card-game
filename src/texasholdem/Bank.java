/*
 * Henry O'Connor
 */
package texasholdem;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author henoc
 */
public abstract class Bank {

    protected Integer total;
    protected HashMap<Chip.Color, Stack<Chip>> stackLookup;
    protected Stack<Chip> whiteChips;
    protected Stack<Chip> redChips;
    protected Stack<Chip> blueChips;
    protected Stack<Chip> greenChips;
    protected Stack<Chip> blackChips;
    
    public Bank(){}
    public Bank(Integer total){
        this.total = total;
    }

    /**
     *
     * I'm thinking the user will click a chip or chip button, which will call
     * this function with the correct color and pop from the correct stack
     *
     * @param c
     * @return Chip
     */
    public Chip popChip(Chip.Color c) {
        total -= c.getValue();
        return stackLookup.get(c).pop();
    }

    /**
     * Takes a stack of chips and sorts them onto the correct player stacks,
     * increases total
     *
     * @param newChips
     */
    public void put(Stack<Chip> newChips) {
        Chip temp;
        while (!newChips.empty()) {
            temp = newChips.pop();
            total += temp.color.getValue();
            stackLookup.get(temp.color).push(temp);
        }
    }

    /**
     *
     * Returns stack of all chips
     *
     * @return Stack<Chip>
     */
    public Stack<Chip> popAll() {
        Stack<Chip> allMyMoney = new Stack<>();
        // iterates through all color stks and pops them onto new stk

        for (Chip.Color color : Chip.Color.values()) {
            Stack<Chip> stk = stackLookup.get(color);
            while (!stk.isEmpty()) {
                allMyMoney.push(stk.pop());
            }
        }
        return allMyMoney;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void addToTotal(int toAdd) {
        total += toAdd;
    }
}
