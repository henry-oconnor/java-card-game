/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Bernard Heres
 */

public class DeckOfCardsTest {
@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    public DeckOfCardsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of manyShuffles method, of class DeckOfCards.
     */
    @Test
    public void testManyShuffles() {
        System.out.println("manyShuffles");
        int numShuffles = 20;
        DeckOfCards firstDeck = new DeckOfCards();
        DeckOfCards shuffleDeck = new DeckOfCards();
        shuffleDeck.manyShuffles(numShuffles);
        assertNotEquals(shuffleDeck, firstDeck);
    }

    /**
     * Test of shuffle method, of class DeckOfCards.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        DeckOfCards firstDeck = new DeckOfCards();
        DeckOfCards shuffleDeck = new DeckOfCards();
        shuffleDeck.shuffle();
        assertNotEquals(shuffleDeck, firstDeck);
    }

    /**
     * Test of cut method, of class DeckOfCards.
     */
    @Test
    public void testCut() {
        System.out.println("cut");
        DeckOfCards firstDeck = new DeckOfCards();
        DeckOfCards cutDeck = new DeckOfCards();
        cutDeck.cut();
        assertNotEquals(cutDeck, firstDeck);
    }

    /**
     * Test of dealCard method, of class DeckOfCards.
     */
    @Test
    public void testDealCard() {
        System.out.println("dealCard");
        DeckOfCards deck = new DeckOfCards();
        Card expResult = new Card(Suit.SPADES, Rank.ACE);
        Card result = deck.dealCard();
        assertEquals(expResult, result);
    }

}
