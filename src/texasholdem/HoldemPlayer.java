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
public class HoldemPlayer implements Serializable {

    private HoldemHand hand;
    private PlayerBank bank;
    private boolean playing = true;

    // Amount the player has contributed this round
    private int currentWager;

    private int bestHandScore;
    
    private String playerName;

    public HoldemPlayer(){
         hand = new HoldemHand();
         bank = new PlayerBank(1000);
    }
    
    /**
     * Wrapper method for HoldemHand.addCard()
     *
     * @param card
     */
    public void addCard(Card card) {
        hand.addCard(card);
    }
    /**
     * Adds multiple cards to a hand.
     * @param cards 
     */
    public void addCards(Card...cards){
        for(Card card : cards){
            hand.addCard(card);
        }
    }

    /**
     * Wrapper method for Bank.getChips()
     *
     * @param betAmount
     * @return chip stack
     */
    public Stack<Chip> placeBet(int betAmount) {
        currentWager += betAmount;
        return bank.getChips(betAmount);
    }

    public Stack<Chip> allIn() {
        return bank.getChips(bank.getTotal());
    }

    public HoldemHand getHand() {
        return hand;
    }

    public PlayerBank getBank() {
        return bank;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean isPlaying) {
        this.playing = isPlaying;
    }

    public int getCurrentWagers() {
        return currentWager;
    }

    public void setCurrentWager(int currentWager) {
        this.currentWager = currentWager;
    }

    public int getBestHandScore() {
        return bestHandScore;
    }

    public void setBestHandScore(int bestHandScore) {
        this.bestHandScore = bestHandScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void emptyHand(){
        this.hand = new HoldemHand();
    }
    
}
