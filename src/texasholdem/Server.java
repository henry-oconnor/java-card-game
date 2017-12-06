package texasholdem;

import javafx.application.Application;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Server extends Application
            implements HoldemConstants{
    
    // unused, server user could input a port number
    private int port;
    // client sockets
    private ArrayList<Socket> socketList;
    
    public void start(Stage primaryStage){
        TextArea log = new TextArea();
        
        Scene scene = new Scene(new ScrollPane(log), 450, 200);
        primaryStage.setTitle("Texas Hold'em Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // start a new 
        new Thread( ()-> {
            try{
                Platform.runLater(() -> log.appendText(new Date() 
                        + ": Running\n"));
                
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> log.appendText(new Date() 
                        + ": Server started at port 8000\n"));
                
                while(true){
                    socketList = new ArrayList<>();
                    while(socketList.size() < 4){

                        Platform.runLater(()-> log.appendText(new Date()
                        + ": Waiting for players"));

                        socketList.add(serverSocket.accept());
                        Platform.runLater(()->
                                log.appendText(new Date() + ": Player " + socketList.size() + " joined session\n"));

                        new DataOutputStream(
                                socketList.get(socketList.size()).getOutputStream()).writeInt(socketList.size());
 
                    }
                    Platform.runLater(()->
                            log.appendText(new Date() + ": Starting game..."));
                    
                    new Thread(new SessionHandler(socketList)).start();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }).start();
    }
    
    class SessionHandler implements Runnable, HoldemConstants{
        // contains players, deck, pot, and other key game data
        private GameBoard gameBoard;
        // connections to each client
        private ArrayList<Socket> socketList;
        
        // initialize socketList
        public SessionHandler(ArrayList<Socket> argList){
            for(Socket arg : argList){
                socketList.add(arg);
            }
            
            // create player for each client
            ArrayList<HoldemPlayer> playerList = new ArrayList<>();
            for(int i = 0; i < socketList.size(); i++)
                playerList.add(new HoldemPlayer());
            // initialize gameBoard
            gameBoard = new GameBoard(playerList);
        }
        
        public void run(){
            
        }
        
        // sends all clients an update of one player, the pot, and control vars
        public void updatePlayerState(DataOutputStream out, int playerIndex) throws IOException{
            out.flush();
            // owwwwwwwwww object oriented. should we write a cleaner way of getting the size of each chip stack?
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().whiteChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().redChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().blueChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().greenChips.size());
            out.writeInt(gameBoard.getPlayers().get(playerIndex).getBank().blackChips.size());
            
            
        }
    }

    public static void main(String args[]){
       launch(args);
    }
}
