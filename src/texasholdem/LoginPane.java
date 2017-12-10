package texasholdem;

 /* To change this template file, choose Tools | Templates
=======
/* To change this template file, choose Tools | Templates
>>>>>>> 7a175635f106f663ada04ec5c98408ca7eb26f96
 * and open the template in the editor.
 */


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import texasholdem.StartSceneAnimaton;

/**
 *
 * @author jiach
 */
public class LoginPane extends Group{
    private StartSceneAnimaton animation;
    private Label usernameLabel,passwordLabel,titleLabel;
    private TextField usernameText,passwordText;
    private Button loginBtm,registerBtm;
    private Stop[] stops;
    private LinearGradient color;
    private Timeline titleAnimation;
    private int x;
    
    public LoginPane()
    {
        x=1;
        animation=new StartSceneAnimaton();
        titleAnimation=new Timeline();
        usernameLabel=new Label("Username: ");
        passwordLabel=new Label("Password: ");
        titleLabel=new Label("Texas hold 'em");
        usernameText=new TextField("");
        passwordText=new TextField("");
        loginBtm=new Button("Login");
        registerBtm=new Button("Register");
        getChildren().addAll(animation,titleLabel,usernameLabel,passwordLabel,usernameText,passwordText,loginBtm,registerBtm);
        
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
        
        loginBtm.setLayoutX(170);
        loginBtm.setLayoutY(250);
        loginBtm.setMinSize(80, 30);
        loginBtm.setFont(Font.font("Arial",FontWeight.LIGHT,13));
        loginBtm.setTextFill(Color.BLACK);
        
        registerBtm.setLayoutX(270);
        registerBtm.setLayoutY(250);
        
        titleAnimation.getKeyFrames().addAll(new KeyFrame(Duration.seconds(2),e-> titleAnimations()));
        titleAnimation.setCycleCount(Timeline.INDEFINITE);
        titleAnimation.play();
    }
    
    public String getUsername() { return usernameText.getText();}
    public String getPassword() { return passwordText.getText();}
    public Button getLoginBtm() { return loginBtm;}
    public Button getRegisterBtm() { return registerBtm;}
    
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
