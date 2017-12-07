package texasholdem;

/*
 * Henry O'Connor
 */
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author henoc A player's bank for betting and buy-ins
 */
public class PlayerBank extends Bank {

    // buyin should be in multiples of 100
    // creates a reasonable chip distribution from buyin amount
    public PlayerBank(int buyin) {
        stackLookup = new HashMap<>();
        double remaining = buyin;
        total = buyin;

        blackChips = new Stack<>();
        stackLookup.put(Chip.Color.BLACK, blackChips);
        if (remaining > 500) {
            while (remaining / buyin > .7) {
                blackChips.push(new Chip(Chip.Color.BLACK));
                remaining -= 100;
            }
        }

        greenChips = new Stack<>();
        stackLookup.put(Chip.Color.GREEN, greenChips);
        if (remaining > 100) {
            while (remaining / buyin > .4) {
                greenChips.push(new Chip(Chip.Color.GREEN));
                remaining -= 25;
            }
        }

        blueChips = new Stack<>();
        stackLookup.put(Chip.Color.BLUE, blueChips);
        while (remaining / buyin > .25) {
            blueChips.push(new Chip(Chip.Color.BLUE));
            remaining -= 10;
        }

        redChips = new Stack<>();
        stackLookup.put(Chip.Color.RED, redChips);
        while (remaining / buyin > .05) {
            redChips.push(new Chip(Chip.Color.RED));
            remaining -= 5;
        }

        whiteChips = new Stack<>();
        stackLookup.put(Chip.Color.WHITE, whiteChips);
        while (remaining > 0) {
            whiteChips.push(new Chip(Chip.Color.WHITE));
            remaining -= 1;
        }
    }

    /**
     * Returns a stack of chips equal in value to betAmount
     *
     * @param value
     * @return
     */
    public Stack<Chip> getChips(int value) {
        Stack<Chip> returnStack = new Stack<>();

        Chip.Color[] colors = Chip.Color.values();

        for (int i = colors.length - 1; i >= 0; i--) {
            Stack<Chip> stk = stackLookup.get(colors[i]);
            while (value > colors[i].getValue() && !stk.isEmpty()) {
                returnStack.push(stk.pop());
                value -= colors[i].getValue();
            }
        }

        return returnStack;
    }


}
