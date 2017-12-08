/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author jiach
 */
public class RegisterPane extends Pane{
    private Label userNameLabel,passwordLabel;
    private TextField userNameText,passwordText;
    private Button done;
    public RegisterPane()
    {
        userNameLabel=new Label("Username: ");
        passwordLabel=new Label("Password: ");
        userNameText=new TextField();
        passwordText=new TextField();
        done=new Button("Register");
        this.getChildren().addAll(userNameLabel,passwordLabel,userNameText,passwordText,done);
        defultPosition();
    }
    
    public String getUsername() { return userNameText.getText();}
    public String getPassword() { return passwordText.getText();}
    public Button getDoneBtm() { return done;}
    
    public void defultPosition()
    {
        userNameLabel.setLayoutX(50);
        userNameLabel.setLayoutY(50);
        
        passwordLabel.setLayoutX(50);
        passwordLabel.setLayoutY(100);
        
        userNameText.setLayoutX(120);
        userNameText.setLayoutY(45);
        
        passwordText.setLayoutX(120);
        passwordText.setLayoutY(95);
        
        done.setLayoutX(150);
        done.setLayoutY(150);
    }
}
