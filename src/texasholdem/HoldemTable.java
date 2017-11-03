/*
 * Henry O'Connor
 */
package texasholdem;

import java.util.ArrayList;

/**
 *
 * @author henoc
 */
public class HoldemTable {
    ArrayList<HoldemPlayer> players;
    ArrayList<Card> gameCards;
    int pool;
    DeckOfCards deck;
    Pot pot;
    
    
    void addCard(Card card){
        gameCards.add(card);
    }
    
    void deal(HoldemPlayer player){
        player.addCard(deck.dealCard());
    }
    
    
}
