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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jiach
 */
public class Client extends Application {

    @Override
    public void start(Stage primaryStage) {
        GamePane gamePane = new GamePane();
        LoginPane logn = new LoginPane();
        Scene scene = new Scene(gamePane, 1200, 800);

        //LoginPane logn=new LoginPane();
        Card card1, card2;
        card1 = new Card();
        card2 = new Card();

        ChipImage c = new ChipImage(1);

        gamePane.getSelfHoleCards().getChildren().addAll(card1.getImage().getImageView(), card2.getImage().getImageView());
        gamePane.getLeftHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getTopHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getRightHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
        gamePane.getCommunityCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98));

        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.show();

        PlayerBank a = new PlayerBank(1000);

        gamePane.addBlackChips(0, 55);
        //   gamePane.addBlackChips(79);
        gamePane.addGreenChips(0, 80);
        gamePane.addBlueChips(0, 80);
        gamePane.addRedChips(0, 80);
        gamePane.addWhiteChips(0, 80);

        gamePane.addBlackChips(1, 55);
        //   gamePane.addBlackChips(79);
        gamePane.addGreenChips(1, 80);
        gamePane.addBlueChips(1, 80);
        gamePane.addRedChips(1, 80);
        gamePane.addWhiteChips(1, 80);

        gamePane.addBlackChips(2, 55);
        //   gamePane.addBlackChips(79);
        gamePane.addGreenChips(2, 80);
        gamePane.addBlueChips(2, 80);
        gamePane.addRedChips(2, 80);
        gamePane.addWhiteChips(2, 80);

        gamePane.addBlackChips(3, 55);
        //   gamePane.addBlackChips(79);
        gamePane.addGreenChips(3, 80);
        gamePane.addBlueChips(3, 80);
        gamePane.addRedChips(3, 80);
        gamePane.addWhiteChips(3, 80);

        HoldemPlayer b = new HoldemPlayer();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
