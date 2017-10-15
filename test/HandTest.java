/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import groupprojectcardgame.HoldemHand;
import groupprojectcardgame.Card;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Bernard Heres
 *
 * Test of the hand class
 */
public class HandTest {

    protected int holdemSize = 2;
    protected int pokerSize = 5;

    public HandTest() {
        HoldemHand holdemHand = new HoldemHand();
        // Default card is a four of hearts.
        Card defaultCard = new Card();
        // Add cards to the hand
        for (int i = 0; i < holdemHand.getHandSize(); i++) {
            holdemHand.addCard(defaultCard);
        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test public void testDisplayMethod(){
        HoldemHand hand = new HoldemHand();
        hand.getHand().add(0, new Card());
        hand.getHand().add(1, new Card());
        
        assertEquals(showHand(hand), hand.showHand());
    }
    
    @Test
    public String showHand(HoldemHand holdemHand) {
        String str = "";
        for (Card card : holdemHand.getHand()) {
            str += card.toString() + "\n";
        }    
        return str;
    }
}
