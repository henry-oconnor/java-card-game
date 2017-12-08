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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jiach
 */
public class Client extends Application implements HoldemConstants{
    
        private GamePane gamePane = new GamePane();
        private LoginPane loginPane = new LoginPane();
        private Scene scene;
        private Socket socket;
        private DataOutputStream out;
        private DataInputStream in;
        
        // number of players in game
        private int numPlayers;
        private boolean myTurn;

    @Override
    public void start(Stage primaryStage) throws IOException {

        connectToServer();
        
        // launch login window
        scene = new Scene(loginPane, 1200, 800);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.show();
        
        
        // retrieves numPlayers and initial chip counts from server
        initializeGamePane();
        
        
        //function to send ante
        

        // launch game window
        scene = new Scene(gamePane);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.show();

        myTurn = false;
        
        // game loop
        while(true){
            // server should be in collectWagers function at this point waiting for an int
            gamePane.getButtonPressed();
            
            // i THINK this will make the program pause until a bool is received from the server
            myTurn = false;
            myTurn = in.readBoolean();
        }
        
        
        
        
        // the commented code below should go in a function
        
//        Card card1, card2;
//        card1 = new Card();
//        card2 = new Card();
//
//        gamePane.getSelfHoleCards().getChildren().addAll(card1.getImage().getImageView(), card2.getImage().getImageView());
//        gamePane.getLeftHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
//        gamePane.getTopHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
//        gamePane.getRightHoleCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98));
//        gamePane.getCommunityCards().getChildren().addAll(new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98), new Rectangle(72, 98));

    }

    /**
     * @param args the command line arguments
     */
    
    /**
     * initializes gui with number of players, chip counts, card counts
     * 
     * at the time this function is called, server should send an int 
     * representing player count, followed by ints representing the five chip counts
     * in ascending order
     * 
     * @throws IOException 
     */
    private void connectToServer(){
        try{
            socket = new Socket(SERVER_NAME, PORT_NUMBER);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void initializeGamePane() throws IOException{
        // listen for number of players from server
        numPlayers = in.readInt();
        
        // lay out 
        for(int i = 0; i < numPlayers; i++){
            gamePane.addWhiteChips(i, in.readInt());
            gamePane.addGreenChips(i, in.readInt());
            gamePane.addBlueChips(i, in.readInt());
            gamePane.addRedChips(i, in.readInt());
            gamePane.addBlackChips(i, in.readInt());

        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
