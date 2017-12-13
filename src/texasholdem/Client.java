package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static texasholdem.HoldemConstants.DEALING_FLOP;

/**
 *
 * @author jiach
 */
public class Client extends Application implements HoldemConstants {

    private GamePane gamePane;
    private LoginPane loginPane;
    private Scene scene;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private BorderPane pane;

    // number of players in game
    private int numPlayers;
    private boolean myTurn;
    private HoldemPlayer thisPlayer = new HoldemPlayer();

    @Override
    public void start(Stage primaryStage) throws IOException {
        pane = new BorderPane();
        loginPane = new LoginPane();
        gamePane = new GamePane();

        connectToServer();

        Button loginBtn = (Button) loginPane.getChildren().get(6);
        loginBtn.setOnAction(e -> {
            try {
                String username = ((TextField) loginPane.getChildren().get(4)).getText();
                String password = ((TextField) loginPane.getChildren().get(5)).getText();

                if (username.length() == 0 || password.length() == 0) {
                    System.out.println("Tell em you have to enter something");
                } else {
//                    out.writeInt(REQUEST_LOGIN);
//                    bufferedWriter.write(username + "\n");
//                    bufferedWriter.write(password + "\n");
                    setGamePane();
                    runGame(in.readInt());
                    // server will return boolean indicating outcome of login
//                    if (bufferedReader.readLine().equals("" + true)) {
//                        System.out.println("Logged in");
//                        setGamePane();
//                        runGame(in.readInt());
//                        runGame(in.readInt());
//                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button registerBtn = (Button) loginPane.getChildren().get(7);
        registerBtn.setOnAction(e -> {
            try {
                String username = ((TextField) loginPane.getChildren().get(4)).getText();
                String password = ((TextField) loginPane.getChildren().get(5)).getText();

//                if (username.length() == 0 || password.length() == 0) {
//                    System.out.println("Tell em you have to enter something");
//                } else {
//                    out.writeInt(REQUEST_REGISTER);
//                    bufferedWriter.write(username + "\n");
//                    bufferedWriter.write(password + "\n");
                    setGamePane();
                    runGame(in.readInt());
                    // server will return boolean indicating outcome of registration
//                    if (bufferedReader.readLine().equals("" + true)) {
//                        System.out.println("Registered");
//                        setGamePane();
//                        runGame(in.readInt());
//                        runGame(in.readInt());
//                    }
//                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        pane.setCenter(loginPane);

        scene = new Scene(pane, loginPane.getWidth(), loginPane.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.show();

    }

    private void createAccount() {

    }

    private void setGamePane() throws IOException {
        pane.setCenter(gamePane);

        gamePane.changeInt.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    runGame(in.readInt());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        gamePane.getSelfHoleCards()
                .getChildren()
                .addAll(new BackOfTheCardImage().getImageView(),
                        new BackOfTheCardImage().getImageView());
        gamePane.getLeftHoleCards()
                .getChildren()
                .addAll(new BackOfTheCardImage().getImageView(),
                        new BackOfTheCardImage().getImageView());
        gamePane.getTopHoleCards()
                .getChildren()
                .addAll(new BackOfTheCardImage().getImageView(),
                        new BackOfTheCardImage().getImageView());
        gamePane.getRightHoleCards()
                .getChildren()
                .addAll(new BackOfTheCardImage().getImageView(),
                        new BackOfTheCardImage().getImageView());

    }

    public boolean runGame(int input) throws IOException {
        int intIn = input;
        System.out.println("Int sent from server: " + intIn);
        switch (intIn) {
            case SEND_REDUCE_USER_BANK:
                System.out.println("ANTE");
                return true;
            case SENDING_CARDS:
                System.out.println("SENDING_CARDS");
                updateHandCards();
                return false;
            case COLLECTING_WAGERS:
                System.out.println("COLLECTING_WAGERS");
                do {
                    out.writeInt(gamePane.getButtonID());
                } while (!in.readBoolean());
                return true;
            case DEALING_FLOP:
                System.out.println("DEALING_FLOP");
                for (int i = 0; i < CARDS_IN_FLOP; i++) {
                    Card card = cardFromInt(in.readInt(), in.readInt());
                    gamePane.addToCommunityCards(card);
                }
                setupCommunityCards();
                return false;
            case DEALING_TURN: {
                System.out.println("DEALING_TURN");
                Card card = cardFromInt(in.readInt(), in.readInt());
                gamePane.addToCommunityCards(card);
                appendCard(card);
                return false;
            }
            case DEALING_RIVER: {
                System.out.println("DEALING_RIVER");
                Card card = cardFromInt(in.readInt(), in.readInt());
                gamePane.addToCommunityCards(card);
                appendCard(card);
                return false;
            }
            case AWARDING_WINNINGS:
                System.out.println("AWARDING_WINNINGS");
                System.out.println(bufferedReader.read());
                return true;
//            case RESETTING_GAME:
//                System.out.println("RESETTING_GAME");
//                resetGame();
//                return false;
        }

        return false;
    }

    public void resetGame() throws IOException {
        gamePane = new GamePane();
        setGamePane();
        pane.setCenter(gamePane);
        scene = new Scene(pane, gamePane.getWidth(), gamePane.getHeight());
    }

    /**
     * Updates the player's hand with newly dealt cards
     *
     * @throws IOException
     */
    public void updateHandCards() throws IOException {
        Card card1 = cardFromInt(in.readInt(), in.readInt());
        Card card2 = cardFromInt(in.readInt(), in.readInt());
        thisPlayer.addCards(card1, card2);
        updateGamePaneSelfHoleCards(card1, card2);
    }

    /**
     * Takes two integers and generates a new card object
     *
     * @param rankInt
     * @param suitInt
     * @return
     */
    public Card cardFromInt(int rankInt, int suitInt) {
        Card card = new Card(Suit.valueOf(suitInt), Rank.valueOf(rankInt));
        return card;
    }

    /**
     * @param args the command line arguments
     */
    /**
     * Opens data streams between client and server
     *
     * at the time this function is called, server should send an int
     * representing player count, followed by ints representing the five chip
     * counts in ascending order
     *
     * @throws IOException
     */
    private void connectToServer() {
        try {
            socket = new Socket(SERVER_NAME, PORT_NUMBER);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            numPlayers = in.readInt();
            System.out.println("numPlayers: " + numPlayers);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initializeGamePane() throws IOException {
        // listen for number of players from server
//        numPlayers = in.readInt();

        // lay out 
        for (int i = 0; i < numPlayers; i++) {
            //   gamePane.addWhiteChips(i, in.readInt());
            //   gamePane.addGreenChips(i, in.readInt());
            //  gamePane.addBlueChips(i, in.readInt());
            //  gamePane.addRedChips(i, in.readInt());
            //   gamePane.addBlackChips(i, in.readInt());

        }
    }

    /**
     * Removes all children from selfHoleCards and replaces
     * them with new images.
     * @param card1
     * @param card2 
     */
    public void updateGamePaneSelfHoleCards(Card card1, Card card2) {
        gamePane.getSelfHoleCards().getChildren().clear();
        gamePane.getSelfHoleCards().getChildren()
                .addAll(card1.getImage().getImageView(),
                        card2.getImage().getImageView());
    }

    /**
     * Updates the GamePane's communityCards hbox with the Flop.
     */
    public void setupCommunityCards() {
        for (Card card : gamePane.getCommunityCardsList().getCards()) {
            gamePane.getCommunityCards().getChildren().add(card.getImage().getImageView());
        }
    }

    public void appendCard(Card card) {
        gamePane.getCommunityCards().getChildren().add(card.getImage().getImageView());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
