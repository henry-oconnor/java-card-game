/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

/**
 *
 * @author Bernard Heres
 */
public enum PokerHandRanking {
    HIGH_CARD("0"),
    PAIR("1"),
    TWO_PAIR("2"),
    THREE_OF_A_KIND("3"),
    STRAIGHT("4"),
    FLUSH("5"),
    FULL_HOUSE("6"),
    FOUR_OF_A_KIND("7"),
    STRAIGHT_FLUSH("8");

    private final String ranking;

    PokerHandRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getRanking() {
        return ranking;
    }
}
