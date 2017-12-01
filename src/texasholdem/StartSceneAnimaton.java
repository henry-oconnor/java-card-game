package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author jiach
 */
public class StartSceneAnimaton extends Group{
    private ImageCube cube[];
    private Timeline  cardAnimation;
    private int x=100,size=0;
    private Point3D rotateAxis;
    public StartSceneAnimaton()
    {
        cube=new ImageCube[5];
        cube[0]=new ImageCube(300);
        cube[1]=new ImageCube(300);
        cube[2]=new ImageCube(300);
        cube[3]=new ImageCube(300);
        cube[4]=new ImageCube(300);
        rotateAxis = new Point3D(1,1,1);
        cardAnimation=new Timeline();
        getChildren().addAll(cube[0],cube[1],cube[2],cube[3],cube[4]);
       
        spinningCube();
        cube[0].setLayoutX(300);
        cube[0].setLayoutY(300);
        cube[1].setLayoutX(500);
        cube[1].setLayoutY(500);
        cube[2].setLayoutX(200);
        cube[2].setLayoutY(420);
        cube[3].setLayoutX(300);
        cube[3].setLayoutY(300);
        cube[4].setLayoutX(400);
        cube[4].setLayoutY(420);
        
        cardAnimation.setCycleCount(Timeline.INDEFINITE);
        cardAnimation.play();
    }
    
    public void sizeAnimation()
    {
        size=30;
        cube[0].setSize(size);
        cube[0].reSize();
        cube[0].setLayoutX(300-size/2);
        cube[0].setLayoutY(300-size/2);
        
        cube[1].setSize(10);
        cube[1].reSize();
        cube[1].setLayoutX(400-size/2);
        cube[1].setLayoutY(500-size/2);
        
        cube[2].setSize(size);
        cube[2].reSize();
        cube[2].setLayoutX(200-size/2);
        cube[2].setLayoutY(420-size/2);
        
        cube[3].setSize(50);
        cube[3].reSize();
        cube[3].setLayoutX(500-size/2);
        cube[3].setLayoutY(200-size/2);
        
        cube[4].setSize(size);
        cube[4].reSize();
        cube[4].setLayoutX(300-size/2);
        cube[4].setLayoutY(200-size/2);
    }
    
    public void spinningCube()
    {
        cardAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(cube[0].rotationAxisProperty(), new Point3D(1,1,1)), new KeyValue(cube[0].rotateProperty(), 0d) , 
                                            new KeyValue(cube[1].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[1].rotateProperty(), 0d),
                                            new KeyValue(cube[2].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[2].rotateProperty(), 0d),
                                            new KeyValue(cube[3].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[3].rotateProperty(), 0d),
                                            new KeyValue(cube[4].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[4].rotateProperty(), 0d)),
                new KeyFrame(Duration.seconds(0.1),e-> sizeAnimation()),
                new KeyFrame(Duration.seconds(05), new KeyValue(cube[0].rotationAxisProperty(), new Point3D(1,1,1)), new KeyValue(cube[0].rotateProperty(), -360d),
                                                    new KeyValue(cube[1].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[1].rotateProperty(), -360d),
                                                    new KeyValue(cube[2].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[2].rotateProperty(), -360d),
                                                    new KeyValue(cube[3].rotationAxisProperty(), new Point3D(0,1,1)), new KeyValue(cube[3].rotateProperty(), -360d),
                                                    new KeyValue(cube[4].rotationAxisProperty(), new Point3D(1,1,1)), new KeyValue(cube[4].rotateProperty(), -360d))
        );
    }
}
