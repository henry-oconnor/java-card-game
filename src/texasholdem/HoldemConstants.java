/*
 * Henry O'Connor
 */
package texasholdem;

/**
 *
 * @author henoc
 */
public interface HoldemConstants {
    public static final int PLAYER1 = 0;
    public static final int PLAYER2 = 1;
    public static final int PLAYER3 = 2;
    public static final int PLAYER4 = 3;
    
    public static final int CONTINUE = 0;
    public static final int PLAYER1_WON = 1;
    public static final int PLAYER2_WON = 2;
    public static final int PLAYER3_WON = 3;
    public static final int PLAYER4_WON = 4;
    
    public static final int FOLD = 0;
    public static final int CHECK = 1;
    public static final int CALL = 2;
    public static final int RAISE = 3;
    public static final int ALL_IN = 4;
    
    public static final int MINIMUM_ANTE = 100;
    public static final int RAISE_AMOUNT = 100;
    public static final int CALL_AMOUNT = 100;
    public static final int NUM_SHUFFLES = 20;
    public static final int HAND_SIZE = 2;
    
    // Server/client flags
    public static final int SEND_REDUCE_USER_BANK = 10;
    
    
    public static final String SERVER_NAME = "localhost";
    public static final int PORT_NUMBER = 8000;

}

