package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Bernard Heres
 *
 * Hand object with no specific size. Inherited by HoldemHand and FiveCardHand
 * which have 2 and 5 cards, respectively
 */
public abstract class Hand {

    private int handSize;
    private ArrayList<Card> cards = new ArrayList<>();

    public Hand() {
    }

    /**
     * Will take any number of card objects and add them to the cards ArrayList
     *
     * @param inCards
     */
    public Hand(Card... inCards) {
        this.cards.addAll(Arrays.asList(inCards));
    }

    public void setHand() {
        setHandSize(handSize);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> inCards) {
        this.cards = inCards;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public int getHandSize() {
        return handSize;
    }

    /**
     * Takes a card and adds it to the cards
     *
     * @param card
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        String str = "";
        for (Card card : cards) {
            str += card.toString() + "\n";
        }
        return str;
    }

    /**
     * Because toString() is a vague function name, this function's name is more
     * descriptive and simply calls toString()
     *
     * @return
     */
    public String showHand() {
        return toString();
    }
}
