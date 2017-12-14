/*
 * Henry O'Connor
 */
package texasholdem;

import java.io.Serializable;
import java.util.Stack;

/**
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
 * 
 * @author henoc
 */
public class HoldemPlayer implements Serializable {

    private HoldemHand hand;

    private BestHand bestHand;
    private String bestHandScore;

    private String playerName;

    public HoldemPlayer() {
        hand = new HoldemHand();

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
     *
     * @param cards
     */
    public void addCards(Card... cards) {
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    public HoldemHand getHand() {
        return hand;
    }

    public void setHand(HoldemHand hand) {
        this.hand = hand;
    }

    public String getBestHandScore() {
        return bestHandScore;
    }

    public void setBestHandScore(String bestHandScore) {
        this.bestHandScore = bestHandScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void emptyHand() {
        this.hand = new HoldemHand();
    }

    public void determineBestHand(FiveCardHand communityCards) {
        bestHand = new BestHand(
                new PotentialHands(hand, communityCards));
    }
    public void determineBestHandScore(){
        bestHandScore = bestHand.getBestHandScore();
    }
}
