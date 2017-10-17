/*
 * Henry O'Connor
 */
package groupprojectcardgame;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Henry O'Connor
 */


public class Chip {
   
    public ChipColor color;
    
    public Chip(int n){
        this.color = ChipColor.getColor(n);
    }
    
    public Chip(ChipColor c){
        this.color = c;
    }
    
    public String toString(){
        return color.toString() + " chip: $" + color.getValue();
    }
}
