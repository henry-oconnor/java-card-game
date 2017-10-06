/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

/**
 *
 * @author Bernard Heres
 * Enum to hold the suits of cards. 
 * The joker isn't a suit, but is used when a card is set incorrectly, that is
 * the setter/constructor tries to set the card as some fifth suit.
 * 
 * Has a private constructor so each integer of the enum also has a string
 * associated with it.
 */
public enum Suit {
    HEARTS("Hearts"), CLUBS("Clubs"), DIAMONDS("Diamonds"), SPADES("Spades");
    
    private String suitName;
    private Suit(String suitName){
        this.suitName = suitName;
    }
    public String toString(){
        return suitName;
    }
}
