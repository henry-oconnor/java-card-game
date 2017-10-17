/*
 * Henry O'Connor
 */
package groupprojectcardgame;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author henoc
 */
    public enum ChipColor{
        WHITE(1), RED(5), BLUE(10), GREEN(25), BLACK(100);
        private final int ID;
        
        private ChipColor(int id) {
          this.ID = id;
        }

        // hashmap storing int values and corresponding colors, allows
        // lookup of colors with an int input
        private static final Map<Integer, ChipColor> lookup
            = new HashMap<Integer, ChipColor >();
        
        // initialize hashmap with values and colors
        static{
        for (ChipColor c : EnumSet.allOf(ChipColor.class))
            lookup.put(c.ID, c);
        }
        
        // takes a color, returns its value
        public int getValue(){
            return ID;
        }
        
        // takes an int, returns the corresponding color
        public static ChipColor getColor(int intValue) {
            return lookup.get(intValue);
        }
    }