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
import javafx.scene.layout.Pane;
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
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
 * 
 * @author jiach
 */
public class LoginPane extends Pane{
    private StartSceneAnimaton animation;
    private Label usernameLabel,passwordLabel,titleLabel;
    private TextField usernameText,passwordText;
    private Button loginBtn,registerBtn;
    private Stop[] stops;
    private LinearGradient color;
    private Timeline titleAnimation;
    private int x;
    public boolean continueFlag;
    
    public LoginPane()
    {
        continueFlag = false;
        x=1;
        animation=new StartSceneAnimaton();
        titleAnimation=new Timeline();
        usernameLabel=new Label("Username: ");
        passwordLabel=new Label("Password: ");
        titleLabel=new Label("Texas hold 'em");
        usernameText=new TextField("");
        passwordText=new TextField("");
        loginBtn=new Button("Login");
        registerBtn=new Button("Register");
        getChildren().addAll(animation,titleLabel,usernameLabel,passwordLabel,usernameText,passwordText,loginBtn,registerBtn);
        
        registerBtn.setOnAction(e -> {
            continueFlag = true;
        });
        
        
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
        
        loginBtn.setLayoutX(170);
        loginBtn.setLayoutY(250);
        loginBtn.setMinSize(80, 30);
        loginBtn.setFont(Font.font("Arial",FontWeight.LIGHT,13));
        loginBtn.setTextFill(Color.BLACK);
        
        registerBtn.setLayoutX(270);
        registerBtn.setLayoutY(250);
        
        titleAnimation.getKeyFrames().addAll(new KeyFrame(Duration.seconds(2),e-> titleAnimations()));
        titleAnimation.setCycleCount(Timeline.INDEFINITE);
        titleAnimation.play();
    }
    
    public String getUsername() { return usernameText.getText();}
    public String getPassword() { return passwordText.getText();}
    public Button getLoginBtn() { return loginBtn;}
    public Button getRegisterBtn() { return registerBtn;}
    
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
