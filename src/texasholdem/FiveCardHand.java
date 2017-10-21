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
     * Default constructor creates a hand for texas holdem of size 5, which
     * is the number of in a hand made from the 5 community cards 
     * and 2 hole cards
     */
    public FiveCardHand() {
        super();
        super.setHandSize(FIVE_CARD_HAND_SIZE);       
    }
    public FiveCardHand(Card... cards) {
        super(cards);
        super.setHandSize(FIVE_CARD_HAND_SIZE);
    }
}
