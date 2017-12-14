/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
 * 
 * @author jiach
 */
public class SettingIconAnimation extends Group{
    private Cube cube;
    private Timeline  cardAnimation;
    private int size=0;
    private Point3D rotateAxis;
    public SettingIconAnimation()
    {
        cube=new Cube(15);
        cube.setAllFaceColor(Color.RED, Color.BISQUE, Color.BLANCHEDALMOND, Color.GOLD, Color.GREEN, Color.GREY);
        rotateAxis = new Point3D(1,1,1);
        cardAnimation=new Timeline();
        
        getChildren().add(cube);
        
        spinningCube();
        
        cardAnimation.setCycleCount(Timeline.INDEFINITE);
        cardAnimation.play();
        
        cube.setOnMouseMoved(e-> handleMoved());
        cube.setOnMouseExited(e-> handleExited());
        cube.reSize(10);
    }
    
    public Cube getCube(){
        return cube;
    }
    
    private void handleMoved()
    {
        cube.reSize(15);
    }
    
    private void handleExited()
    {
        cube.reSize(10);
    }
    
    public void spinningCube()
    {
        cardAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(cube.rotationAxisProperty(), new Point3D(1,1,1)), new KeyValue(cube.rotateProperty(), 0d)),
                new KeyFrame(Duration.seconds(05), new KeyValue(cube.rotationAxisProperty(), new Point3D(1,1,1)), new KeyValue(cube.rotateProperty(), -360d))
        );
    }
    
    public void sizeAnimation()
    {
        cube.setSize(size);
        cube.reSize();
    }
}
