package texasholdem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author jiach
 */
public class ImageCube extends Group{
    private Image image;
    private ImageView rectangle[];
    private int size;
    public ImageCube()
    {
        size=100;
        image=new Image("Image/cards.png");
        rectangle=new ImageView[6];
        built();
        getChildren().addAll(rectangle[0],rectangle[1],rectangle[2],rectangle[3],rectangle[4],rectangle[5]);
        rectangle[0].setSmooth(true);
    }
    public ImageCube(int size)
    {
        setSize(size);
        image=new Image("Image/cards.png");
        rectangle=new ImageView[6];
        built();
        getChildren().addAll(rectangle[0],rectangle[1],rectangle[2],rectangle[3],rectangle[4],rectangle[5]);
        rectangle[0].setSmooth(true);
    }
    
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        if(size<=0)
            size=100;
        else
            this.size=size;
    }
    
    public void reSize()
    {
       rectangle[0].setFitHeight(size*0.98);
       rectangle[0].setFitWidth(size*0.72);
       rectangle[1].setFitHeight(size*0.98);
       rectangle[1].setFitWidth(size*0.72);
       rectangle[2].setFitHeight(size*0.98);
       rectangle[2].setFitWidth(size*0.72);
       rectangle[3].setFitHeight(size*0.98);
       rectangle[3].setFitWidth(size*0.72);
       rectangle[4].setFitHeight(size*0.98);
       rectangle[4].setFitWidth(size*0.72);
       rectangle[5].setFitHeight(size*0.98);
       rectangle[5].setFitWidth(size*0.72);
    }
    
    public void Rotate(int x,int y,int z){
        getTransforms().addAll(new Rotate(x, Rotate.X_AXIS), new Rotate(y, Rotate.Y_AXIS),new Rotate(z,Rotate.Z_AXIS));
    }
    
    public ImageView getBackFace() { return rectangle[0];}
    public ImageView getBottomFace() { return rectangle[1];}
    public ImageView getRightFace() { return rectangle[2];}
    public ImageView getLeftFace() { return rectangle[3];}
    public ImageView getTopFace() { return rectangle[4];}
    public ImageView getFrontFace() { return rectangle[5];}
    
    private void built()
    {
        rectangle[0]=new ImageView(image); // back 
        rectangle[0].setViewport(new Rectangle2D(0,0,72,98));
        rectangle[0].setTranslateX(-0.5 * size);
        rectangle[0].setTranslateY(-0.5 * size);
        rectangle[0].setTranslateZ(0.5 * size);
        
        rectangle[1]=new ImageView(image); // bottom
        rectangle[1].setViewport(new Rectangle2D(804,294,72,98));
        rectangle[1].setTranslateX(-0.5 * size);
        rectangle[1].setTranslateY(0);
        rectangle[1].setRotationAxis(Rotate.X_AXIS);
        rectangle[1].setRotate(90);
        
        rectangle[2]=new ImageView(image); //right
        rectangle[2].setViewport(new Rectangle2D(0,98,72,98));
        rectangle[2].setTranslateX(-1 * size);
        rectangle[2].setTranslateY(-0.5 * size);
        rectangle[2].setRotationAxis(Rotate.Y_AXIS);
        rectangle[2].setRotate(90);
        
        rectangle[3]=new ImageView(image); //left
        rectangle[3].setViewport(new Rectangle2D(0,196,72,98));
        rectangle[3].setTranslateX(0);
        rectangle[3].setTranslateY(-0.5 * size);
        rectangle[3].setRotationAxis(Rotate.Y_AXIS);
        rectangle[3].setRotate(90);
        
        rectangle[4]=new ImageView(image); //top
        rectangle[4].setViewport(new Rectangle2D(658,196,72,98));
        rectangle[4].setTranslateX(-0.5 * size);
        rectangle[4].setTranslateY(-1 * size);
        rectangle[4].setRotationAxis(Rotate.X_AXIS);
        rectangle[4].setRotate(90);
        
        rectangle[5]=new ImageView(image); //front
        rectangle[5].setViewport(new Rectangle2D(144,196,72,98));
        rectangle[5].setTranslateX(-0.5 * size);
        rectangle[5].setTranslateY(-0.5 * size);
        rectangle[5].setTranslateZ(-0.5 * size);
    }
}
