package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiach
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
