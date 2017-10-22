package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jiach
 */
public class Client extends Application {

    @Override
    public void start(Stage primaryStage) {
        BackGroundMusic bgm = new BackGroundMusic();
        GamePane gamePane = new GamePane();
        SettingPane settingPane = new SettingPane(bgm);
        Scene scene = new Scene(gamePane, 1200, 800);
        Scene settingScene = new Scene(settingPane, 300, 400);

        // Calling this function for testing purposes.
        // Keeps the music from playing each time the program is started up.
        // Will be removed.
        // bgm.muteMusic();

        bgm.getMediaPlayer().play();

        Card card1, card2;
        card1 = new Card();
        card2 = new Card();

        ChipImage c = new ChipImage(1);

        gamePane.getHoleCards().getChildren().addAll(card1.getImage().getImageView(), card2.getImage().getImageView());
        gamePane.getHoleCards1().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getHoleCards2().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getHoleCards3().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getCommunityCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98));

        gamePane.getSettingBtm().setOnAction(e -> {
            primaryStage.setScene(settingScene);
        });
        settingPane.getDoneBtm().setOnAction(e -> {
            primaryStage.setScene(scene);
        });

        primaryStage.setScene(settingScene);
        primaryStage.setTitle("Texas Hold'em");

        /*
        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);*/
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
