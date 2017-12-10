package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private BorderPane pane;

    // number of players in game
    private int numPlayers;
    private boolean myTurn;
    private HoldemPlayer thisPlayer = new HoldemPlayer();


    @Override
    public void start(Stage primaryStage) throws IOException {
        loginPane = new LoginPane();
        gamePane = new GamePane();
        
        connectToServer();

        pane = new BorderPane();

        pane.setCenter(loginPane);

        //while (!loginPane.continueFlag);
        Button button = (Button) loginPane.getChildren().get(7);
        button.setOnAction(e -> {
            try {
                setGamePane();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //scene.setFill(Color.BLACK);

        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.show();

    }

    private void setGamePane() throws IOException {
        pane.setCenter(gamePane);

        gamePane.buttonPressed.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(oldValue + "changed " + "->" + newValue);
                try {
                    out.writeBoolean(true);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        runGame();


        Card card1 = thisPlayer.getHand().getCards().get(FIRST_CARD);
        Card card2 = thisPlayer.getHand().getCards().get(SECOND_CARD);
//        Card card1 = new Card();
//        Card card2 = new Card();

        gamePane.getSelfHoleCards().getChildren().addAll(card1.getImage().getImageView(), card2.getImage().getImageView());
        gamePane.getLeftHoleCards().getChildren().addAll(new BackOfTheCardImage().getImageView(), new BackOfTheCardImage().getImageView());
        gamePane.getTopHoleCards().getChildren().addAll(new BackOfTheCardImage().getImageView(), new BackOfTheCardImage().getImageView());
        gamePane.getRightHoleCards().getChildren().addAll(new BackOfTheCardImage().getImageView(), new BackOfTheCardImage().getImageView());
        setupCommunityCards();

//        out.writeBoolean(true);
    }

    public void runGame() throws IOException {
        boolean openConnection = true;
        while (openConnection) {
            int intIn = in.readInt();
            System.out.println("Int sent from server: " + intIn);
            switch (intIn) {
                case SEND_REDUCE_USER_BANK:
                    thisPlayer.getBank().decreaseTotal(MINIMUM_ANTE);
                    break;
                case SENDING_CARDS:
                    updateHandCards();
                    openConnection = false;
                    break;
                case COLLECTING_WAGERS:
                    do {
                        out.writeInt(gamePane.getButtonID());
                    } while (!in.readBoolean());
                    break;
                case DEALING_FLOP:
                    for (int i = 0; i < CARDS_IN_FLOP; i++) {
                        Card card = cardFromInt(in.readInt(), in.readInt());
                        gamePane.addToCommunityCards(card);
                    }
                    openConnection = false;
                    break;
                case DEALING_TURN: {
                    Card card = cardFromInt(in.readInt(), in.readInt());
                    gamePane.addToCommunityCards(card);
                }
                break;
                case DEALING_RIVER: {
                    Card card = cardFromInt(in.readInt(), in.readInt());
                    gamePane.addToCommunityCards(card);
                    //System.out.println(gamePane.getCommunityCardsList().toString());

                }
                break;
                case AWARDING_WINNINGS:
                    if (in.readInt() == WINNING_PLAYER) {
                        thisPlayer.getBank().addToTotal(in.readInt());
                    }
                    break;
                case RESETTING_GAME:
                    openConnection = false;
                    break;
            }
        }
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
     * initializes gui with number of players, chip counts, card counts
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

    public void setupCommunityCards() {
        for (Card card : gamePane.getCommunityCardsList().getCards()) {
            gamePane.getCommunityCards().getChildren().add(card.getImage().getImageView());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
