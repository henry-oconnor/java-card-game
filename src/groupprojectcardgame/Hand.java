/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

import java.util.ArrayList;

/**
 *
 * @author Bernard Heres
 *
 * Hand object with no specific size. Inherited by HoldemHand and FiveCardHand
 * which have 2 and 5 cards, respectively
 */
public abstract class Hand {

    private int handSize;
    private ArrayList<Card> hand = new ArrayList<>();

    public void setHand() {
        setHandSize(handSize);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public int getHandSize() {
        return handSize;
    }
    
    /**
     * Takes a card and adds it to the hand 
     * @param card 
     */
    public void addCard(Card card){
        hand.add(card);
    } 
    public String toString(){
        String str = "";
        for(Card card : hand){
            str += card.toString() + "\n";
        }
        return str;
    }
    /**
     * Because toString() is a vague function name, this function's name
     * is more descriptive and simply calls toString()
     * @return 
     */
    public String showHand(){
        return toString();
    }
}
