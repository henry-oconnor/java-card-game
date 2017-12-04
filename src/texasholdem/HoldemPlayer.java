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
    private boolean playing;

    // Amount the player has contributed this round
    private int currentWager;

    private int bestHandScore;

    /**
     * Wrapper method for HoldemHand.addCard()
     *
     * @param card
     */
    public void addCard(Card card) {
        hand.addCard(card);
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

}
