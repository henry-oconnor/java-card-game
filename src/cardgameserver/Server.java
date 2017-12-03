package cardgameserver;

import javafx.application.Application;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import texasholdem.GameBoard;

public class Server extends Application
            implements HoldemConstants{
    
    private InetAddress ip;
    private int port;
    private Thread listenThread;
    private ServerSocket serverSocket;
    private DataInputStream clientInput;
    private DataOutputStream serverOutput;
    
    public void start(Stage primaryStage){
        TextArea log = new TextArea();
        
        Scene scene = new Scene(new ScrollPane(log), 450, 200);
        primaryStage.setTitle("Texas Hold'em Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread( ()-> {
            try{
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> log.appendText(new Date() 
                        + ": Server started at socket 8000\n"));
                
                while(true){
                    Platform.runLater(()-> log.appendText(new Date()
                    + ": Waiting for players"));
                    
                    Socket player1 = serverSocket.accept();
                    Platform.runLater(()->
                            log.appendText(new Date() + ": Player 1 joined session\n"));
                    
                    new DataOutputStream(
                            player1.getOutputStream()).writeInt(PLAYER1);
                    
                    
                    Socket player2 = serverSocket.accept();
                    Platform.runLater(()->
                            log.appendText(new Date() + ": Player 2 joined session\n"));
                    new DataOutputStream(
                            player2.getOutputStream()).writeInt(PLAYER2);                    
                    
                    
                    Socket player3 = serverSocket.accept();
                    new DataOutputStream(
                            player3.getOutputStream()).writeInt(PLAYER4);
                    Platform.runLater(()->
                            log.appendText(new Date() + ": Player 3 joined session\n"));
                    
                    
                    Socket player4 = serverSocket.accept();
                    new DataOutputStream(
                            player4.getOutputStream()).writeInt(PLAYER4);
                    Platform.runLater(()->
                            log.appendText(new Date() + ": Player 4 joined session\n"));
                            
                    Platform.runLater(()->
                            log.appendText(new Date() + ": Starting game..."));
                    
                    
                    new Thread(new SessionHandler(player1, player2, player3, player4)).start();
                    
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }).start();
    }
    
    class SessionHandler implements Runnable, HoldemConstants{
        private GameBoard gameBoard;
        private Socket player1;
        private Socket player2;
        private Socket player3;
        private Socket player4;
        
        public SessionHandler(Socket p1, Socket p2, Socket p3, Socket p4){
            player1 = p1;
            player2 = p2;
            player3 = p3;
            player4 = p4;
        }
        
        public void run(){
            
        }
    }

}
