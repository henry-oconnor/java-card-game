package texasholdem;

/*
 * Henry O'Connor
 */
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
 * 
 * @author Henry O'Connor
 */
public class Chip {

    public static enum Color {
        WHITE(10), RED(50), BLUE(100), GREEN(200), BLACK(500);
        private final int ID;

        private Color(int id) {
            this.ID = id;
        }

        // hashmap storing int values and corresponding colors, allows
        // lookup of colors with an int input
        private static final Map<Integer, Color> lookup
                = new HashMap<Integer, Color>();

        // initialize hashmap with values and colors
        static {
            for (Color c : EnumSet.allOf(Color.class)) {
                lookup.put(c.ID, c);
            }
        }

        // takes a color, returns its value
        public int getValue() {
            return ID;
        }

        // takes an int, returns the corresponding color
        public static Color getColor(int intValue) {
            return lookup.get(intValue);
        }
    }

    public Color color;

    public Chip(int n) {
        this.color = Color.getColor(n);
    }

    public Chip(Color c) {
        this.color = c;
    }

    public String toString() {
        return color.toString() + " chip: $" + color.getValue();
    }
}
