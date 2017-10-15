/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

/**
 *
 * @author Bernard Heres
 *
 * Holds some number of cards that make up the players hand. In texas holdem,
 * that number is 2.
 */
public class HoldemHand extends Hand {

    private final int TEXAS_HOLDEM_HAND_SIZE = 2;

    /**
     * Default constructor creates a hand for texas holdem of size 2
     */
    public HoldemHand() {
        super();
        super.setHandSize(TEXAS_HOLDEM_HAND_SIZE);       
    }

}
