package texasholdem;

import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Hexadecimal rankValues for each rank Two = 2 Three = 3 Four = 4 Five = 5 Six
 * = 6 Seven = 7 Eight = 8 Nine = 9 Ten = A Jack = B Queen = C King = D Ace = E
 */
public enum Rank {
    TWO("Two", "2"),
    THREE("Three", "3"),
    FOUR("Four", "4"),
    FIVE("Five", "5"),
    SIX("Six", "6"),
    SEVEN("Seven", "7"),
    EIGHT("Eight", "8"),
    NINE("Nine", "9"),
    TEN("Ten", "A"),
    JACK("Jack", "B"),
    QUEEN("Queen", "C"),
    KING("King", "D"),
    ACE("Ace", "E");

    private final String rankName;

    // rankValue is the hexadecimal value a rank would have
    private final String rankValue;
    private final int enumValue;
    private static Map map = new HashMap<>();

    private Rank(String rankName, String rankValue) {
        this.rankName = rankName;
        this.rankValue = rankValue;
        this.enumValue = Integer.parseInt(rankValue, 16);
    }

    static {
        for (Rank rank : Rank.values()) {
            map.put(rank.enumValue, rank);
        }
    }

    public static Rank valueOf(int rankInt) {
        switch (rankInt) {
            case 0:
                return TWO;
            case 1:
                return THREE;
            case 2:
                return FOUR;
            case 3:
                return FIVE;
            case 4:
                return SIX;
            case 5:
                return SEVEN;
            case 6:
                return EIGHT;
            case 7:
                return NINE;
            case 8:
                return TEN;
            case 9:
                return JACK;
            case 10:
                return QUEEN;
            case 11:
                return KING;
            case 12:
                return ACE;
        }
        return null;
    }

    public String getRankValue() {
        return rankValue;
    }

    @Override
    public String toString() {
        return rankName;
    }
}
