/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
 * 
 * @author Bernard Heres
 */
public final class BestHand {

    private PotentialHands potentialHands;
    private ArrayList<FiveCardHand> allHands = new ArrayList<>();

    private ArrayList<SingleHandScore> scoredHands = new ArrayList<>();

    private SingleHandScore bestHand = new SingleHandScore();
    private int bestHandScore;

    public BestHand(PotentialHands potential) {
        this.potentialHands = potential;
        setAllHands();      // Set the allHands list
        setScoredHands();   // Set the scoredHands list
        scoreHands();       // Score the hands in scoredHands
        setHighestScore();  // set bestHand

    }

    /**
     * Takes any number of FiveCardHand objects and determines the best hand out
     * of all of them. Useful to compare the BestHand objects of each player.
     *
     * @param hands
     */
    public BestHand(FiveCardHand... hands) {
        // Use for-each to set argument of unknown size
        allHands.addAll(Arrays.asList(hands));
        setScoredHands();
        scoreHands();
        setHighestScore();
    }

    /**
     * Gets the hands from the potentialHands object and stores them in and
     * array for quicker access
     */
    public void setAllHands() {
        allHands = potentialHands.getAllHands();
    }

    /**
     * Passes each hand into a SingleHandScore object, and stores it in an
     * arraylist
     */
    public void setScoredHands() {
        if (!allHands.isEmpty()) {
            for (FiveCardHand hand : allHands) {
                SingleHandScore singleHandScore = new SingleHandScore(hand);
                scoredHands.add(singleHandScore);
            }
        }
    }

    public void scoreHands() {
        if (!scoredHands.isEmpty()) {
            for (SingleHandScore hand : scoredHands) {
                // Score the hand
                hand.scoreHand();
            }
        }
    }

    /**
     * Compare all the scores of each hand and get the best scoring hand
     */
    public void setHighestScore() {
        if (!scoredHands.isEmpty()) {
            bestHand = scoredHands.get(0);
            for (SingleHandScore hand : scoredHands) {
                if (bestHand.getScoreAsInt() < hand.getScoreAsInt()) {
                    bestHand = hand;
                }
            }
        }
    }

    /**
     * Return the bestHand SingleHandScore
     *
     * @return the bestHand SingleHandScore
     */
    public SingleHandScore getBestHand() {
        return bestHand;
    }

    public String getBestHandScore() {
        return bestHand.getScore();
    }
}
