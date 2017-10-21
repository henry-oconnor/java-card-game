package texasholdem;


import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bernard Heres
 *
 * Takes a HoldemHand object and a FiveCardHand object (total of 7 cards) and
 * generates 21 different possible 5-card hands (7 choose 5 = 21).
 */
public class PotentialHands {

    private final HoldemHand holeCards;
    private final FiveCardHand communityCards;
    private final ArrayList<Card> allCards = new ArrayList<>();

    private ArrayList<FiveCardHand> potentialHandsArr = new ArrayList<>();

    public PotentialHands(HoldemHand twoCards, FiveCardHand fiveCards) {
        this.holeCards = twoCards;
        this.communityCards = fiveCards;
        this.allCards.addAll(holeCards.getCards());
        this.allCards.addAll(communityCards.getCards());
    }

    /**
     * Uses recursion to create 21 different hands from 7 cards (2 hold cards
     * and 5 community cards) These are the potential hand a player may have.
     * The best hand of these 21 is a person's "winning" hand that is compared
     * to the other players' "winning" hands
     *
     * @param subsetSize
     * @param start
     * @param current
     * @param used
     */
    private void performCombination(int subsetSize, int start,
            int current, boolean[] used) {

        if (current == subsetSize) {
            // Create a hand object to hold each card
            FiveCardHand hand = new FiveCardHand();
            for (int i = 0; i < allCards.size(); i++) {
                // if used at i is true, add the card to the hand (from allCards)
                if (used[i]) {
                    hand.addCard(allCards.get(i));
                }
            }
            // After getting 5 cards, store the cards in potentialHandsArr
            potentialHandsArr.add(hand);
            return;
        }
        if (start == allCards.size()) {
            return;
        }
        used[start] = true;
        performCombination(subsetSize, start + 1, current + 1, used);

        used[start] = false;
        performCombination(subsetSize, start + 1, current, used);
    }

    /**
     * Wrapper function for performCombination
     */
    public void performCombination() {
        int subsetSize = communityCards.getHandSize();
        boolean used[] = new boolean[allCards.size()];
        FiveCardHand hand = new FiveCardHand();

        performCombination(subsetSize, 0, 0, used);
    }

    public ArrayList<FiveCardHand> getPotentialHands() {
        return potentialHandsArr;
    }

    /**
     * To see what hands have been made (and display how many there are).
     * @return a list of 21 different poker hands.
     */
    public String toString() {
        String str = "";
        int i = 1;
        for (FiveCardHand hand : potentialHandsArr) {
            str += "Hand #" + i + "\n" + hand.showHand() + "\n";
            i++;
        }
        return str;
    }
}
