package texasholdem;

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

    Rank(String rankName, String rankValue) {
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    public String getRankValue() {
        return rankValue;
    }

    @Override
    public String toString() {
        return rankName;
    }
}
