package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bernard Heres
 */
public class FiveCardHand extends Hand {

    private final int FIVE_CARD_HAND_SIZE = 5;

    /**
     * Default constructor creates a hand for texas holdem of size 5, which is
     * the number of in a hand made from the 5 community cards and 2 hole cards
     */
    public FiveCardHand() {
        super();
        super.setHandSize(FIVE_CARD_HAND_SIZE);
    }

    public FiveCardHand(Card... cards) {
        super(cards);
        super.setHandSize(FIVE_CARD_HAND_SIZE);
    }

    /**
     * ONLY COMPARES RANKS, NOT SUITS. This function is used in
     * SingleHandScore.hasStraight() to determine if the player's hand is an
     * edge case.
     *
     * @param t
     * @return 1 if the hands are not the same
     */
    @Override
    public boolean equals(Object t) {
        for (int i = 0; i < this.getHandSize(); i++) {
            Card c1 = this.getCards().get(i);
            Card c2 = ((FiveCardHand) t).getCards().get(i);
            // If a single comparison is false, the hands are not equal in rank
            if (c1.compareTo(c2) != 0) {
                return false;
            }
        }
        // No return yet, so the hands must have the same ranks
        return true;
    }
}
