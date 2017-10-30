package texasholdem;

import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bernard Heres
 *
 * Takes an arraylist of hands, from PotentialHands, and determines the best
 * hand of all those hands
 */
public class SingleHandScore {

    private final FiveCardHand handToScore;
    // Stores the score as a string which will later be converted to hexadecimal
    private String score;

    // Used in some of the methods determining the rank of the hand
    final int ONE_PAIR = 1;
    final int TWO_PAIR = 2;
    final int THREE_OF_A_KIND = 3;
    final int FOUR_OF_A_KIND = 4;

    public SingleHandScore(FiveCardHand fiveCardHand) {
        this.handToScore = fiveCardHand;
        this.score = new String();
    }

    /**
     * Sorts a single hand using Collection.sort() and a lambda function
     *
     */
    public void sortHand() {
        Collections.sort(handToScore.getCards(), (Card c1, Card c2) -> {
            return c2.compareTo(c1);
        });
    }

    /**
     * Gives the hand a score based on what type of hand it is (1 pair, 2 pair,
     * etc.) and the numerical value of each card in the hand.
     *
     * Full House and 4 of a kind have both a 3 of a kind and 2 pairs 3 of a
     * kind and 2 pairs have at least 1 pair
     *
     * A straight flush has a straight and a flush
     *
     */
    public void scoreHand() {
        this.sortHand();

        // Does it have a pair?
        if (hasOnePair(ONE_PAIR)) {
            // Does it have two pairs AND ToaK?
            if (hasTwoPair(TWO_PAIR) && hasThreeOfAKind(THREE_OF_A_KIND)) {
                // Does it have four of a kind?
                if (hasFourOfAKind(FOUR_OF_A_KIND)) {
                    score += PokerHandRanking.FOUR_OF_A_KIND.getRanking();
                    // If it doesn't have a FoaK, it must be a full house
                } else if (hasFullHouse()) {
                    score += PokerHandRanking.FULL_HOUSE.getRanking();
                }
                // Doesn't have ToaK but has two pair?
            } else if (hasTwoPair(TWO_PAIR)) {
                score += PokerHandRanking.TWO_PAIR.getRanking();
                // Doesn't have two pair but has ToaK?
            } else if (hasThreeOfAKind(THREE_OF_A_KIND)) {
                score += PokerHandRanking.THREE_OF_A_KIND.getRanking();
            } else {
                // Made it this far? Must just be a single pair.
                score += PokerHandRanking.PAIR.getRanking();
            }
            // Doesn't have a pair? Does it have a flush?
        } else if (hasFlush()) {
            // Does it also have a straight?
            if (hasStraight()) {
                score += PokerHandRanking.STRAIGHT_FLUSH.getRanking();
                // No straight? Must just be a flush then
            } else {
                score += PokerHandRanking.FLUSH.getRanking();
            }
            // Does it have a straight?
        } else if (hasStraight()) {
            score += PokerHandRanking.STRAIGHT.getRanking();
            // Doesn't have ANYTHING? What a shame...
        } else {
            score += PokerHandRanking.HIGH_CARD.getRanking();
        }
        // Score the hand by the value of each card
        for (Card card : handToScore.getCards()) {
            score += card.getScoreValue();
        }
    }

    // The following methods determine if 
    // a hand has a flush, straight, two-pair, etc...
    // They are in order from lowest to highest value.
    // EX. Two-pair ALWAYS loses to 4 of a kind
    // IMPORTANT NOTE: These methods assume the hand is sorted.
    // If the hands are not sorted, then the methods won't function properly.
    // This is why scoreHand() called sortHand() before doing anything else.
    /**
     * At least two cards are the same rank of any suit.
     *
     * EX. A, A, 4, 3, 9
     *
     * @param numPairsNeeded
     * @return true if above conditions are true
     */
    public boolean hasOnePair(int numPairsNeeded) {
        int i = 0;
        int numPairs = 0;
        // Start walking through the hand
        while (i < handToScore.getHandSize() - 1) {
            // Get two adjacent cards.
            Card c1 = handToScore.getCards().get(i);
            Card c2 = handToScore.getCards().get(i + 1);
            // If they match, skip over c2 by incrementing i by 2
            if (c1.compareTo(c2) == 0) {
                i += 2;
                numPairs++;
                // If they don't match, don't skip any cards
            } else {
                i++;
            }
        }
        // If there's at least numPairsNeeded number of pairs
        return numPairs >= numPairsNeeded;
    }

    /**
     * Contains a pair of one card of the same rank and a pair of another card
     * of another rank (4 of the same rank is a four of a kind).
     *
     * Reuses hasOnePair code since the functions are pretty similar.
     *
     * EX. A, A, 4, 4, 9
     *
     * @param numPairsNeeded
     * @return true if above conditions are true
     */
    public boolean hasTwoPair(int numPairsNeeded) {
        return hasOnePair(numPairsNeeded);
    }

