/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texasholdem;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jiach
 */
public class BackOfTheCardImage {
    private Image image;
    private ImageView imageView;
    
    public BackOfTheCardImage()
    {
        image=new Image("Image/backOfTheCard.png");
        imageView=new ImageView(image);
        //imageView.setRotate(90);
        imageView.setViewport(new Rectangle2D(2,0,70,101));
        imageView.setFitHeight(98);
        imageView.setFitWidth(72);
    }
    
    public ImageView getImageView(){
        return imageView;
    }
}
