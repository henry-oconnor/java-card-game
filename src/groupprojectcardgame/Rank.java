/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

/**
 *
 * @author Bernard Heres Enum for the card ranks: 2-10, J, Q, K, A The JOKER is
 * used when an input is invalid
 */
public enum Rank {
    TWO("Two"), THREE("Three"), FOUR("Four"),
    FIVE("Five"), SIX("Six"), SEVEN("Seven"),
    EIGHT("Eight"), NINE("Nine"), TEN("Ten"),
    JACK("Jack"), QUEEN("Queen"), KING("King"), ACE("Ace");

    private String rankName;

    private Rank(String rankName) {
        this.rankName = rankName;
    }

    public String toString() {
        return rankName;
    }
}