package texasholdem;

import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 */
public enum Suit {
    HEARTS("Hearts", 0),
    CLUBS("Clubs", 1),
    DIAMONDS("Diamonds", 2),
    SPADES("Spades", 3);

    private final int value;
    private final String suitName;
    private static Map map = new HashMap<>();

    private Suit(String suitName, int value) {
        this.suitName = suitName;
        this.value = value;
    }

    static {
        for (Suit suit : Suit.values()) {
            map.put(suit.suitName, suit);
        }
    }

    public static Suit valueOf(int suitInt) {
        switch(suitInt){
            case 0:
                return HEARTS;
            case 1:
                return CLUBS;
            case 2:
                return DIAMONDS;
            case 3:
                return SPADES;
        }
        return null;
    }

    public String getSuitName() {
        return suitName;
    }
    public int getValue(){
        return value;
    }

    @Override
    public String toString() {
        return suitName;
    }
}