    /**
     * At least three cards have the same rank of any suit.
     *
     * Compares the first card to the next card. If they match, increment
     * numCardsMatch If they don't match, change the card being compared to and
     * increment the loop. When numCardsMatch == numCardsNeeded, return true
     * numCardsMatch starts at 1 since all cards match themselves.
     *
     * EX. A, A, A, 3, 9
     *
     * @param numCardsNeeded
     * @return true if above conditions are true
     */
    public boolean hasThreeOfAKind(int numCardsNeeded) {
        Card card = handToScore.getCards().get(0);
        int numCardsMatch = 1;
        for (int i = 1; i < handToScore.getHandSize(); i++) {
            Card cardAtIndex = handToScore.getCards().get(i);
            if (card.compareTo(cardAtIndex) == 0) {
                numCardsMatch++;
                if (numCardsMatch == numCardsNeeded) {
                    return true;
                }
            } else {
                card = cardAtIndex;
                numCardsMatch = 0;
            }
        }
        // If there's at least numCardsNeeded matches, return true
        return false;
    }

    /**
     * All five cards are in a sequence (suit irrelevant).
     *
     * Because and Ace can be high or low in a straight, there is an awkward
     * case when the player's hand is this low straight. Rather than making an
     * algorithm that unsorts and resorts and does a bunch of unintuitive
     * things, I opted to just hard-code this edge case
     *
     * EX. 2, 3, 4, 5, 6
     *
     * @return true if above conditions are true
     */
    public boolean hasStraight() {
        FiveCardHand specialHand = new FiveCardHand(
                new Card(Rank.ACE),
                new Card(Rank.FIVE),
                new Card(Rank.FOUR),
                new Card(Rank.THREE),
                new Card(Rank.TWO));
        if (specialHand.equals(handToScore)) {
            return true;
        } else {
            Card higherCard = handToScore.getCards().get(0);
            // Get the decimal value of the card's rank (from the enum)
            int higherCardValue = Integer.parseInt(higherCard.getScoreValue(), 16);
            for (int i = 1; i < handToScore.getHandSize(); i++) {
                Card lowerCard = handToScore.getCards().get(i);
                int lowerCardValue = Integer.parseInt(lowerCard.getScoreValue(), 16);
                // The difference in values is equal to i IF and only IF 
                // the next card is one less than the previous.
                // 10, 9, 8, 7, 6 — 9 is one less than 10, 
                // 8 is two less than 10 and one less than 9, and so on
                if ((higherCardValue - lowerCardValue) == i) {
                    return false;
                }
            }
            // Not false yet? So it must be a straight.
            return true;
        }
    }

    /**
     * ALL FIVE cards are the same suit (rank irrelevant).
     *
     * EX. 2, 6, 8, K, 9, all Hearts/Spades/Diamonds/Clubs
     *
     * @return true if above conditions are true
     */
    public boolean hasFlush() {
        Card firstCard = handToScore.getCards().get(0);
        Suit firstCardSuit = firstCard.getSuit();
        // Compare the suits to that of the first card
        for (int i = 1; i < handToScore.getHandSize(); i++) {
            Suit currentSuit = handToScore.getCards().get(i).getSuit();
            // If even one card is a different suit, there's no flush
            if (currentSuit.compareTo(firstCardSuit) != 0) {
                return false;
            }
        }
        // If the for loop doesn't return false, 
        // then all the cards have the same suit
        return true;
    }

    /**
     * A pair of one card and three of a kind of another.
     *
     * If a hand has both a ToaK and two pairs and DOESN'T have FoaK, it has a
     * full house
     *
     * EX. 2, 2, 3, 3, 3
     *
     * @return true if above conditions are true
     */
    public boolean hasFullHouse() {
        return this.hasThreeOfAKind(THREE_OF_A_KIND)
                && this.hasTwoPair(TWO_PAIR)
                && !this.hasFourOfAKind(FOUR_OF_A_KIND);
    }

    /**
     * At least four cards are the same rank of any suit.
     *
     * Similar to hasThreeOfAKind, but doesn't return true unless there are 4
     * cards matching instead of at least 3.
     *
     * EX. 10, 10, 10, 10, K
     *
     * @param numCardsNeeded
     * @return true if above conditions are true
     */
    public boolean hasFourOfAKind(int numCardsNeeded) {
        return this.hasThreeOfAKind(numCardsNeeded);
    }

    /**
     * Cards are in consecutive order and are the same suit hasFlush and
     * hasStraight are both true
     *
     * EX. 5, 6, 7, 8, 9, all Hearts/Spades/Diamonds/Clubs
     *
     * @return true if above conditions are true
     */
    public boolean hasStraightFlush() {
        return hasStraight() && hasFlush();
    }

    // Although there is a royal straight flush, it doesn't need its own
    // function because a royal straight flush is just an Ace-high straight flush.
    public FiveCardHand getHandToScore() {
        return this.handToScore;
    }

    public String getScore() {
        return this.score;
    }

}
