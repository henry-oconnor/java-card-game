
import java.awt.Font;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
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
}
