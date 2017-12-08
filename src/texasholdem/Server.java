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

    public void start(Stage primaryStage) {
        TextArea log = new TextArea();

        Scene scene = new Scene(new ScrollPane(log), 450, 200);
        primaryStage.setTitle("Texas Hold'em Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        // start a new 
        new Thread(() -> {
            try {
                Platform.runLater(() -> log.appendText(new Date()
                        + ": Running\n"));

                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> log.appendText(new Date()
                        + ": Server started at port 8000\n"));

                while (true) {
                    socketList = new ArrayList<>();
                    while (socketList.size() < 4) {

                        Platform.runLater(() -> log.appendText(new Date()
                                + ": Waiting for players"));

                        socketList.add(serverSocket.accept());
                        Platform.runLater(()
                                -> log.appendText(new Date() + ": Player " + socketList.size() + " joined session\n"));

                        new DataOutputStream(
                                socketList.get(socketList.size()).getOutputStream()).writeInt(socketList.size());

                    }
                    Platform.runLater(()
                            -> log.appendText(new Date() + ": Starting game..."));

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
            for (Socket arg : argList) {
                socketList.add(arg);
            }

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
                // send player count and chip counts to player
                initializeClientGui();
                
                collectAntes();
                dealCards();
                sendCards();
                collectWagers();
                dealFlop();
                collectWagers();
                dealTurn();
                collectWagers();
                dealRiver();
                awardWinnings(determineWinner());
                reset();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        public void collectWagers() throws IOException {
            for (int i = 0; i < socketList.size(); i++) {
                HoldemPlayer player = gameBoard.getPlayers().get(i);
                if (player.isPlaying()) {
                    in = new DataInputStream(socketList.get(i).getInputStream());
                    int userChoice = in.readInt();

                    int amountToMatch = gameBoard.getAmountToMatch();
                    int playerTotal = gameBoard.getPlayers().get(i).getBank().getTotal();

                    switch (userChoice) {
                        case FOLD:
                            player.setPlaying(false);
                            break;
                        case CHECK:
                            // Do nothing
                            break;
                        case CALL:
                            if (player.getBank().getTotal() > amountToMatch) {
                                updatePot(amountToMatch, i, i);
                                // Just take everything if they don't have enough
                            } else {
                                updatePot(playerTotal, i, i);
                            }
                            break;
                        case RAISE:
                            updatePot(RAISE_AMOUNT + amountToMatch, i, i);
                            amountToMatch += RAISE_AMOUNT;
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
            out
                    = new DataOutputStream(socketList
                            .get(socketIndex).getOutputStream());
            // Reduce player's pot by this much
            out.writeInt(amount);
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
            int numWinners = Collections.frequency(winners, true);
            int award = gameBoard.getPot().getTotal() / numWinners;

            for (int i = 0; i < gameBoard.getPlayers().size(); i++) {
                if (winners.get(i)) {
                    gameBoard.getPlayers().get(i).getBank().addToTotal(award);
                    updateClientBank(award, i, i);
                }
            }
        }

        public void updateClientBank(int amount, int playerIndex, int socketIndex) throws IOException {
            out = new DataOutputStream(socketList.get(socketIndex).getOutputStream());
            // Reduce player's pot by this much
            out.writeInt(amount);
        }

        /**
         * Deals three cards to the gameboard, notifying the clients of the
         * cards.
         */
        public void dealFlop() throws IOException {
            dealCardToBoard(3);
        }

        /**
         * dealTurn and dealFlop are the same, but have different names to match
         * the naming of the texas holdem game. They deal one card to the
         * gameboard.
         */
        public void dealTurn() throws IOException {
            dealCardToBoard(1);
        }

        public void dealRiver() throws IOException {
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
                    sendCard(card, socket);
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
                    
                // send new chip totals to client
                    updateClientBank(MINIMUM_ANTE, i, i);
                    
                }
            }
        }

        /**
         * Add a card to each player's hands.
         */
        public void dealCards() {
            ArrayList<HoldemPlayer> players = gameBoard.getPlayers();
            for (int i = 0; i < gameBoard.getHAND_SIZE(); i++) {
                if (players.get(i).isPlaying()) {
                    players.get(i).addCard(gameBoard.getDeck().dealCard());
                }
            }
        }
        /**
         * Puts amountToMatch back to 0 and creates a new shuffled deck
         */
        public void reset(){
            gameBoard.setAmountToMatch(0);
            DeckOfCards deck = new DeckOfCards();
            deck.manyShuffles(NUM_SHUFFLES);
            gameBoard.setDeck(deck);
        }

        /**
         * Uses the sendCard function twice to send the hand to the player.
         *
         * @throws IOException
         */
        public void sendCards() throws IOException {
            ArrayList<HoldemPlayer> players = gameBoard.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                Card firstCard = players.get(i).getHand().getCards().get(0);
                Card secondCard = players.get(i).getHand().getCards().get(1);

                sendCard(firstCard, socketList.get(i));
                sendCard(secondCard, socketList.get(i));
            }
        }

        /**
         * Send a card to a socket
         *
         * @param card
         * @throws IOException
         */
        public void sendCard(Card card, Socket socket) throws IOException {
            int rankInt = card.getRank().ordinal();
            int suitInt = card.getSuit().ordinal();

            out = new DataOutputStream(socket.getOutputStream());
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
            for(int i = 0; i < socketList.size(); i++){
                out = new DataOutputStream(socketList.get(i).getOutputStream());
                out.writeInt(socketList.size());
                
                for(int j = 0; j < socketList.size(); j++){
                    updatePlayerState(j);
                }
            }
        }

    }

    public static void main(String args[]) {
        launch(args);
    }
}
