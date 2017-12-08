package texasholdem;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.PointLight;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

/**
 *
 * @author jiach
 */
public class Cube extends Group{
    private Rectangle rectangle[];
    private int size;
    public Cube()
    {
        size=100;
        rectangle=new Rectangle[6];
        built();
        getChildren().addAll(rectangle[0],rectangle[1],rectangle[2],rectangle[3],rectangle[4],rectangle[5]);
        rectangle[0].setSmooth(true);
    }
    public Cube(int size)
    {
        setSize(size);
        rectangle=new Rectangle[6];
        built();
        getChildren().addAll(rectangle[0],rectangle[1],rectangle[2],rectangle[3],rectangle[4],rectangle[5]);
        rectangle[0].setSmooth(true);
        reSize();
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
       rectangle[0].setHeight(size);
       rectangle[0].setWidth(size);
       rectangle[1].setHeight(size);
       rectangle[1].setWidth(size);
       rectangle[2].setHeight(size);
       rectangle[2].setWidth(size);
       rectangle[3].setHeight(size);
       rectangle[3].setWidth(size);
       rectangle[4].setHeight(size);
       rectangle[4].setWidth(size);
       rectangle[5].setHeight(size);
       rectangle[5].setWidth(size);
    }
    
    public void reSize(int size)
    {
       rectangle[0].setHeight(size);
       rectangle[0].setWidth(size);
       rectangle[1].setHeight(size);
       rectangle[1].setWidth(size);
       rectangle[2].setHeight(size);
       rectangle[2].setWidth(size);
       rectangle[3].setHeight(size);
       rectangle[3].setWidth(size);
       rectangle[4].setHeight(size);
       rectangle[4].setWidth(size);
       rectangle[5].setHeight(size);
       rectangle[5].setWidth(size);
    }
    
    public void Rotate(int x,int y,int z){
        getTransforms().addAll(new Rotate(x, Rotate.X_AXIS), new Rotate(y, Rotate.Y_AXIS),new Rotate(z,Rotate.Z_AXIS));
    }
    
    public void setAllFaceColor(Color c1,Color c2,Color c3,Color c4,Color c5,Color c6){
        rectangle[0].setFill(c1);
        rectangle[1].setFill(c2);
        rectangle[2].setFill(c3);
        rectangle[3].setFill(c4);
        rectangle[4].setFill(c5);
        rectangle[5].setFill(c6);
    } 
    public void setBackFaceColor(Color color){
        rectangle[0].setFill(color);
    }
    public void setBottomColor(Color color){
        rectangle[1].setFill(color);
    }
    public void setRightColor(Color color){
        rectangle[2].setFill(color);
    }
    public void setLeftColor(Color color){
        rectangle[3].setFill(color);
    }
    public void setTopColor(Color color){
        rectangle[4].setFill(color);
    }
    public void setFrontColor(Color color){
        rectangle[5].setFill(color);
    }
    
    public Rectangle getBackFace() { return rectangle[0];}
    public Rectangle getBottomFace() { return rectangle[1];}
    public Rectangle getRightFace() { return rectangle[2];}
    public Rectangle getLeftFace() { return rectangle[3];}
    public Rectangle getTopFace() { return rectangle[4];}
    public Rectangle getFrontFace() { return rectangle[5];}
    
    private void built()
    {
        rectangle[0]=new Rectangle(size,size); // back 
        rectangle[0].setTranslateX(-0.5 * size);
        rectangle[0].setTranslateY(-0.5 * size);
        rectangle[0].setTranslateZ(0.5 * size);
        
        rectangle[1]=new Rectangle(size,size); // bottom
        rectangle[1].setTranslateX(-0.5 * size);
        rectangle[1].setTranslateY(0);
        rectangle[1].setRotationAxis(Rotate.X_AXIS);
        rectangle[1].setRotate(90);
        
        rectangle[2]=new Rectangle(size,size); //right
        rectangle[2].setTranslateX(-1 * size);
        rectangle[2].setTranslateY(-0.5 * size);
        rectangle[2].setRotationAxis(Rotate.Y_AXIS);
        rectangle[2].setRotate(90);
        
        rectangle[3]=new Rectangle(size,size); //left
        rectangle[3].setTranslateX(0);
        rectangle[3].setTranslateY(-0.5 * size);
        rectangle[3].setRotationAxis(Rotate.Y_AXIS);
        rectangle[3].setRotate(90);
        
        rectangle[4]=new Rectangle(size,size); //top
        rectangle[4].setTranslateX(-0.5 * size);
        rectangle[4].setTranslateY(-1 * size);
        rectangle[4].setRotationAxis(Rotate.X_AXIS);
        rectangle[4].setRotate(90);
        
        rectangle[5]=new Rectangle(size,size); //front
        rectangle[5].setTranslateX(-0.5 * size);
        rectangle[5].setTranslateY(-0.5 * size);
        rectangle[5].setTranslateZ(-0.5 * size);
    }
}
