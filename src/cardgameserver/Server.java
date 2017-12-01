package cardgameserver;

import javafx.application.Application;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Server extends Application{
    
    private InetAddress ip;
    private int port;
    private Thread listenThread;
    private ServerSocket serverSocket;
    private Socket socket;
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
                    Platform.runLater(()->log.appendText(new Date() + ": Player 1 joined session\n"));
                        
                        
                    
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

    }

}
