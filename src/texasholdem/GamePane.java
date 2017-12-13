package texasholdem;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiach
 *
 * SIGNIFICANT CHANGES: Removing the functionality of wagering/folding. Rather,
 * there will be a single button — Continue. When all players have pressed the
 * button, the game will move to the next step (flop, river, turn, winner,
 * reset).
 */
public class GamePane extends Pane {

    private Label name[];
    private Image tableImage;
    private ImageView tableView;
    private HBox holeCards[], communityCards, raiseChipBox;
    private VBox blackChip[][], greenChip[][], blueChip[][], redChip[][], whiteChip[][];
    //private Button btnFold, btnCall, btnRaise, btnCheck, btnRaiseChip[];
    private Button btnContinue;
    private SettingIconAnimation settingIcon;

    //public BooleanProperty buttonPressed;
    public IntegerProperty changeInt;

    private int buttonID;
    private final ArrayList<HoldemPlayer> players = new ArrayList<HoldemPlayer>() {
        {
            add(new HoldemPlayer());
        }
    };
    private GameBoard gameBoard = new GameBoard(players);

    public GamePane() {
        changeInt = new SimpleIntegerProperty(0);
        blackChip = new VBox[5][5];
        greenChip = new VBox[5][5];
        blueChip = new VBox[5][5];
        redChip = new VBox[5][5];
        whiteChip = new VBox[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                blackChip[i][j] = new VBox();
                greenChip[i][j] = new VBox();
                blueChip[i][j] = new VBox();
                redChip[i][j] = new VBox();
                whiteChip[i][j] = new VBox();
            }
        }

        settingIcon = new SettingIconAnimation();
//        btnRaiseChip=new Button[5];
//        btnRaiseChip[0]=new Button();
//        btnRaiseChip[1]=new Button();
//        btnRaiseChip[2]=new Button();
//        btnRaiseChip[3]=new Button();
//        btnRaiseChip[4]=new Button();
//        raiseChipBox=new HBox();
//        raiseChipBox.getChildren().addAll(btnRaiseChip[0],btnRaiseChip[1],btnRaiseChip[2],btnRaiseChip[3],btnRaiseChip[4]);

        tableImage = new Image("Image/aa.png");
        tableView = new ImageView(tableImage);
        tableView.setFitHeight(800);
        tableView.setFitWidth(1200);
        getChildren().addAll(tableView);

        /*
        
        get usernames from database here
        
         */
        name = new Label[4];
        name[0] = new Label("Jiachao");
        name[1] = new Label("Moses");
        name[2] = new Label("Bheres");
        name[3] = new Label("Honery");

        holeCards = new HBox[4];
        holeCards[0] = new HBox();
        holeCards[1] = new HBox();
        holeCards[2] = new HBox();
        holeCards[3] = new HBox();

        communityCards = new HBox();
        btnContinue = new Button("Continue");
//        btnFold = new Button("Fold");
//        btnCall = new Button("Call");
//        btnRaise = new Button("Raise");
//        btnCheck = new Button("Check");

        getChildren().addAll(name[0], name[1], name[2], name[3]);
        getChildren().addAll(holeCards[0], holeCards[1], holeCards[2], holeCards[3]);
        getChildren().add(btnContinue);
        getChildren().add(communityCards);
//        getChildren().addAll(btnFold, btnCall, btnRaise, btnCheck, communityCards);
        getChildren().add(settingIcon);

        for (int i = 0; i < 5; i++) {
            getChildren().addAll(blackChip[i][0], blackChip[i][1], blackChip[i][2], blackChip[i][3], blackChip[i][4]);
            getChildren().addAll(greenChip[i][0], greenChip[i][1], greenChip[i][2], greenChip[i][3], greenChip[i][4]);
            getChildren().addAll(blueChip[i][0], blueChip[i][1], blueChip[i][2], blueChip[i][3], blueChip[i][4]);
            getChildren().addAll(redChip[i][0], redChip[i][1], redChip[i][2], redChip[i][3], redChip[i][4]);
            getChildren().addAll(whiteChip[i][0], whiteChip[i][1], whiteChip[i][2], whiteChip[i][3], whiteChip[i][4]);
        }
        setTo1200x800();

