
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
public class CardImage {
    private Image image;
    private ImageView imageView;
    
    public CardImage()
    {
        image=new Image("cards.png");
        imageView=new ImageView(image);
        imageView.setViewport(new Rectangle2D(0,0,72,98));
    }
    public CardImage(Rank rank,Suit suit)
    {
        image=new Image("cards.png");
        imageView=new ImageView(image);
        setImageView(rank,suit);
    }
    
    // getter
    public Image getImage() { return image;}
    public ImageView getImageView() { return imageView;}
    
    /**
     * find set the viewPort of the image
     * @param rank
     * @param Suit 
     */
    private void setImageView(Rank rank,Suit suit)
    {
        int x,y;
        x=findXCoordinate(rank);
        y=findYCoordinate(suit);
        imageView.setViewport(new Rectangle2D(x,y,72,98));
    }
    
    private int findYCoordinate(Suit suit)
    {
        if(suit.toString().compareTo("Clubs")==0)
            return 0;
        else if(suit.toString().compareTo("Spades")==0)
            return 98;
        else if(suit.toString().compareTo("Hearts")==0)
            return 196;
        else if(suit.toString().compareTo("Diamonds")==0)
            return 294;
        return 0;
    }
    
    private int findXCoordinate(Rank rank)
    {
        if(rank.toString().compareTo("Two")==0)
            return 0;
        else if(rank.toString().compareTo("Three")==0)
            return 0;
        else if(rank.toString().compareTo("Four")==0)
            return 0;
        else if(rank.toString().compareTo("Five")==0)
            return 0;
        else if(rank.toString().compareTo("Six")==0)
            return 0;
        else if(rank.toString().compareTo("Seven")==0)
            return 0;
        else if(rank.toString().compareTo("Eight")==0)
            return 0;
        else if(rank.toString().compareTo("Nine")==0)
            return 0;
        else if(rank.toString().compareTo("Ten")==0)
            return 0;
        else if(rank.toString().compareTo("Jack")==0)
            return 0;
        else if(rank.toString().compareTo("Queen")==0)
            return 0;
        else if(rank.toString().compareTo("King")==0)
            return 0;
        else if(rank.toString().compareTo("Ace")==0)
            return 0;
        return 0;
    }
    
    
}
