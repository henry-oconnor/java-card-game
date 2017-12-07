/*
 * Henry O'Connor
 */
package texasholdem;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public class GameBoard implements HoldemConstants {

    private FiveCardHand communityCards;
    private ArrayList<HoldemPlayer> players;
    private DeckOfCards deck;
    private Pot pot;

    // Increments through the players
    private int playerTurn;

    // Changes based on the choice the player made (check, call, etc.)
    private PlayerChoice choice;

    // To call, you must match the amount raised so far.
    // 
    private int amountToMatch;

    public GameBoard() {
        this.communityCards = new FiveCardHand();
        this.deck = new DeckOfCards();
        this.pot = new Pot();
        this.players = new ArrayList<>();
    }

    public GameBoard(ArrayList<HoldemPlayer> players) {
        // Instantiate objects
        communityCards = new FiveCardHand();
        this.players = players;
        deck = new DeckOfCards();
        deck.manyShuffles(NUM_SHUFFLES);
        pot = new Pot();
    }

    public FiveCardHand getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(FiveCardHand communityCards) {
        this.communityCards = communityCards;
    }

    public ArrayList<HoldemPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<HoldemPlayer> players) {
        this.players = players;
    }

    public DeckOfCards getDeck() {
        return deck;
    }

    public void setDeck(DeckOfCards deck) {
        this.deck = deck;
    }

    public Pot getPot() {
        return pot;
    }

    public void setPot(Pot pot) {
        this.pot = pot;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public PlayerChoice getChoice() {
        return choice;
    }

    public void setChoice(PlayerChoice choice) {
        this.choice = choice;
    }

    public int getAmountToMatch() {
        return amountToMatch;
    }

    public void setAmountToMatch(int amountToMatch) {
        this.amountToMatch = amountToMatch;
    }

    public int getHAND_SIZE() {
        return HAND_SIZE;
    }

//
//    public void play() {
//        deck.manyShuffles(NUM_SHUFFLES);
//        collectAntes(MINIMUM_ANTE);
//   //     dealHands();
//        // The players' turns change in this method
//        collectWagers();
//        dealFlop();
//        collectWagers();
//        dealTurn();
//        collectWagers();
//        dealRiver();
//        determineBestScores();
//        awardWinnings();
//    }
//
//    /**
//     * Each player has 21 potential hands, one of which is the best, so the
//     * player has their best hand's score attached to them.
//     *
//     */
//    public void determineBestScores() {
//        for (HoldemPlayer player : players) {
//            if (player.isPlaying()) {
//                BestHand bestHand = new BestHand(new PotentialHands(player.getHand(), communityCards));
//                player.setBestHandScore(bestHand.getBestHandScore());
//            } else {
//                // Marker to tell us that this player didn't have a hand to score
//                player.setBestHandScore(-1);
//            }
//        }
//    }
//
//    /**
//     * Looks at each player's scores, determining the winner(s)
//     * @return a list of the winners as a boolean arraylist
//     */
//    public ArrayList<Boolean> determineWinner() {
//        ArrayList<Boolean> winners = new ArrayList<>();
//        ArrayList<Integer> scores = new ArrayList<>();
//        for (HoldemPlayer player : players) {
//            scores.add(player.getBestHandScore());
//        }
//        int bestScore = Collections.max(scores);
//        for (HoldemPlayer player : players) {
//            if (player.getBestHandScore() == bestScore) {
//                winners.add(true);
//            } else {
//                winners.add(false);
//            }
//        }
//        return winners;
//    }
//
//    /**
//     * Hands out the award to the winners (usually one winner; splits pot if
//     * more than one).
//     */
//    public void awardWinnings() {
//        ArrayList<Boolean> winners = determineWinner();
//        int numWinners = Collections.frequency(winners, true);
//        int award = pot.getTotal() / numWinners;
//
//        for (int i = 0; i < players.size(); i++) {
//            if (winners.get(i)) {
//                players.get(i).getBank().addToTotal(award);
//            }
//        }
//    }
//
//    public void collectWagers() {
//
//        for (int i = 0; i < players.size(); i++) {
//            HoldemPlayer thisPlayer = players.get(i);
//            if (thisPlayer.isPlaying()) {
//                playerTurn = i;
//                switch (choice) {
//                    case FOLD:
//                        // Collect no more bets this round
//                        thisPlayer.setPlaying(false);
//                        break;
//                    case CHECK:
//                        // Only possible if amountToMatch == currentWager
//                        break;
//                    case CALL:
//                        if (thisPlayer.getCurrentWagers() < amountToMatch) {
//                            thisPlayer.placeBet(
//                                    amountToMatch - thisPlayer.getCurrentWagers());
//                        }
//                        pot.addToTotal(amountToMatch - thisPlayer.getCurrentWagers());
//                        break;
//                    case RAISE:
//                        // To raise, you must match current amount bet, and add to that
//                        // Basically the player calls, then adds more.
//                        thisPlayer.placeBet((amountToMatch - thisPlayer.getCurrentWagers()) + RAISE_AMOUNT);
//                        pot.addToTotal((amountToMatch - thisPlayer.getCurrentWagers()) + RAISE_AMOUNT);
//                }
//            }
//        }
//        resetForNextCycle();
//    }
//
//    public void collectAntes(int ante) {
//        for (HoldemPlayer player : players) {
//            if (player.getBank().getTotal() >= ante) {
//                // Removes chips from players' banks
//                player.placeBet(ante);
//            } else {
//                // Can't afford the ante, so they don't play
//                player.setPlaying(false);
//            }
//        }
//    }
//
//    /**
//     * Deals a single card to each player until the last player has HAND_SIZE
//     * number of cards (in the case of Texas Holdem, 2 cards).
//     */
//    public void dealHands() {
//        // Get the last player's hand, get the cards from the hand, 
//        // and look at how many cards are in the hand.
//        while (players.get(players.size() - 1).getHand().getCards() == null 
//                || players.get(players.size() - 1).getHand().getCards().size() != HAND_SIZE) {
//            for (HoldemPlayer player : players) {
//                if (player.isPlaying()) {
//                    player.addCard(deck.dealCard());
//                }
//            }
//        }
//    }
//
//    /**
//     * Deals three cards to the table.
//     */
//    public void dealFlop() {
//        communityCards.addCard(deck.dealCard());
//        communityCards.addCard(deck.dealCard());
//        communityCards.addCard(deck.dealCard());
//    }
//
//    /**
//     * Deals a single card to the table.
//     */
//    public void dealTurn() {
//        communityCards.addCard(deck.dealCard());
//    }
//
//    /**
//     * Deals a single card to the table. Same as dealTurn, but different names
//     * makes the progression of the game in the code more explicit.
//     */
//    public void dealRiver() {
//        communityCards.addCard(deck.dealCard());
//    }
//
//    /**
//     * Reset current wagers and amountToMatch to 0 after a cycle of
//     * checks/calls/raises/folds.
//     */
//    public void resetForNextCycle() {
//        for (HoldemPlayer player : players) {
//            player.setCurrentWager(0);
//        }
//        amountToMatch = 0;
//    }
}
