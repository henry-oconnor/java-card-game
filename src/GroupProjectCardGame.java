/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.EmptyStackException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author 09/20/17
 *
 * NOTE: This is currently a testing area for classes. The logic of the card
 * game will come later, likely in its own class.
 *
 * This file will be a launcher for the game with a splash screen and the like.
 *
 * MODIFICATIONS:
 */
public class GroupProjectCardGame extends Application {

    // Turn this to false to prevent shuffling
    final boolean DEBUG_SHUFFLE = true;
    final int NUM_SHUFFLES = 10;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        DeckOfCards deck = new DeckOfCards();
        DeckOfCards secondDeck = new DeckOfCards();

        // To test the shuffle function
        if (DEBUG_SHUFFLE) {
            int i = 0;
            while (i < NUM_SHUFFLES) {
                deck.shuffle();
                secondDeck.shuffle();
                i++;
            }
        }

        // TEST OF THE DECKOFCARDS CLASS
        ObservableList<String> deckList = FXCollections.observableArrayList();
        ObservableList<String> secondDeckList = FXCollections.observableArrayList();
        for (int i = 0; i < deck.getDeckSize(); i++) {
            deckList.add(deck.dealCard().toString());
            secondDeckList.add(secondDeck.dealCard().toString());
        }

        ListView listViewDeck = new ListView(deckList);
        ListView listViewSecondDeck = new ListView(secondDeckList);

        
        DeckOfCards handTestDeck = new DeckOfCards();
        ObservableList<String> handList = FXCollections.observableArrayList();
        HoldemHand hand = makeTestHand(handTestDeck);

        for (Card card : hand.getHand()) {
            handList.add(card.toString());
        }
        ListView listViewHand = new ListView(handList);

        root.setLeft(listViewDeck);
        //      root.setRight(listViewSecondDeck);
        root.setRight(listViewHand);

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Deck test");
        primaryStage.setScene(scene);
        primaryStage.show();

        Chip c = new Chip(5);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Create a HoldemHand object and deal cards to it.
     *
     * @param deck
     * @return a HoldemHand with two cards dealt from the top of the deck
     */
    public HoldemHand makeTestHand(DeckOfCards deck) {
        HoldemHand hand = new HoldemHand();
        for (int i = 0; i < hand.getHandSize(); i++) {
            try {
                hand.addCard(deck.dealCard());
            } catch (EmptyStackException e) {
                System.out.println(e.getMessage());
            }
        }
        return hand;
    }

}
