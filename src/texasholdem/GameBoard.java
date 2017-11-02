/*
 * Henry O'Connor
 */
package texasholdem;

import java.util.ArrayList;

/**
 *
 * @author henoc
 */
public class GameBoard {
    ArrayList<Player> players;
    ArrayList<Card> gameCards;
    int pool;
    DeckOfCards deck;
    Pot pot;
    
    
    void addCard(Card card){
        gameCards.add(card);
    }
    
    Card deal(){
        for(int i = 0; i < players.size(); i++){
            deck.dealCard();
        }
     
   }
}
