/*
 * Henry O'Connor
 */
package texasholdem;

import java.util.Stack;

/**
 *
 * @author henoc
 */
public class HoldemPlayer {
    private HoldemHand holeCards;
    private PlayerBank bank;
    
    /**
     * 
     * @param card 
     */
    public void addCard(Card card){
        holeCards.addCard(card);
    } 
    
    /**
     * 
     * @param betAmount
     * @return chip stack
     */
    public Stack<Chip> placeBet(int betAmount){
        return bank.getChips(betAmount);
    }
    
    public Stack<Chip> allIn(){
        return bank.getChips(bank.getTotal());
    }
}