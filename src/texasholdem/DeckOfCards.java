package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author 09/20/17
 *
 * A collection of card objects, stored in a stack because a deck of cards is
 * literally a stack.
 *
 * MODIFICATIONS: 10/06/17 — changed the setDeck() function to create 52
 * distinct cards, thirteen of each suit, four suits
 *
 * 10/15/17 changes — Removed numCards from the class since a deck of cards
 * should have a specific number of cards.
 *
 */

public class DeckOfCards {

    private final int NUM_SUITS = 4;
    private final int NUM_RANKS = 13;
    private final int STANDARD_DECK_SIZE = 52;

    private Stack<Card> deck;

    public DeckOfCards() {
        setDeck();
    }

    /**
     * Shuffles the deck by interweaving the cards and cutting it. Randomness
     * can be added later (rather than interweaving neatly, it can interweave
     * the cards in uneven chunks)
     */
    
    public void shuffle() {
        // First, split the deck into two pieces
        Stack<Card> firstHalf = new Stack<>();
        Stack<Card> secondHalf = new Stack<>();
        for (int i = 0; i < STANDARD_DECK_SIZE; i++) {
            // Populate first half with half the deck
            if (i < STANDARD_DECK_SIZE / 2) {
                firstHalf.push(deck.pop());
            } else { // Populate second half with the other half
                secondHalf.push(deck.pop());
            }
        }
        // The above operation reverses the order of the cards in each half
        // which will be undone when put back into the initial stack

        // Merge the halves, weaving them together 
        for (int i = 0; i < STANDARD_DECK_SIZE; i++) {
            if (i % 2 == 0) {
                deck.push(firstHalf.pop());
            } else {
                deck.push(secondHalf.pop());
            }
        } 
       // Take a chunk off the bottom of the deck and put it at the bottom
        cut();
    }

    /**
     * Selects a random number of cards from 1-STANDARD_DECK_SIZE from the top
     * and moves them to the bottom.
     */
    public void cut() {
        Stack<Card> top = new Stack<>();
        Stack<Card> bottom = new Stack<>();

        Random rand = new Random();
        int randomNumber = rand.nextInt(STANDARD_DECK_SIZE) + 1;
        // Put cards into two stacks
        for (int i = 0; i < randomNumber; i++) {
            top.push(deck.pop());
        }
        for (int i = randomNumber; i < STANDARD_DECK_SIZE; i++) {
            bottom.push(deck.pop());
        }
        // Return cards to deck, putting the top in first
        for (int i = 0; i < randomNumber; i++) {
            deck.push(top.pop());
        }
        for (int i = randomNumber; i < STANDARD_DECK_SIZE; i++) {
            deck.push(bottom.pop());
        }

        // By popping the values then pushing them back in, reverseStack()
        // doesn't need to be called. The pushing and popping reverses then 
        // un-reverses the deck automatically.
    }

    /**
     * Pops items from a stack and pushes them into a new stack, reversing the
     * order
     *
     * FUNCTIONALITY NOT ALL HERE. USING FUNCTION CAUSES ERRORS.
     *
     */
    public Stack<Card> reverseStack(Stack<Card> stack) {
        Stack<Card> newStack = new Stack<>();
        newStack.push(stack.pop());
        return newStack;
    }

    /**
     * Deals a card off the top of the deck. Pops an object off the top of the
     * stack.
     *
     * @return top card off the deck
     */
    public Card dealCard() {
        if (!deck.isEmpty()) {
            return deck.pop();
        } else {
            throw new EmptyStackException();
        }
    }
    /**
     * Deals a card off the top of the deck. Pops an object off the top of the
     * stack.
     *
     * @return top card off the deck
     */
    public Card dealToPlayer() {
        if (!deck.isEmpty()) {
            return deck.pop();
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Creates a deck of 52 cards.
     * Put in the order: 2 - A, Hearts, Clubs, Diamonds, Spades
     * Because it's a stack, the top card is the Ace of Spades
     */
    public void setDeck() {
        deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.push(new Card(suit, rank));
            }
        }
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public int getDeckSize() {
        return STANDARD_DECK_SIZE;
    }
}
