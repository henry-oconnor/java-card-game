/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bernard Heres
 *
 * Takes an arraylist of hands, from PotentialHands, and determines the best
 * hand of all those hands
 */
public class BestHand {

    private final PotentialHands potentialHands;
    private int size;
    // Array of scores, one for each hand
    private int[] score;

    public BestHand(PotentialHands hands) {
        this.potentialHands = hands;
        this.size = potentialHands.getPotentialHands().size();
        this.score = new int[size];
    }

    // The following functions determine if 
    // a hand has a flush, straight, two-pair, etc...
    // They are in order from lowest to highest value.
    // EX. Two-pair ALWAYS loses to 4 of a kind
    /**
     * At least two cards are the same rank of any suit.
     *
     * EX. A, A, 4, 3, 9
     *
     * @return true if above conditions are true
     */
    public boolean hasOnePair() {
        return false;
    }

    /**
     * Contains a pair of one card of the same rank and a pair of another card
     * of another rank (4 of the same rank is a four of a kind).
     *
     * EX. A, A, 4, 4, 9
     *
     * @return true if above conditions are true
     */
    public boolean hasTwoPair() {
        return false;
    }

    /**
     * At least three cards have the same rank of any suit.
     *
     * EX. A, A, A, 3, 9
     *
     * @return true if above conditions are true
     */
    public boolean hasThreeOfKind() {
        return false;
    }

    /**
     * All five cards are in a sequence (suit irrelevant)
     *
     * EX. 2, 3, 4, 5, 6
     *
     * @return true if above conditions are true
     */
    public boolean hasStraight() {
        return false;
    }

    /**
     * ALL FIVE cards are the same suit (rank irrelevant).
     *
     * EX. 2, 6, 8, K, 9, all Hearts/Spades/Diamonds/Clubs
     *
     * @return true if above conditions are true
     */
    public boolean hasFlush() {
        return false;
    }

    /**
     * A pair of one card and three of a kind of another.
     *
     * EX. 2, 2, 3, 3, 3
     *
     * @return true if above conditions are true
     */
    public boolean hasFullHouse() {
        return false;
    }

    /**
     * At least four cards are the same rank of any suit.
     *
     * EX. 10, 10, 10, 10, K
     *
     * @return true if above conditions are true
     */
    public boolean hasFourOfKind() {
        return false;
    }

    /**
     * Cards are in consecutive order and are the same suit hasFlush and
     * hasStraight are both true
     *
     * EX. 5, 6, 7, 8, 9, all Hearts/Spades/Diamonds/Clubs
     *
     * @return true if above conditions are true
     */
    public boolean hasStraightFlush() {
        return false;
    }

    // Although there is a royal straight flush, it doesn't need its own
    // function because a royal straight flush is just an Ace-high straight flush.

}
