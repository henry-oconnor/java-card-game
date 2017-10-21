package texasholdem;


import javafx.scene.image.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiach
 */
public class Card implements Comparable {

    private Suit suit;
    private Rank rank;
    private CardImage image;

    // Default card
    public Card() {
        setSuit(Suit.HEARTS);
        setRank(Rank.FOUR);
        image = new CardImage();
    }

    public Card(Suit suit, Rank rank) {
        setSuit(suit);
        setRank(rank);
        image = new CardImage(rank, suit);
    }

    // Getters and setters
    public Suit getSuit() {
        return suit;
    }

    public CardImage getImage() {
        return image;
    }

    /**
     * Doesn't allow the suit to be set as anything outside the bounds of the
     * enumerator. If it is outside the bounds, it sets the card to a spade.
     */
    public void setSuit(Suit suit) {
        if (suit.compareTo(Suit.HEARTS) < 0 || suit.compareTo(Suit.SPADES) > 0) {
            this.suit = Suit.SPADES;
        } else {
            this.suit = suit;
        }
    }

    public Rank getRank() {
        return rank;
    }

    /**
     * Like setSuit, setRank also creates an Ace if the rank is invalid. So
     * invalid inputs become Aces of Spades.
     */
    public void setRank(Rank rank) {
        if (rank.compareTo(Rank.TWO) < 0 || rank.compareTo(Rank.ACE) > 0) {
            this.rank = Rank.ACE;
        } else {
            this.rank = rank;
        }
    }

    /**
     * Takes the suit and rank and creates a string from them. EX. ACE and SPADE
     * becomes Ace of Spades.
     */
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    @Override
    public int compareTo(Object t) {
        return this.rank.compareTo(((Card) t).getRank());
    }
}
