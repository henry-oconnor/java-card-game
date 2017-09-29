/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcardgame;

/**
 *
 * @author Bernard Heres
 *  TEMPORARY DUMMY CLASS FOR TESTING PURPOSES.
 */
class Card {
    private int cardNumber;
    
    public Card(){
        this.cardNumber = 0;
    }
    
    public Card(int cardNumber){
        this.cardNumber = cardNumber;
    }
    
    public String toString(){
        return "This is card# " + (cardNumber + 1);
    }
}
