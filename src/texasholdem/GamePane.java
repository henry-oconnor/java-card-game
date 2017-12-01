package texasholdem;


import java.util.Stack;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiach
 */
public class GamePane extends Pane{
    private Label name[];
    private Image tableImage;
    private ImageView tableView;
    private HBox holeCards[],communityCards;
    private VBox blackChip[][],greenChip[][],blueChip[][],redChip[][],whiteChip[][];
    private Button fold,call,raise,check;
    public GamePane()
    {
        blackChip=new VBox[4][5];
        greenChip=new VBox[4][5];
        blueChip=new VBox[4][5];
        redChip=new VBox[4][5];
        whiteChip=new VBox[4][5];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<5;j++)
            {
                blackChip[i][j]=new VBox();
                greenChip[i][j]=new VBox();
                blueChip[i][j]=new VBox();
                redChip[i][j]=new VBox();
                whiteChip[i][j]=new VBox();
            }
        }
        
        tableImage=new Image("Image/aa.png");
        tableView=new ImageView(tableImage);
        tableView.setFitHeight(800);
        tableView.setFitWidth(1200);
        getChildren().addAll(tableView);
        
        name=new Label[4];
        name[0]=new Label("Jiachao");
        name[1]=new Label("Moses");
        name[2]=new Label("Bheres");
        name[3]=new Label("Honery");
        
        holeCards=new HBox[4];
        holeCards[0]=new HBox();
        holeCards[1]=new HBox();
        holeCards[2]=new HBox();
        holeCards[3]=new HBox();
        
        communityCards=new HBox();
        fold=new Button("Fold");
        call=new Button("Call");
        raise=new Button("Raise");
        check=new Button("Check");
        getChildren().addAll(name[0],name[1],name[2],name[3]);
        getChildren().addAll(holeCards[0],holeCards[1],holeCards[2],holeCards[3]);
        getChildren().addAll(fold,call,raise,check,communityCards);
        
        for(int i=0;i<4;i++)
        {
            getChildren().addAll(blackChip[i][0],blackChip[i][1],blackChip[i][2],blackChip[i][3]);
            getChildren().addAll(greenChip[i][0],greenChip[i][1],greenChip[i][2],greenChip[i][3]);
            getChildren().addAll(blueChip[i][0],blueChip[i][1],blueChip[i][2],blueChip[i][3]);
            getChildren().addAll(redChip[i][0],redChip[i][1],redChip[i][2],redChip[i][3]);
            getChildren().addAll(whiteChip[i][0],whiteChip[i][1],whiteChip[i][2],whiteChip[i][3]);
        }
        setTo1200x800();
    }
    
    
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
     * @param player
     * @param numDelete 
     */
    public void deleteBlackChip(int player,int numDelete)
    {
        for(int i=4;i>=0;i--)
        {
            numDelete-=blackChip[player][i].getChildren().size();
            if(numDelete>=0)
                blackChip[player][i].getChildren().clear();
            else if(numDelete<0)
            {
                blackChip[player][i].getChildren().remove((numDelete-1)*-1, blackChip[player][i].getChildren().size()-1);
                return;
            }
        }
    }
    
    /**
     * delete number of green chips
     * @param player
     * @param numDelete 
     */
    public void deleteGreenChip(int player,int numDelete)
    {
        for(int i=4;i>=0;i--)
        {
            numDelete-=greenChip[player][i].getChildren().size();
            if(numDelete>=0)
                greenChip[player][i].getChildren().clear();
            else if(numDelete<0)
            {
                greenChip[player][i].getChildren().remove((numDelete-1)*-1, greenChip[player][i].getChildren().size()-1);
                return;
            }
        }
    }
    
    /**
     * delete number of blue chips
     * @param player
     * @param numDelete 
     */
    public void deleteBlueChip(int player,int numDelete)
    {
        for(int i=4;i>=0;i--)
        {
            numDelete-=blueChip[player][i].getChildren().size();
            if(numDelete>=0)
                blueChip[player][i].getChildren().clear();
            else if(numDelete<0)
            {
                blueChip[player][i].getChildren().remove((numDelete-1)*-1, blueChip[player][i].getChildren().size()-1);
                return;
            }
        }
    }
    
    /**
     * delete number of red chips
     * @param player
     * @param numDelete 
     */
    public void deleteRedChip(int player,int numDelete)
    {
        for(int i=4;i>=0;i--)
        {
            numDelete-=redChip[player][i].getChildren().size();
            if(numDelete>=0)
                redChip[player][i].getChildren().clear();
            else if(numDelete<0)
            {
                redChip[player][i].getChildren().remove((numDelete-1)*-1, redChip[player][i].getChildren().size()-1);
                return;
            }
        }
    }
    
    /**
     * delete number of white chips
     * @param player
     * @param numDelete 
     */
    public void deleteWhiteChip(int player,int numDelete)
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
    }
    
    public void addChips(int player,int num)
    {
        int black,green,blue,red;
        black=num/100; // cal number of black chips need to add
        num-=black*100; // minus total of black chips
        green=num/25; // cal number of green chips need to add
        num-=green*25; // minus total of green chips
        blue=num/10; // cal number of blue chips need to add
        num-=blue*10; // minus total of blue chips
        red=num/5; // cal number of red chips need to add
        num-=red*5; // minus total of red chips
        addBlackChips(player,black);
        addGreenChips(player,green);
        addBlueChips(player,blue);
        addRedChips(player,red);
        addWhiteChips(player,num);
    }
    
    /**
     * pass in how many number of black chip player have
     * add number of black chip to the pane
     * 80 max black chip can display on pane 
     * @param num 
     */
    public void addBlackChips(int player,int num)
    {
        int temp=0,i=0;
        ChipImage chip[]=new ChipImage[num];
        temp=blackChip[player][0].getChildren().size()+blackChip[player][1].getChildren().size()+blackChip[player][2].getChildren().size()+blackChip[player][3].getChildren().size(); // get total number of chips
        for(int j=0;j<num;j++)
        {
            chip[j]=new ChipImage(500);
            temp++;
            if(temp>80) // if max 40 chips
                i=4;
            else if(temp>60) // if blackChip[3] is full
                i=3;
            else if(temp>40) // if blackChip[2] is full
                i=2;
            else if(temp>20) // if blackChip[1] is full
                i=1;
            else // if blackChip[0] is full
                i=0;
            blackChip[player][i].getChildren().add(chip[j].getImageView());
        }   
        System.out.println(i);
    }
    
    /**
     * pass in how many number of green chip player have
     * add number of green chip to the pane
     * 80 max green chip can display on pane 
     * @param num 
     */
    public void addGreenChips(int player,int num)
    {
        int temp=0,i=0;
        ChipImage chip[]=new ChipImage[num];
        temp=greenChip[player][0].getChildren().size()+greenChip[player][1].getChildren().size()+greenChip[player][2].getChildren().size()+greenChip[player][3].getChildren().size(); // get total number of chips
        for(int j=0;j<num;j++)
        {
            chip[j]=new ChipImage(200);
            temp++;
            if(temp>80) // if max 40 chips
                i=4;
            else if(temp>60) // if blackChip[3] is full
                i=3;
            else if(temp>40) // if blackChip[2] is full
                i=2;
            else if(temp>20) // if blackChip[1] is full
                i=1;
            else // if blackChip[0] is full
                i=0;
            greenChip[player][i].getChildren().add(chip[j].getImageView());
        }   
    }
    
    /**
     * pass in how many number of blue chip player have
     * add number of blue chip to the pane
     * 80 max blue chip can display on pane 
     * @param num 
     */
    public void addBlueChips(int player,int num)
    {
        int temp=0,i=0;
        ChipImage chip[]=new ChipImage[num];
        temp=blueChip[player][0].getChildren().size()+blueChip[player][1].getChildren().size()+blueChip[player][2].getChildren().size()+blueChip[player][3].getChildren().size(); // get total number of chips
        for(int j=0;j<num;j++)
        {
            chip[j]=new ChipImage(100);
            temp++;
            if(temp>80) // if max 40 chips
                i=4;
            else if(temp>60) // if blackChip[3] is full
                i=3;
            else if(temp>40) // if blackChip[2] is full
                i=2;
            else if(temp>20) // if blackChip[1] is full
                i=1;
            else // if blackChip[0] is full
                i=0;
            blueChip[player][i].getChildren().add(chip[j].getImageView());
        }   
    }
    
    /**
     * pass in how many number of red chip player have
     * add number of red chip to the pane
     * 80 max red chip can display on pane 
     * @param num 
     */
    public void addRedChips(int player,int num)
    {
        int temp=0,i=0;
        ChipImage chip[]=new ChipImage[num];
        temp=redChip[player][0].getChildren().size()+redChip[player][1].getChildren().size()+redChip[player][2].getChildren().size()+redChip[player][3].getChildren().size(); // get total number of chips
        for(int j=0;j<num;j++)
        {
            chip[j]=new ChipImage(50);
            temp++;
            if(temp>80) // if max 40 chips
                i=4;
            else if(temp>60) // if blackChip[3] is full
                i=3;
            else if(temp>40) // if blackChip[2] is full
                i=2;
            else if(temp>20) // if blackChip[1] is full
                i=1;
            else // if blackChip[0] is full
                i=0;
            redChip[player][i].getChildren().add(chip[j].getImageView());
        }   
    }
    
    /**
     * pass in how many number of white chip player have
     * add number of white chip to the pane
     * 80 max white chip can display on pane 
     * @param num 
     */
    public void addWhiteChips(int player,int num)
    {
        int temp=0,i=0;
        ChipImage chip[]=new ChipImage[num];
        temp=whiteChip[player][0].getChildren().size()+whiteChip[player][1].getChildren().size()+whiteChip[player][2].getChildren().size()+whiteChip[player][3].getChildren().size(); // get total number of chips
        for(int j=0;j<num;j++)
        {
            chip[j]=new ChipImage(10);
            temp++;
            if(temp>80) // if max 40 chips
                i=4;
            else if(temp>60) // if blackChip[3] is full
                i=3;
            else if(temp>40) // if blackChip[2] is full
                i=2;
            else if(temp>20) // if blackChip[1] is full
                i=1;
            else // if blackChip[0] is full
                i=0;
            whiteChip[player][i].getChildren().add(chip[j].getImageView());
        }   
    }
    
    //gettter
    public HBox getCommunityCards() { return communityCards;}
    public Button getFoldBtm() { return fold;}
    public Button getCallBtm() { return call;}
    public Button getRaiseBtm() { return raise;}
    public Button getCheckBtm() { return check;}
    public HBox getSelfHoleCards() { return holeCards[0];}
    public HBox getLeftHoleCards() { return holeCards[1];}
    public HBox getTopHoleCards() { return holeCards[2];}
    public HBox getRightHoleCards() { return holeCards[3];}
    
    public void setTo1200x800()
    {
        fold.setMinSize(80, 24);
        fold.setMaxSize(80,24);
        fold.setLayoutX(450);
        fold.setLayoutY(655);
        
        call.setMinSize(80, 24);
        call.setMaxSize(80,24);
        call.setLayoutX(530);
        call.setLayoutY(655);
        
        raise.setMinSize(80, 24);
        raise.setMaxSize(80,24);
        raise.setLayoutX(610);
        raise.setLayoutY(655);
        
        check.setMinSize(80, 24);
        check.setMaxSize(80,24);
        check.setLayoutX(690);
        check.setLayoutY(655);
        
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
        name[0].setFont(Font.font("",FontWeight.LIGHT,15));
        
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
        
        int selfChipX=0; // self Chip layout X at the first set
        int selfChipY=550; // self Chip layout Y at the first set
        int leftChipX=0;
        int leftChipY=520;
        int topChipX=0;
        int topChipY=230;
        int rightChipX=0;
        int rightChipY=270;
        for(int i=0;i<4;i++)
        {
            //--------------self Chip layout---------------
            selfChipX=660;
            blackChip[0][i].setLayoutX(selfChipX);
            blackChip[0][i].setLayoutY(selfChipY);
            blackChip[0][i].setSpacing(-16);
            
            selfChipX+=15;
            greenChip[0][i].setLayoutX(selfChipX);
            greenChip[0][i].setLayoutY(selfChipY);
            greenChip[0][i].setSpacing(-16);
            
            selfChipX+=15;
            blueChip[0][i].setLayoutX(selfChipX);
            blueChip[0][i].setLayoutY(selfChipY);
            blueChip[0][i].setSpacing(-16);
            
            selfChipX+=15;
            redChip[0][i].setLayoutX(selfChipX);
            redChip[0][i].setLayoutY(selfChipY);
            redChip[0][i].setSpacing(-16);
            
            selfChipX+=15;
            whiteChip[0][i].setLayoutX(selfChipX);
            whiteChip[0][i].setLayoutY(selfChipY);
            whiteChip[0][i].setSpacing(-16);
            selfChipY+=15;
            
            //--------------left Chip layout---------------
            leftChipX=220;
            blackChip[1][i].setLayoutX(leftChipX);
            blackChip[1][i].setLayoutY(leftChipY);
            blackChip[1][i].setSpacing(-16);
            
            leftChipX+=15;
            greenChip[1][i].setLayoutX(leftChipX);
            greenChip[1][i].setLayoutY(leftChipY);
            greenChip[1][i].setSpacing(-16);
            
            leftChipX+=15;
            blueChip[1][i].setLayoutX(leftChipX);
            blueChip[1][i].setLayoutY(leftChipY);
            blueChip[1][i].setSpacing(-16);
            
            leftChipX+=15;
            redChip[1][i].setLayoutX(leftChipX);
            redChip[1][i].setLayoutY(leftChipY);
            redChip[1][i].setSpacing(-16);
            
            leftChipX+=15;
            whiteChip[1][i].setLayoutX(leftChipX);
            whiteChip[1][i].setLayoutY(leftChipY);
            whiteChip[1][i].setSpacing(-16);
            leftChipY+=15;
            
            //--------------top Chip layout---------------
            topChipX=440;
            blackChip[2][i].setLayoutX(topChipX);
            blackChip[2][i].setLayoutY(topChipY);
            blackChip[2][i].setSpacing(-16);
            
            topChipX+=15;
            greenChip[2][i].setLayoutX(topChipX);
            greenChip[2][i].setLayoutY(topChipY);
            greenChip[2][i].setSpacing(-16);
            
            topChipX+=15;
            blueChip[2][i].setLayoutX(topChipX);
            blueChip[2][i].setLayoutY(topChipY);
            blueChip[2][i].setSpacing(-16);
            
            topChipX+=15;
            redChip[2][i].setLayoutX(topChipX);
            redChip[2][i].setLayoutY(topChipY);
            redChip[2][i].setSpacing(-16);
            
            topChipX+=15;
            whiteChip[2][i].setLayoutX(topChipX);
            whiteChip[2][i].setLayoutY(topChipY);
            whiteChip[2][i].setSpacing(-16);
            topChipY+=15;
            
            //--------------right Chip layout---------------
            rightChipX=875;
            blackChip[3][i].setLayoutX(rightChipX);
            blackChip[3][i].setLayoutY(rightChipY);
            blackChip[3][i].setSpacing(-16);
            
            rightChipX+=15;
            greenChip[3][i].setLayoutX(rightChipX);
            greenChip[3][i].setLayoutY(rightChipY);
            greenChip[3][i].setSpacing(-16);
            
            rightChipX+=15;
            blueChip[3][i].setLayoutX(rightChipX);
            blueChip[3][i].setLayoutY(rightChipY);
            blueChip[3][i].setSpacing(-16);
            
            rightChipX+=15;
            redChip[3][i].setLayoutX(rightChipX);
            redChip[3][i].setLayoutY(rightChipY);
            redChip[3][i].setSpacing(-16);
            
            rightChipX+=15;
            whiteChip[3][i].setLayoutX(rightChipX);
            whiteChip[3][i].setLayoutY(rightChipY);
            whiteChip[3][i].setSpacing(-16);
            rightChipY+=15;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
/*
private Label name,name1,name2,name3;
    private Image icon,icon1,icon2,icon3,settingImage;
    private ImageView iconView,iconView1,iconView2,iconView3,settingImageView;
    private HBox holeCards,holeCards2;
    private VBox holeCards1,holeCards3;
    private HBox communityCards;
    private Button fold,call,raise,check,setting;
    
    public GamePane()
    {
        name=new Label("jiachao");
        name1=new Label("Moses");
        name2=new Label("Henry");
        name3=new Label("Bheres");
        
        icon=new Image("Image/icon.png");
        icon1=new Image("Image/icon.png");
        icon2=new Image("Image/icon.png");
        icon3=new Image("Image/icon.png");
        settingImage=new Image("Image/gear.png");
        
        iconView=new ImageView(icon);
        iconView1=new ImageView(icon1);
        iconView2=new ImageView(icon2);
        iconView3=new ImageView(icon3);
        settingImageView=new ImageView(settingImage);
        
        holeCards=new HBox();
        holeCards1=new VBox();
        holeCards2=new HBox();
        holeCards3=new VBox();
        
        communityCards=new HBox();
        fold=new Button("Fold");
        call=new Button("Call");
        raise=new Button("Raise");
        check=new Button("Check");
        setting=new Button();
        getChildren().addAll(fold,call,raise,check,communityCards,setting);
        getChildren().addAll(name,iconView,holeCards);
        getChildren().addAll(name1,iconView1,holeCards1,name2,iconView2,holeCards2,name3,iconView3,holeCards3);
        setting.setGraphic(settingImageView);
        
        setTo1200x800();
    }
    
    //gettter
    public HBox getHoleCards() { return holeCards;}
    public VBox getHoleCards1() { return holeCards1;}
    public HBox getHoleCards2() { return holeCards2;}
    public VBox getHoleCards3() { return holeCards3;}
    public HBox getCommunityCards() { return communityCards;}
    public Label getNameLabel() { return name;}
    public Image getIconImage() { return icon;}
    public ImageView getImageView() { return iconView;}
    public Button getFoldBtm() { return fold;}
    public Button getCallBtm() { return call;}
    public Button getRaiseBtm() { return raise;}
    public Button getCheckBtm() { return check;}
    public Button getSettingBtm() { return setting;}
    
    public void setTo1200x800()
    {
        fold.setMinSize(80, 24);
        fold.setMaxSize(80,24);
        fold.setLayoutX(650);
        fold.setLayoutY(600);
        
        call.setMinSize(80, 24);
        call.setMaxSize(80,24);
        call.setLayoutX(650);
        call.setLayoutY(625);
        
        raise.setMinSize(80, 24);
        raise.setMaxSize(80,24);
        raise.setLayoutX(650);
        raise.setLayoutY(650);
        
        check.setMinSize(80, 24);
        check.setMaxSize(80,24);
        check.setLayoutX(650);
        check.setLayoutY(675);
        
        communityCards.setSpacing(7);
        communityCards.setLayoutX(355);
        communityCards.setLayoutY(351);
        
        setting.setMaxSize(25, 25);
        setting.setMinSize(20, 20);
        settingImageView.setFitHeight(25);
        settingImageView.setFitWidth(25);
        setting.setLayoutX(1160);
        setting.setLayoutY(0);
        
        // ------------------------------------------self-----------------------------------------------
        name.setLayoutX(300);
        name.setLayoutY(600);
        name.setMaxSize(100, 30);
        name.setMinSize(100, 30);
        
        iconView.setLayoutX(300);
        iconView.setLayoutY(650);
        iconView.setFitHeight(100);
        iconView.setFitWidth(100);
        
        holeCards.setSpacing(7);
        holeCards.setLayoutX(410);
        holeCards.setLayoutY(600);
        
        //----------------------------------------------left player-------------------------------------------
        name1.setLayoutX(25);
        name1.setLayoutY(302);
        name1.setMaxSize(100, 30);
        name1.setMinSize(100, 30);
        
        iconView1.setLayoutX(25);
        iconView1.setLayoutY(352);
        iconView1.setFitHeight(100);
        iconView1.setFitWidth(100);
        
        holeCards1.setSpacing(7);
        holeCards1.setLayoutX(135);
        holeCards1.setLayoutY(302);
        //----------------------------------------------top player-------------------------------------------
        name2.setLayoutX(300);
        name2.setLayoutY(25);
        name2.setMaxSize(100, 30);
        name2.setMinSize(100, 30);
        
        iconView2.setLayoutX(300);
        iconView2.setLayoutY(75);
        iconView2.setFitHeight(100);
        iconView2.setFitWidth(100);
        
        holeCards2.setSpacing(7);
        holeCards2.setLayoutX(410);
        holeCards2.setLayoutY(25);
        //----------------------------------------------right player-------------------------------------------
        name3.setLayoutX(900);
        name3.setLayoutY(302);
        name3.setMaxSize(100, 30);
        name3.setMinSize(100, 30);
        
        iconView3.setLayoutX(900);
        iconView3.setLayoutY(352);
        iconView3.setFitHeight(100);
        iconView3.setFitWidth(100);
        
        holeCards3.setSpacing(7);
        holeCards3.setLayoutX(820);
        holeCards3.setLayoutY(302);
    }
*/