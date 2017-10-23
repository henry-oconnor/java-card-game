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
public class Card implements Comparable {

    private Suit suit;
    private Rank rank;
    private String scoreValue;
    private CardImage image;

    // Default card
    public Card() {
        setSuit(Suit.HEARTS);
        setRank(Rank.FOUR);
        setScoreValue();
        image = new CardImage();
    }
    
    /**
     * Special constructor used when 
     * a card must be made but the suit doesn't matter.
     * Has a use in SingleHandScore.hasStraight() method.
     * @param rank 
     */
    public Card(Rank rank){
        setSuit(Suit.SPADES);
        setRank(rank);
        setScoreValue();
        image = new CardImage();
    }

    public Card(Suit suit, Rank rank) {
        setSuit(suit);
        setRank(rank);
        setScoreValue();
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
     *
     * @param suit
     */
    public final void setSuit(Suit suit) {
        if (suit.compareTo(Suit.HEARTS) < 0 || suit.compareTo(Suit.SPADES) > 0) {
            this.suit = Suit.HEARTS;
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
     *
     * @param rank
     */
    public final void setRank(Rank rank) {
        if (rank.compareTo(Rank.TWO) < 0 || rank.compareTo(Rank.ACE) > 0) {
            this.rank = Rank.FOUR;
        } else {
            this.rank = rank;
        }
    }

    public String getScoreValue() {
        return scoreValue;
    }

    /**
     * Sets the Card's rank's rankValue to scoreValue
     */
    public final void setScoreValue() {
        this.scoreValue = rank.getRankValue();
    }

    /**
     * Takes the suit and rank and creates a string from them.
     *
     * @return EX. ACE and SPADE becomes Ace of Spades.
     */
    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    /**
     * ONLY COMPARES RANK, NOT SUIT
     * @param t
     * @return 
     * 1 if this.rank is greater than t.rank, 
     * 0 if this.rank is the same t.rank, 
     * -1 if this.rank is less than t.rank
     */
    @Override
    public int compareTo(Object t) {
        return this.rank.compareTo(((Card) t).getRank());
    }
}