        //btnRaise.setOnMouseClicked(e-> {setRaiseChipBtn(true);});
        btnContinue.setOnAction(e -> {
            if (changeInt.getValue() == 0) {
                changeInt.set(1);
            } else {
                changeInt.set(0);
            }
        });
//        btnCall.setOnAction(e -> {
//            this.buttonID = HoldemConstants.CALL;
//        });
//        btnRaise.setOnAction(e -> {
//            this.buttonID = HoldemConstants.RAISE;
//        });
//        btnCheck.setOnAction(e -> {
//            this.buttonID = HoldemConstants.CHECK;
//
//        });

    }

//    public void setRaiseChipBtn(boolean value)
//      {
//          btnRaiseChip[0].setVisible(value);
//          btnRaiseChip[1].setVisible(value);
//          btnRaiseChip[2].setVisible(value);
//          btnRaiseChip[3].setVisible(value);
//          btnRaiseChip[4].setVisible(value);
//      }

    /* public void deleteChips(int player,int numDelete)
    {  
        for(int i=4;i>=0;i--)
        {
            numDelete-=whiteChip[player][i].getChildren().size();
            if(numDelete>=0)
                whiteChip[player][i].getChildren().clear();
            else if(numDelete<0)
            {
                whiteChip[player][i].getChildren().remove((numDelete-1)*-1, whiteChip[player][i].getChildren().size()-1);
                return;
            }
        }
    }*/
    /**
     * delete number of black chips
     *
     * @param player
     * @param numDelete
     */
    public void deleteBlackChip(int player, int numDelete) {
        for (int i = 4; i >= 0; i--) {
            numDelete -= blackChip[player][i].getChildren().size();
            if (numDelete >= 0) {
                blackChip[player][i].getChildren().clear();
            } else if (numDelete < 0) {
                blackChip[player][i].getChildren().remove((numDelete - 1) * -1, blackChip[player][i].getChildren().size() - 1);
                return;
            }
        }
    }

    /**
     * delete number of green chips
     *
     * @param player
     * @param numDelete
     */
    public void deleteGreenChip(int player, int numDelete) {
        for (int i = 4; i >= 0; i--) {
            numDelete -= greenChip[player][i].getChildren().size();
            if (numDelete >= 0) {
                greenChip[player][i].getChildren().clear();
            } else if (numDelete < 0) {
                greenChip[player][i].getChildren().remove((numDelete - 1) * -1, greenChip[player][i].getChildren().size() - 1);
                return;
            }
        }
    }

    /**
     * delete number of blue chips
     *
     * @param player
     * @param numDelete
     */
    public void deleteBlueChip(int player, int numDelete) {
        for (int i = 4; i >= 0; i--) {
            numDelete -= blueChip[player][i].getChildren().size();
            if (numDelete >= 0) {
                blueChip[player][i].getChildren().clear();
            } else if (numDelete < 0) {
                blueChip[player][i].getChildren().remove((numDelete - 1) * -1, blueChip[player][i].getChildren().size() - 1);
                return;
            }
        }
    }

    /**
     * delete number of red chips
     *
     * @param player
     * @param numDelete
     */
    public void deleteRedChip(int player, int numDelete) {
        for (int i = 4; i >= 0; i--) {
            numDelete -= redChip[player][i].getChildren().size();
            if (numDelete >= 0) {
                redChip[player][i].getChildren().clear();
            } else if (numDelete < 0) {
                redChip[player][i].getChildren().remove((numDelete - 1) * -1, redChip[player][i].getChildren().size() - 1);
                return;
            }
        }
    }

    /**
     * delete number of white chips
     *
     * @param player
     * @param numDelete
     */
    public void deleteWhiteChip(int player, int numDelete) {
        for (int i = 4; i >= 0; i--) {
            numDelete -= whiteChip[player][i].getChildren().size();
            if (numDelete >= 0) {
                whiteChip[player][i].getChildren().clear();
            } else if (numDelete < 0) {
                whiteChip[player][i].getChildren().remove((numDelete - 1) * -1, whiteChip[player][i].getChildren().size() - 1);
                return;
            }
        }
    }

    // probly shouldn't use this function at all
    public void addChips(int player, int num) {
        int black, green, blue, red;
        black = num / 100; // cal number of black chips need to add
        num -= black * 100; // minus total of black chips
        green = num / 25; // cal number of green chips need to add
        num -= green * 25; // minus total of green chips
        blue = num / 10; // cal number of blue chips need to add
        num -= blue * 10; // minus total of blue chips
        red = num / 5; // cal number of red chips need to add
        num -= red * 5; // minus total of red chips
        addBlackChips(player, black);
        addGreenChips(player, green);
        addBlueChips(player, blue);
        addRedChips(player, red);
        addWhiteChips(player, num);
    }

    /**
     * pass in how many number of black chip player have add number of black
     * chip to the pane 80 max black chip can display on pane
     *
     * @param num
     */
    public void addBlackChips(int player, int num) {
        int temp = 0, i = 0;
        ChipImage chip[] = new ChipImage[num];
        temp = blackChip[player][0].getChildren().size() + blackChip[player][1].getChildren().size() + blackChip[player][2].getChildren().size() + blackChip[player][3].getChildren().size(); // get total number of chips
        for (int j = 0; j < num; j++) {
            chip[j] = new ChipImage(500);
            temp++;
            if (temp > 80) // if max 40 chips
            {
                i = 4;
            } else if (temp > 60) // if blackChip[3] is full
            {
                i = 3;
            } else if (temp > 40) // if blackChip[2] is full
            {
                i = 2;
            } else if (temp > 20) // if blackChip[1] is full
            {
                i = 1;
            } else // if blackChip[0] is full
            {
                i = 0;
            }
            blackChip[player][i].getChildren().add(chip[j].getImageView());
        }
        System.out.println(i);
    }

    /**
     * pass in how many number of green chip player have add number of green
     * chip to the pane 80 max green chip can display on pane
     *
     * @param num
     */
    public void addGreenChips(int player, int num) {
        int temp = 0, i = 0;
        ChipImage chip[] = new ChipImage[num];
        temp = greenChip[player][0].getChildren().size() + greenChip[player][1].getChildren().size() + greenChip[player][2].getChildren().size() + greenChip[player][3].getChildren().size(); // get total number of chips
        for (int j = 0; j < num; j++) {
            chip[j] = new ChipImage(200);
            temp++;
            if (temp > 80) // if max 40 chips
            {
                i = 4;
            } else if (temp > 60) // if blackChip[3] is full
            {
                i = 3;
            } else if (temp > 40) // if blackChip[2] is full
            {
                i = 2;
            } else if (temp > 20) // if blackChip[1] is full
            {
                i = 1;
            } else // if blackChip[0] is full
            {
                i = 0;
            }
            greenChip[player][i].getChildren().add(chip[j].getImageView());
        }
    }

    /**
     * pass in how many number of blue chip player have add number of blue chip
     * to the pane 80 max blue chip can display on pane
     *
     * @param num
     */
    public void addBlueChips(int player, int num) {
        int temp = 0, i = 0;
        ChipImage chip[] = new ChipImage[num];
        temp = blueChip[player][0].getChildren().size() + blueChip[player][1].getChildren().size() + blueChip[player][2].getChildren().size() + blueChip[player][3].getChildren().size(); // get total number of chips
        for (int j = 0; j < num; j++) {
            chip[j] = new ChipImage(100);
            temp++;
            if (temp > 80) // if max 40 chips
            {
                i = 4;
            } else if (temp > 60) // if blackChip[3] is full
            {
                i = 3;
            } else if (temp > 40) // if blackChip[2] is full
            {
                i = 2;
            } else if (temp > 20) // if blackChip[1] is full
            {
                i = 1;
            } else // if blackChip[0] is full
            {
                i = 0;
            }
            blueChip[player][i].getChildren().add(chip[j].getImageView());
        }
    }

    /**
     * pass in how many number of red chip player have add number of red chip to
     * the pane 80 max red chip can display on pane
     *
     * @param num
     */
    public void addRedChips(int player, int num) {
        int temp = 0, i = 0;
        ChipImage chip[] = new ChipImage[num];
        temp = redChip[player][0].getChildren().size() + redChip[player][1].getChildren().size() + redChip[player][2].getChildren().size() + redChip[player][3].getChildren().size(); // get total number of chips
        for (int j = 0; j < num; j++) {
            chip[j] = new ChipImage(50);
            temp++;
            if (temp > 80) // if max 40 chips
            {
                i = 4;
            } else if (temp > 60) // if blackChip[3] is full
            {
                i = 3;
            } else if (temp > 40) // if blackChip[2] is full
            {
                i = 2;
            } else if (temp > 20) // if blackChip[1] is full
            {
                i = 1;
            } else // if blackChip[0] is full
            {
                i = 0;
            }
            redChip[player][i].getChildren().add(chip[j].getImageView());
        }
    }

    /**
     * pass in how many number of white chip player have add number of white
     * chip to the pane 80 max white chip can display on pane
     *
     * @param num
     */
    public void addWhiteChips(int player, int num) {
        int temp = 0, i = 0;
        ChipImage chip[] = new ChipImage[num];
        temp = whiteChip[player][0].getChildren().size() + whiteChip[player][1].getChildren().size() + whiteChip[player][2].getChildren().size() + whiteChip[player][3].getChildren().size(); // get total number of chips
        for (int j = 0; j < num; j++) {
            chip[j] = new ChipImage(10);
            temp++;
            if (temp > 80) // if max 40 chips
            {
                i = 4;
            } else if (temp > 60) // if blackChip[3] is full
            {
                i = 3;
            } else if (temp > 40) // if blackChip[2] is full
            {
                i = 2;
            } else if (temp > 20) // if blackChip[1] is full
            {
                i = 1;
            } else // if blackChip[0] is full
            {
                i = 0;
            }
            whiteChip[player][i].getChildren().add(chip[j].getImageView());
        }
    }

    //getter
    public HBox getCommunityCards() {
        return communityCards;
    }

    public HBox getSelfHoleCards() {
        return holeCards[0];
    }

    public HBox getLeftHoleCards() {
        return holeCards[1];
    }

    public HBox getTopHoleCards() {
        return holeCards[2];
    }

    public HBox getRightHoleCards() {
        return holeCards[3];
    }

    public int getButtonID() {
        return buttonID;
    }

    public FiveCardHand getCommunityCardsList() {
        return gameBoard.getCommunityCards();
    }

    public void setButtonID(int buttonID) {
        this.buttonID = buttonID;
    }

    public void setCommunityCards(HBox hBox) {
        this.communityCards = hBox;
    }

    public void addToCommunityCards(Card card) {
        gameBoard.addCardToBoard(card);
    }
    public void setName(String username, int index){
        name[index].setText(username);
    }

    public void setTo1200x800() {
//        btnFold.setMinSize(80, 24);
//        btnFold.setMaxSize(80, 24);
//        btnFold.setLayoutX(450);
//        btnFold.setLayoutY(655);
//
//        btnCall.setMinSize(80, 24);
//        btnCall.setMaxSize(80, 24);
//        btnCall.setLayoutX(530);
//        btnCall.setLayoutY(655);
//
//        btnRaise.setMinSize(80, 24);
//        btnRaise.setMaxSize(80, 24);
//        btnRaise.setLayoutX(610);
//        btnRaise.setLayoutY(655);

        btnContinue.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        btnContinue.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        btnContinue.setLayoutX(610);
        btnContinue.setLayoutY(655);

//        raiseChipBox.setLayoutX(610);
//        raiseChipBox.setLayoutY(690);
//        raiseChipBox.setSpacing(5);
//        btnRaiseChip[0].setMinSize(40, 24);
//        btnRaiseChip[1].setMinSize(40, 24);
//        btnRaiseChip[2].setMinSize(40, 24);
//        btnRaiseChip[3].setMinSize(40, 24);
//        btnRaiseChip[4].setMinSize(40, 24);
//        setRaiseChipBtn(false);
//        
//        btnCheck.setMinSize(80, 24);
//        btnCheck.setMaxSize(80, 24);
//        btnCheck.setLayoutX(690);
//        btnCheck.setLayoutY(655);
        communityCards.setSpacing(7);
        communityCards.setLayoutX(400);
        communityCards.setLayoutY(400);
        //communityCards.setLayoutX(400);
        //communityCards.setLayoutY(351);

        // ------------------------------------------self-----------------------------------------------
        name[0].setLayoutX(350);
        name[0].setLayoutY(650);
        name[0].setMaxSize(100, 30);
        name[0].setMinSize(100, 30);
        name[0].setTextFill(Color.WHITE);
        name[0].setFont(Font.font("", FontWeight.LIGHT, 15));

        holeCards[0].setSpacing(7);
        holeCards[0].setLayoutX(500);
        holeCards[0].setLayoutY(530);

        //----------------------------------------------left player-------------------------------------------
        name[1].setLayoutX(25);
        name[1].setLayoutY(302);
        name[1].setMaxSize(100, 30);
        name[1].setMinSize(100, 30);
        name[1].setTextFill(Color.WHITE);

        holeCards[1].setSpacing(7);
        holeCards[1].setLayoutX(150);
        holeCards[1].setLayoutY(365);
        holeCards[1].setRotate(90);

        //----------------------------------------------Top player-------------------------------------------
        name[2].setLayoutX(500);
        name[2].setLayoutY(50);
        name[2].setMaxSize(100, 30);
        name[2].setMinSize(100, 30);
        name[2].setTextFill(Color.WHITE);

        holeCards[2].setSpacing(7);
        holeCards[2].setLayoutX(530);
        holeCards[2].setLayoutY(210);
        // holeCards[2].setRotate(90);

        //----------------------------------------------right player-------------------------------------------
        name[3].setLayoutX(1100);
        name[3].setLayoutY(302);
        name[3].setMaxSize(100, 30);
        name[3].setMinSize(100, 30);
        name[3].setTextFill(Color.WHITE);

        holeCards[3].setSpacing(7);
        holeCards[3].setLayoutX(885);
        holeCards[3].setLayoutY(365);
        holeCards[3].setRotate(90);

        settingIcon.setLayoutX(1180);
        settingIcon.setLayoutY(20);

        int selfChipX = 0; // self Chip layout X at the first set
        int selfChipY = 550; // self Chip layout Y at the first set
        int leftChipX = 0;
        int leftChipY = 520;
        int topChipX = 0;
        int topChipY = 230;
        int rightChipX = 0;
        int rightChipY = 270;
        int betInChipX = 0;
        int betInChipY = 335;

        for (int i = 0; i < 4; i++) {
            //--------------self Chip layout---------------
            selfChipX = 660;
            blackChip[0][i].setLayoutX(selfChipX);
            blackChip[0][i].setLayoutY(selfChipY);
            blackChip[0][i].setSpacing(-16);

            selfChipX += 15;
            greenChip[0][i].setLayoutX(selfChipX);
            greenChip[0][i].setLayoutY(selfChipY);
            greenChip[0][i].setSpacing(-16);

            selfChipX += 15;
            blueChip[0][i].setLayoutX(selfChipX);
            blueChip[0][i].setLayoutY(selfChipY);
            blueChip[0][i].setSpacing(-16);

            selfChipX += 15;
            redChip[0][i].setLayoutX(selfChipX);
            redChip[0][i].setLayoutY(selfChipY);
            redChip[0][i].setSpacing(-16);

            selfChipX += 15;
            whiteChip[0][i].setLayoutX(selfChipX);
            whiteChip[0][i].setLayoutY(selfChipY);
            whiteChip[0][i].setSpacing(-16);
            selfChipY += 15;

            //--------------left Chip layout---------------
            leftChipX = 220;
            blackChip[1][i].setLayoutX(leftChipX);
            blackChip[1][i].setLayoutY(leftChipY);
            blackChip[1][i].setSpacing(-16);

            leftChipX += 15;
            greenChip[1][i].setLayoutX(leftChipX);
            greenChip[1][i].setLayoutY(leftChipY);
            greenChip[1][i].setSpacing(-16);

            leftChipX += 15;
            blueChip[1][i].setLayoutX(leftChipX);
            blueChip[1][i].setLayoutY(leftChipY);
            blueChip[1][i].setSpacing(-16);

            leftChipX += 15;
            redChip[1][i].setLayoutX(leftChipX);
            redChip[1][i].setLayoutY(leftChipY);
            redChip[1][i].setSpacing(-16);

            leftChipX += 15;
            whiteChip[1][i].setLayoutX(leftChipX);
            whiteChip[1][i].setLayoutY(leftChipY);
            whiteChip[1][i].setSpacing(-16);
            leftChipY += 15;

            //--------------top Chip layout---------------
            topChipX = 440;
            blackChip[2][i].setLayoutX(topChipX);
            blackChip[2][i].setLayoutY(topChipY);
            blackChip[2][i].setSpacing(-16);

            topChipX += 15;
            greenChip[2][i].setLayoutX(topChipX);
            greenChip[2][i].setLayoutY(topChipY);
            greenChip[2][i].setSpacing(-16);

            topChipX += 15;
            blueChip[2][i].setLayoutX(topChipX);
            blueChip[2][i].setLayoutY(topChipY);
            blueChip[2][i].setSpacing(-16);

            topChipX += 15;
            redChip[2][i].setLayoutX(topChipX);
            redChip[2][i].setLayoutY(topChipY);
            redChip[2][i].setSpacing(-16);

            topChipX += 15;
            whiteChip[2][i].setLayoutX(topChipX);
            whiteChip[2][i].setLayoutY(topChipY);
            whiteChip[2][i].setSpacing(-16);
            topChipY += 15;

            //--------------right Chip layout---------------
            rightChipX = 875;
            blackChip[3][i].setLayoutX(rightChipX);
            blackChip[3][i].setLayoutY(rightChipY);
            blackChip[3][i].setSpacing(-16);

            rightChipX += 15;
            greenChip[3][i].setLayoutX(rightChipX);
            greenChip[3][i].setLayoutY(rightChipY);
            greenChip[3][i].setSpacing(-16);

            rightChipX += 15;
            blueChip[3][i].setLayoutX(rightChipX);
            blueChip[3][i].setLayoutY(rightChipY);
            blueChip[3][i].setSpacing(-16);

            rightChipX += 15;
            redChip[3][i].setLayoutX(rightChipX);
            redChip[3][i].setLayoutY(rightChipY);
            redChip[3][i].setSpacing(-16);

            rightChipX += 15;
            whiteChip[3][i].setLayoutX(rightChipX);
            whiteChip[3][i].setLayoutY(rightChipY);
            whiteChip[3][i].setSpacing(-16);
            rightChipY += 15;

            betInChipX = 568;
            blackChip[4][i].setLayoutX(betInChipX);
            blackChip[4][i].setLayoutY(betInChipY);
            blackChip[4][i].setSpacing(-16);

            betInChipX += 15;
            greenChip[4][i].setLayoutX(betInChipX);
            greenChip[4][i].setLayoutY(betInChipY);
            greenChip[4][i].setSpacing(-16);

            betInChipX += 15;
            blueChip[4][i].setLayoutX(betInChipX);
            blueChip[4][i].setLayoutY(betInChipY);
            blueChip[4][i].setSpacing(-16);

            betInChipX += 15;
            redChip[4][i].setLayoutX(betInChipX);
            redChip[4][i].setLayoutY(betInChipY);
            redChip[4][i].setSpacing(-16);

            betInChipX += 15;
            whiteChip[4][i].setLayoutX(betInChipX);
            whiteChip[4][i].setLayoutY(betInChipY);
            whiteChip[4][i].setSpacing(-16);
            betInChipY += 15;
        }
    }
//
//    void getButtonPressed() {        
//        while(!buttonPressed);
//        buttonPressed = false;      
//    }

}
