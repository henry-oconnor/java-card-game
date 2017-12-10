package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jiach
 */
public class Client extends Application implements HoldemConstants {

    private GamePane gamePane = new GamePane();
    private LoginPane loginPane = new LoginPane();
    private Scene scene;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    // number of players in game
    private int numPlayers;
    private boolean myTurn;
    private HoldemPlayer thisPlayer;

    @Override
    public void start(Stage primaryStage) throws IOException {

        thisPlayer = new HoldemPlayer();
        connectToServer();
        runGame();

        // retrieves numPlayers and initial chip counts from server
        //initializeGamePane();
        // System.out.println(openConnection);
        // System.out.println(in.readInt());
        Card card1 = thisPlayer.getHand().getCards().get(FIRST_CARD);
        Card card2 = thisPlayer.getHand().getCards().get(SECOND_CARD);

        gamePane.getSelfHoleCards().getChildren().addAll(card1.getImage().getImageView(), card2.getImage().getImageView());
        gamePane.getLeftHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getTopHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getRightHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        setupCommunityCards();

        scene = new Scene(gamePane);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.show();

//        myTurn = false;
//        // game loop
//        while(true){
//            // server should be in collectWagers function at this point waiting for an int
//            gamePane.getButtonPressed();
//            
//            // i THINK this will make the program pause until a bool is received from the server
//            myTurn = false;
//            myTurn = in.readBoolean();
//        }
//        Card card1, card2;
//        card1 = thisPlayer.getHand().getCards().get(FIRST_CARD);
//        card2 = thisPlayer.getHand().getCards().get(SECOND_CARD);
//
//        gamePane.getSelfHoleCards().getChildren().addAll(card1.getImage().getImageView(), card2.getImage().getImageView());
//        gamePane.getLeftHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
//        gamePane.getTopHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
//        gamePane.getRightHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
//        gamePane.getCommunityCards().getChildren().addAll(new Rectangle(72, 98),
//                new Rectangle(72, 98), new Rectangle(72, 98),
//                new Rectangle(72, 98), new Rectangle(72, 98));
//
//        // launch game window
//        scene = new Scene(gamePane);
//        scene.setFill(Color.BLACK);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Texas Hold'em");
//        primaryStage.show();
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
        for(Card card : gamePane.getCommunityCardsList().getCards()){
        gamePane.getCommunityCards().getChildren().add(card.getImage().getImageView());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
