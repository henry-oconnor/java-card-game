/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        DeckOfCards deck = new DeckOfCards();

        // To test the shuffle function
        if (DEBUG_SHUFFLE) {
            // Call the shuffle function X number of times
            final int NUM_SHUFFLES = 52;
            int i = 0;
            while (i < NUM_SHUFFLES) {
                deck.shuffle();
                i++;
            }
        }

        // TEST OF THE DECKOFCARDS CLASS
        ObservableList<String> deckList = FXCollections.observableArrayList();
        for (int i = 0; i < deck.getNumCards(); i++) {
            deckList.add(deck.dealCard().toString());
        }

        ListView listViewDeck = new ListView(deckList);

        root.setCenter(listViewDeck);

        Scene scene = new Scene(root, 300, 250);

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

}
