package texasholdem;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiach
 */
public class ChipImage {
    private Image image;
    private ImageView imageView;
    
    public ChipImage()
    {
        image=new Image("Image/Chips.png");
        imageView=new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        setImageView(1);
    }
    public ChipImage(int num)
    {
        image=new Image("Image/Chips.png");
        imageView=new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        setImageView(num);
    }
    
    // getter
    public Image getImage() { return image;}
    public ImageView getImageView() { return imageView;}
    
    private void setImageView(int num)
    {
        int x,y;
        x=findXCoordinate(num);
        y=findYCoordinate(num);
        imageView.setViewport(new Rectangle2D(x,y,150,150));
    }
    
    private int findXCoordinate(int num)
    {
        switch(num)
        {
            case 1: 
                return 150;
            case 5:
                return 0;
            case 10:
                return 300;
            case 25:
                return 150;
            case 100:
                return 0;
        }
        return 0;
    }
    
    private int findYCoordinate(int num)
    {
        switch(num)
        {
            case 1: 
                return 150;
            case 5:
                return 150;
            case 10:
                return 300;
            case 25:
                return 300;
            case 100:
                return 0;
        }
        return 0;
    }
}
