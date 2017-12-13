package texasholdem;

import javafx.application.Application;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Server extends Application
        implements HoldemConstants {

    // unused, server user could bufferedReader a port number
    private int port;
    // client sockets
    private ArrayList<Socket> socketList;
    private Connection connection;
    private DataOutputStream out;
    private DataInputStream in;
    private TextArea log = new TextArea();
    private boolean readyToStart = false;

    // delete after debug 
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    int request;
    String username;
    String password;
    boolean result;

//
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

//                initializeJdbc();
//                DatabaseMetaData dmb = connection.getMetaData();
//                ResultSet rs = dmb.getCatalogs();
//                
//                Platform.runLater(() -> {
//                    try {
//                        rs.first();
//                        log.appendText(new Date()
//                                + ": Connected to schema \'" + rs.getString(1) + "\'\n");
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });
                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
                Platform.runLater(() -> log.appendText(new Date()
                        + ": Server started at port " + PORT_NUMBER + "\n"));

                socketList = new ArrayList<>();

                // while (true) {
                while (socketList.size() < 1) {

                    Platform.runLater(() -> log.appendText(new Date()
                            + ": Waiting for players\n"));

                    Socket client = serverSocket.accept();
                    socketList.add(client);

                    Platform.runLater(()
                            -> log.appendText(new Date() + ": Player " + socketList.size() + " joined session\n"));

                    new DataOutputStream(
                            socketList.get(socketList.size() - 1).getOutputStream()).writeInt(socketList.size());

                    Platform.runLater(()
                            -> log.appendText(new Date() + ": Starting game...\n"));

                    // delete after debug        
                    bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    in = new DataInputStream(client.getInputStream());
                    System.out.println("In LoginHandler thread");
//                                        try {
//                                            request = in.readInt();
//                                            username = bufferedReader.readLine();
//                                            password = bufferedReader.readLine();
//                                            
//                                            if(request == REQUEST_LOGIN){
//                                                result = verifyLogin(username, password);
//                                                System.out.println("Login status: " + result);
//                                                bufferedWriter.write("" + result);
//                                            }
//                                            else if(request == REQUEST_REGISTER){
//                                                result = createAccount(username, password);
//                                                System.out.println("Registration status: " + result);
//                                                bufferedWriter.write("" + result);
//                                            }
//                                        } catch (Exception ex) {
//                                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                                        }
//                                        finally{
//                                            try {
//                                                bufferedReader.close();                
//                                                bufferedWriter.close();
//                                            } catch (IOException ex) {
//                                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                                            }
//                                        }
                    //    

                    new Thread(new SessionHandler(socketList)).start();
                }
                //     }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    class LoginHandler implements Runnable, HoldemConstants {

        BufferedReader input;
        DataOutputStream output;
        int request;
        String username;
        String password;
        boolean result;

        public LoginHandler(Socket client, Connection connection) throws IOException {
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new DataOutputStream(client.getOutputStream());
        }

        @Override
        public void run() {
            System.out.println("In LoginHandler thread");
            try {
                request = input.read();
                username = input.readLine();
                password = input.readLine();

                if (request == REQUEST_LOGIN) {
                    result = verifyLogin(username, password);
                    System.out.println("Login status: " + result);
                    output.writeBoolean(result);
                } else if (request == REQUEST_REGISTER) {
                    result = createAccount(username, password);
                    System.out.println("Registration status: " + result);
                    output.writeBoolean(result);
                }
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class SessionHandler implements Runnable, HoldemConstants {

        private int highScore = 0;

        // contains players, deck, pot, and other key game data
        private final GameBoard gameBoard;
        // connections to each client
        private ArrayList<Socket> socketList;

        // initialize socketList
        public SessionHandler(ArrayList<Socket> argList) throws IOException {
            socketList = argList;

            // create player for each client
            ArrayList<HoldemPlayer> playerList = new ArrayList<>();
            for (Socket socket : socketList) {
                playerList.add(new HoldemPlayer());
            }
            // initialize gameBoard
            gameBoard = new GameBoard(playerList);
            // Open the streams
            in = new DataInputStream(socketList
                    .get(socketList.size() - 1)
                    .getInputStream());
            out = new DataOutputStream(socketList
                    .get(socketList.size() - 1)
                    .getOutputStream());
        }

        @Override
        public void run() {
            try {
                collectAntes();
                dealCards();
                sendHandCards();
                dealCommunityCards();
                dealFlop();
                dealTurn();
                dealRiver();
                setBestHands();
                notifyWinners(determineWinners(determineHighestScore()));
                reset();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

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
                out.writeInt(SEND_REDUCE_USER_BANK);
            }
        }

        public void setBestHands() throws IOException {
            for (HoldemPlayer player : gameBoard.getPlayers()) {
                PotentialHands potentialHands = new PotentialHands(player.getHand(), gameBoard.getCommunityCards());
                BestHand bestHand = new BestHand(potentialHands);
                player.setBestHandScore(bestHand.getBestHandScore());

                System.out.println(gameBoard.getCommunityCards());
                System.out.println(player.getHand());
            }
        }

        public int determineHighestScore() {
            int highestScore = gameBoard.getPlayers().get(0).getBestHandScore();
            for (HoldemPlayer player : gameBoard.getPlayers()) {
                if (player.getBestHandScore() > highestScore) {
                    highestScore = player.getBestHandScore();
                }
            }
            this.highScore = highestScore;
            return highestScore;
        }

        public ArrayList<Boolean> determineWinners(int highestScore) {
            ArrayList<Boolean> winners = new ArrayList<>();
            for (HoldemPlayer player : gameBoard.getPlayers()) {
                if (player.getBestHandScore() == highestScore) {
                    winners.add(true);
                } else {
                    winners.add(false);
                }
            }
            return winners;
        }

        /**
         * Takes the score, gets the first digit of the score, and passes it
         * into PokerHandRanking's static getRankingName function.
         *
         * @param player
         * @return
         */
        public String determineHandType(int score) {
            char firstChar = (score + "").charAt(0);
            return PokerHandRanking.getRankingName(firstChar - '0');
        }

        public void notifyWinners(ArrayList<Boolean> winners) throws IOException {
            out.writeInt(AWARDING_WINNINGS);
            String winnerString = "Winner(s):\n";
            for (int i = 0; i < winners.size(); i++) {
                if (winners.get(i) == true) {
                    winnerString += gameBoard.getPlayers().get(i).getPlayerName();
                    winnerString += "\n";
                }
            }
            //winnerString += "With: " + determineHandType(highScore);
            //System.out.println(winnerString);
            bufferedWriter.write(winnerString);

        }

//        public void updateClientBank(int amount, int socketIndex) throws IOException {
//            out = new DataOutputStream(socketList.get(socketIndex).getOutputStream());
//            // Reduce player's pot by this much
//            out.writeInt(amount);
//        }
        /**
         * Deals first three community cards to the players.
         *
         * @throws IOException
         */
        public void dealFlop() throws IOException {
            out.writeInt(DEALING_FLOP);
            dealCardToBoard(0);
            dealCardToBoard(1);
            dealCardToBoard(2);
        }

        /**
         * Deals fourth card out to the players.
         *
         * @throws IOException
         */
        public void dealTurn() throws IOException {
            out.writeInt(DEALING_TURN);
            dealCardToBoard(3);
        }

        /**
         * Deals final card to the players
         *
         * @throws IOException
         */
        public void dealRiver() throws IOException {
            out.writeInt(DEALING_RIVER);
            dealCardToBoard(4);

        }

        /**
         * Sends the cards from the community cards to the players.
         *
         * @param numToDeal
         */
        public void dealCardToBoard(int index) throws IOException {
            for (Socket socket : socketList) {
                sendCard(gameBoard.getCommunityCards().getCards().get(index));
            }
        }

        /**
         * Add a card to each player's hands.
         */
        public void dealCards() {
            for (int i = 0; i < gameBoard.getHAND_SIZE(); i++) {
                for (int j = 0; j < gameBoard.getPlayers().size(); j++) {
                    gameBoard.getPlayers().get(j)
                            .addCard(gameBoard.getDeck().dealCard());

                }
            }
        }

        public void dealSingleToBoard() {
            Card card = gameBoard.getDeck().dealCard();
            gameBoard.getCommunityCards().addCard(card);
        }

        /**
         * Sets the community cards before the game begins.
         */
        public void dealCommunityCards() {
            gameBoard.getDeck().burnCard();
            dealSingleToBoard();
            dealSingleToBoard();
            dealSingleToBoard();
            gameBoard.getDeck().burnCard();
            dealSingleToBoard();
            gameBoard.getDeck().burnCard();
            dealSingleToBoard();
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

//        // sends all clients an update of one player, the pot, and control vars
//        public void updatePlayerState(int playerIndex) throws IOException {
//            out.flush();
//
//            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().whiteChips.size());
//            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().redChips.size());
//            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().blueChips.size());
//            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().greenChips.size());
//            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().blackChips.size());
//
//        }
        // send client the number of players, and chip counts for each player
//        private void initializeClientGui() throws IOException {
//            for (int i = 0; i < socketList.size(); i++) {
//                out = new DataOutputStream(socketList.get(i).getOutputStream());
//                out.writeInt(socketList.size());
//
//                for (int j = 0; j < socketList.size(); j++) {
//                    updatePlayerState(j);
//                }
//            }
//        }
    }

    public void waitForConfirmation(ArrayList<Socket> socketList) throws IOException {
        for (Socket s : socketList) {
            new DataInputStream(s.getInputStream()).readBoolean();
        }
    }

    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/holdemDB", "group", "group");

//            statement = connection.prepareStatement("insert into users (username,"
//                    + " password, wincount) values (" + username + password + wincount
//                + ") on duplicate key set wincount = wincount + 1");
            /**
             * connect to or create sql table here
             */
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean createAccount(String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into users (username, password)"
                + "values ((?), (?))");
        statement.setString(1, username);
        statement.setString(2, password);

        // if account is created successfully, try to log in
        if (statement.execute()) {
            return verifyLogin(username, password);
        }

        return false;
    }

    private boolean verifyLogin(String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select password from users"
                + " where username = (?)");
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()) {
            return false;
        }
        rs.first();

        if (rs.getString(1).equals(password)) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        launch(args);
    }
}
