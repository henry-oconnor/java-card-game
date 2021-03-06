/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

/**
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
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

    public static String getRankingName(char ranking) {
        switch (ranking) {
            case '0':
                return "High Card";
            case '1':
                return "Pair";
            case '2':
                return "Two Pair";
            case '3':
                return "Three of a Kind";
            case '4':
                return "Straight";
            case '5':
                return "Flush";
            case '6':
                return "Full House";
            case '7':
                return "Four of a Kind";
            case '8':
                return "Straight Flush";
        }
        return "";
    }
}
