package texasholdem;

import javafx.application.Application;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Server extends Application
        implements HoldemConstants {

    // unused, server user could input a port number
    private int port;
    // client sockets
    private ArrayList<Socket> socketList;

    private DataOutputStream out;
    private DataInputStream in;
    private TextArea log = new TextArea();

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(new ScrollPane(log), 450, 200);
        primaryStage.setTitle("Texas Hold'em Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        // start a new 
        new Thread(() -> {
            try {
                Platform.runLater(() -> log.appendText(new Date()
                        + ": Running\n"));

                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
                Platform.runLater(() -> log.appendText(new Date()
                        + ": Server started at port " + PORT_NUMBER + "\n"));

                while (true) {
                    socketList = new ArrayList<>();
                    while (socketList.size() < 1) {

                        Platform.runLater(() -> log.appendText(new Date()
                                + ": Waiting for players\n"));

                        Socket client = serverSocket.accept();
                        socketList.add(client);
                        Platform.runLater(()
                                -> log.appendText(new Date() + ": Player " + socketList.size() + " joined session\n"));

                        new DataOutputStream(
                                socketList.get(socketList.size() - 1).getOutputStream()).writeInt(socketList.size());

                    }
                    Platform.runLater(()
                            -> log.appendText(new Date() + ": Starting game...\n"));

//                    waitForConfirmation(socketList);
                    new Thread(new SessionHandler(socketList)).start();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    class SessionHandler implements Runnable, HoldemConstants {

        // contains players, deck, pot, and other key game data
        private final GameBoard gameBoard;
        // connections to each client
        private ArrayList<Socket> socketList;

        // initialize socketList
        public SessionHandler(ArrayList<Socket> argList) {
            socketList = argList;

            // create player for each client
            ArrayList<HoldemPlayer> playerList = new ArrayList<>();
            for (Socket socket : socketList) {
                playerList.add(new HoldemPlayer());
            }
            // initialize gameBoard
            gameBoard = new GameBoard(playerList);
        }

        @Override
        public void run() {
            try {

                collectAntes();
                dealCards();
                sendHandCards();
//                collectWagers();
                dealFlop();
//                collectWagers();
                dealTurn();
//                collectWagers();
                dealRiver();
//                awardWinnings(determineWinner());
                reset();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        public void beginGame() throws IOException {
            out.writeBoolean(OPEN_CONNECTION);
        }

        public void collectWagers() throws IOException {
            out.writeInt(COLLECTING_WAGERS);        // Client flag
            for (int i = 0; i < socketList.size(); i++) {
                HoldemPlayer player = gameBoard.getPlayers().get(i);
                if (player.isPlaying()) {
                    int userChoice = in.readInt();

                    int amountToMatch = gameBoard.getAmountToMatch();
                    int playerTotal = gameBoard.getPlayers().get(i).getBank().getTotal();

                    switch (userChoice) {
                        case FOLD:
                            player.setPlaying(false);
                            out.writeBoolean(true);    // Choice was good
                            break;
                        case CHECK:
                            // If nothing needs to be bet, it's okay to check
                            out.writeBoolean(amountToMatch == 0);
                            break;
                        case CALL:
                            if (player.getBank().getTotal() > amountToMatch) {
                                updatePot(amountToMatch, i, i);
                                // Just take everything if they don't have enough
                            } else {
                                updatePot(playerTotal, i, i);
                            }
                            out.writeBoolean(true);
                            break;
                        case RAISE:
                            if (player.getBank().getTotal() >= RAISE_AMOUNT + amountToMatch) {
                                updatePot(RAISE_AMOUNT + amountToMatch, i, i);
                                amountToMatch += RAISE_AMOUNT;
                                out.writeBoolean(true);
                            } else {
                                out.writeBoolean(false);
                            }
                            break;
                    }
                }
            }

        }

        /**
         * Adds to the pot and subtracts from the user's bank
         *
         * @param amount
         * @param socketIndex
         * @param playerIndex
         * @throws IOException
         */
        public void updatePot(int amount, int socketIndex, int playerIndex) throws IOException {
            gameBoard.getPot().addToTotal(amount);
            gameBoard.getPlayers().get(playerIndex)
                    .getBank().decreaseTotal(amount);
            out = new DataOutputStream(socketList
                    .get(socketIndex).getOutputStream());
            // Reduce player's pot by this much
            out.writeInt(amount);

            waitForConfirmation(socketList);

        }

        /**
         * Looks at each player's scores, determining the winner(s)
         *
         * @return a list of the winners as a boolean arraylist
         */
        public ArrayList<Boolean> determineWinner() {
            ArrayList<Boolean> winners = new ArrayList<>();
            ArrayList<Integer> scores = new ArrayList<>();
            ArrayList<HoldemPlayer> players = gameBoard.getPlayers();

            for (HoldemPlayer player : players) {
                scores.add(player.getBestHandScore());
            }
            int bestScore = Collections.max(scores);
            for (HoldemPlayer player : players) {
                if (player.getBestHandScore() == bestScore) {
                    winners.add(true);
                } else {
                    winners.add(false);
                }
            }
            return winners;
        }

        /**
         * Each player has 21 potential hands, one of which is the best, so the
         * player has their best hand's score attached to them.
         *
         */
        public void determineBestScores() {
            for (HoldemPlayer player : gameBoard.getPlayers()) {
                if (player.isPlaying()) {
                    BestHand bestHand
                            = new BestHand(new PotentialHands(player.getHand(),
                                    gameBoard.getCommunityCards()));
                    player.setBestHandScore(bestHand.getBestHandScore());
                } else {
                    // Marker to tell us that this player didn't have a hand to score
                    player.setBestHandScore(-1);
                }
            }
        }

        public void awardWinnings(ArrayList<Boolean> winners) throws IOException {
            out.writeInt(AWARDING_WINNINGS);
            int numWinners = Collections.frequency(winners, true);
            int award = gameBoard.getPot().getTotal() / numWinners;

            for (int i = 0; i < gameBoard.getPlayers().size(); i++) {
                if (winners.get(i)) {
                    gameBoard.getPlayers().get(i).getBank().addToTotal(award);
                    out.writeInt(WINNING_PLAYER);
                    out.writeInt(award);
                } else {
                    out.writeInt(LOSING_PLAYER);
                }
            }
        }

        public void updateClientBank(int amount, int socketIndex) throws IOException {
            out = new DataOutputStream(socketList.get(socketIndex).getOutputStream());
            // Reduce player's pot by this much
            out.writeInt(amount);
        }

        /**
         * Deals three cards to the gameboard, notifying the clients of the
         * cards.
         */
        public void dealFlop() throws IOException {
            out.writeInt(DEALING_FLOP);
            dealCardToBoard(3);
        }

        /**
         * dealTurn and dealFlop are the same, but have different names to match
         * the naming of the texas holdem game. They deal one card to the
         * gameboard.
         */
        public void dealTurn() throws IOException {
            out.writeInt(DEALING_TURN);
            dealCardToBoard(1);
        }

        public void dealRiver() throws IOException {
            out.writeInt(DEALING_RIVER);
            dealCardToBoard(1);
        }

        /**
         * Deals a card to the board and tells the client the card info. Burns a
         * card before dealing, as is the custom in texas holdem.
         *
         * @param numToDeal
         */
        public void dealCardToBoard(int numToDeal) throws IOException {
            gameBoard.getDeck().burnCard();
            for (int i = 0; i < numToDeal; i++) {
                Card card = gameBoard.getDeck().dealCard();
                gameBoard.getCommunityCards().addCard(card);
                for (Socket socket : socketList) {
                    sendCard(card);
                }
            }

        }

        /**
         * Checks that a user has enough money to ante. If they do, take the
         * ante and update information. Otherwise, set that the user isn't
         * playing
         *
         * @throws IOException
         */
        public void collectAntes() throws IOException {
            ArrayList<HoldemPlayer> players = gameBoard.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getBank().getTotal() < MINIMUM_ANTE) {
                    players.get(i).setPlaying(false);
                } else {
                    gameBoard.getPot().addToTotal(MINIMUM_ANTE);

                    // reduce player chip count
                    players.get(i).getBank().decreaseTotal(MINIMUM_ANTE);

                    out = new DataOutputStream(socketList.get(i).getOutputStream());
                    out.writeInt(SEND_REDUCE_USER_BANK);

                }
            }
        }

        /**
         * Add a card to each player's hands.
         */
        public void dealCards() {
            ArrayList<HoldemPlayer> players = gameBoard.getPlayers();
            for (int i = 0; i < gameBoard.getHAND_SIZE(); i++) {
                for (int j = 0; j < gameBoard.getPlayers().size(); j++) {
                    if (players.get(j).isPlaying()) {
                        players.get(j).addCard(gameBoard.getDeck().dealCard());
                    }
                }
            }
        }

        /**
         * Puts amountToMatch back to 0 and creates a new shuffled deck
         */
        public void reset() throws IOException {
            gameBoard.setAmountToMatch(0);
            DeckOfCards deck = new DeckOfCards();
            deck.manyShuffles(NUM_SHUFFLES);
            gameBoard.setDeck(deck);
            out.writeInt(RESETTING_GAME);
        }

        /**
         * Uses the sendCard function twice to send the hand to the player.
         *
         * @throws IOException
         */
        public void sendHandCards() throws IOException {
            out.writeInt(SENDING_CARDS);
            ArrayList<HoldemPlayer> players = gameBoard.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                Card firstCard = players.get(i).getHand().getCards().get(0);
                Card secondCard = players.get(i).getHand().getCards().get(1);

                sendCard(firstCard);
                sendCard(secondCard);
            }
        }

        /**
         * Send a card to a socket
         *
         * @param card
         * @throws IOException
         */
        public void sendCard(Card card) throws IOException {
            int rankInt = card.getRank().ordinal();
            int suitInt = card.getSuit().ordinal();

            // Send values to the client
            out.writeInt(rankInt);
            out.writeInt(suitInt);

        }

        // sends all clients an update of one player, the pot, and control vars
        public void updatePlayerState(int playerIndex) throws IOException {
            out.flush();

            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().whiteChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().redChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().blueChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().greenChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().blackChips.size());

        }

        // send client the number of players, and chip counts for each player
        private void initializeClientGui() throws IOException {
            for (int i = 0; i < socketList.size(); i++) {
                out = new DataOutputStream(socketList.get(i).getOutputStream());
                out.writeInt(socketList.size());

                for (int j = 0; j < socketList.size(); j++) {
                    updatePlayerState(j);
                }
            }
        }

    }

    public void waitForConfirmation(ArrayList<Socket> socketList) throws IOException {
        for (Socket s : socketList) {
            new DataInputStream(s.getInputStream()).readBoolean();
        }
    }

    public static void main(String args[]) {
        launch(args);
    }
}
