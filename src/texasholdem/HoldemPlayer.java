/*
 * Henry O'Connor
 */
package texasholdem;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @author henoc
 */
public class HoldemPlayer implements Serializable{

    private HoldemHand hand;
    private PlayerBank bank;
    
    /**
     * Wrapper method for HoldemHand.addCard()
     * @param card 
     */
    public void addCard(Card card){
        hand.addCard(card);
    } 
    
    /**
     * Wrapper method for Bank.getChips()
     * @param betAmount
     * @return chip stack
     */
    public Stack<Chip> placeBet(int betAmount){
        return bank.getChips(betAmount);
    }
    
    public Stack<Chip> allIn(){
        return bank.getChips(bank.getTotal());
    }
    
    public HoldemHand getHoleCards() {
        return hand;
    }

    public PlayerBank getBank() {
        return bank;
    }
}