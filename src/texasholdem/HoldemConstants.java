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

    public static final int FIRST_CARD = 0;
    public static final int SECOND_CARD = 1;
    public static final int CARDS_IN_FLOP = 3;
    public static final int CARDS_IN_TURN = 1;
    public static final int CARDS_IN_RIVER = 1;

    // Server/client flags
    public static final int SEND_REDUCE_USER_BANK = 10;
    public static final int SENDING_CARDS = 11;
    public static final int COLLECTING_WAGERS = 12;
    public static final int DEALING_FLOP = 13;
    public static final int DEALING_TURN = 14;
    public static final int DEALING_RIVER = 15;
    public static final int AWARDING_WINNINGS = 16;
    public static final int WINNING_PLAYER = 17;
    public static final int LOSING_PLAYER = 18;
    public static final int RESETTING_GAME = 19;
    public static final int BEGINNING_GAME = 20;
    public static final int DISPLAY_GAMEBOARD = 21;
    public static final int END_GAME = 22;
    public static final int DETERMINE_BEST_HAND = 22;

    public static final boolean OPEN_CONNECTION = true;

    public static final int REQUEST_LOGIN = 0;
    public static final int REQUEST_REGISTER = 1;

    public static final String SERVER_NAME = "localhost";
    public static final int PORT_NUMBER = 8000;

}
