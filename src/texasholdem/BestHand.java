package texasholdem;


import java.util.Collections;

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

    // Stores the score as a string which will later be converted to hexadecimal
    private String score;

    public BestHand(PotentialHands hands) {
        this.potentialHands = hands;
        this.size = potentialHands.getPotentialHands().size();
        this.score = new String();
    }

    /**
     * Sorts the hand from greatest to least, A to 2. Calls the
     * sortHand(FiveCardHand) function on each hand in the array. There are 21
     * hands in an array, so sortHand() is called 21 times and performs a
     * selection sort
     */
    public void sortAllHands() {
        int index = 0;
        while (index < size) {
            FiveCardHand currentHand = potentialHands.getPotentialHands().get(index);
            potentialHands.getPotentialHands().set(index, sortHand(currentHand));
            index++;
        }
    }

    /**
     * Sorts a single hand using Collection.sort() and a lambda function
     *
     * @param hand
     * @return the sorted hand
     */
    private FiveCardHand sortHand(FiveCardHand hand) {   
        Collections.sort(hand.getCards(), (Card c1, Card c2) -> {
            return c2.compareTo(c1);
        });
        return hand;
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
    public boolean hasOnePair(FiveCardHand hand) {
        for (int i = 0; i < hand.getCards().size() - 1; i++) {
            // If this card and the card next to it are the same, there's at
            // least a single pair
            if(hand.getCards().get(i).compareTo(hand.getCards().get(i+1)) == 0)
                return true;
        }
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
    
    /**
     * 
     * @return PotentialHands object
     */
    public PotentialHands getPotentialHands(){
        return potentialHands;
    }
    /**
     * 
     * @param index
     * @return A hand at a given index of a PotentialHands object
     */
    public FiveCardHand getFiveCardHand(int index){
        return potentialHands.getPotentialHands().get(index);
    }
}
