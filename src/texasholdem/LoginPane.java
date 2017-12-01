package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 *
 * @author jiach
 */
public class LoginPane extends Group{
    private StartSceneAnimaton animation;
    private Label usernameLabel,passwordLabel,titleLabel;
    private TextField usernameText,passwordText;
    private Button loginBtm;
    private Stop[] stops;
    private LinearGradient color;
    private Timeline titleAnimation;
    private int x;
    public LoginPane()
    {
        x=1;
        System.out.println("aaaaaaaaaa");
        animation=new StartSceneAnimaton();
        titleAnimation=new Timeline();
        usernameLabel=new Label("Username: ");
        passwordLabel=new Label("Password: ");
        titleLabel=new Label("Texas hold 'em");
        usernameText=new TextField("");
        passwordText=new TextField("");
        loginBtm=new Button("Login");
        getChildren().addAll(animation,titleLabel,usernameLabel,passwordLabel,usernameText,passwordText,loginBtm);
        
        usernameLabel.setLayoutX(130);
        usernameLabel.setLayoutY(150);
        passwordLabel.setLayoutX(130);
        passwordLabel.setLayoutY(200);
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(0);
        titleLabel.setMinSize(400, 150);
        usernameLabel.setFont(Font.font("Arial",FontWeight.BOLD,13));
        usernameLabel.setTextFill(Color.WHITE);
        passwordLabel.setFont(Font.font("Arial",FontWeight.BOLD,13));
        passwordLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,40));
        stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.WHITE), new Stop(2, Color.RED)};
        color = new LinearGradient(1, 0, 0,0, true, CycleMethod.REFLECT, stops);
        titleLabel.setTextFill(color);
        
        usernameText.setLayoutX(200);
        usernameText.setLayoutY(150);
        passwordText.setLayoutX(200);
        passwordText.setLayoutY(200);
        
        loginBtm.setLayoutX(220);
        loginBtm.setLayoutY(250);
        loginBtm.setFont(Font.font("Arial",FontWeight.LIGHT,13));
        loginBtm.setTextFill(Color.BLACK);
        
        titleAnimation.getKeyFrames().addAll(new KeyFrame(Duration.seconds(2),e-> titleAnimations()));
        titleAnimation.setCycleCount(Timeline.INDEFINITE);
        titleAnimation.play();
    }
    
    public void titleAnimations()
    {
        stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.WHITE), new Stop(2, Color.RED)};
        color = new LinearGradient(x, 0, 0,0, true, CycleMethod.REFLECT, stops);
        x++;
        if(x>=8)
            titleAnimation.stop();
        titleLabel.setTextFill(color);
    }
}
