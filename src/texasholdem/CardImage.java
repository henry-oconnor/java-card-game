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
public class CardImage {

    private Image image;
    private ImageView imageView;

    public CardImage() {
        image = new Image("Image/cards.png");
        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(1, 0.5, 71.5, 97.5));
    }

    public CardImage(Rank rank, Suit suit) {
        image = new Image("Image/cards.png");
        imageView = new ImageView(image);
        setImageView(rank, suit);
    }

    // getter
    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    /**
     * find set the viewPort of the image
     *
     * @param rank
     * @param Suit
     */
    private void setImageView(Rank rank, Suit suit) {
        double x, y;
        x = findXCoordinate(rank);
        y = findYCoordinate(suit);
        imageView.setViewport(new Rectangle2D(x, y, 72, 98));
    }

    private double findYCoordinate(Suit suit) {
        final int SPACING = 98;
        double returnVal = 0;
        if (null != suit) 
            switch (suit) {
            case CLUBS:
                returnVal = (SPACING * 0) + 0.5;
                break;
            case SPADES:
                returnVal = (SPACING * 1) + 0.5;
                break;
            case HEARTS:
                returnVal = (SPACING * 2) + 0.5;
                break;
            case DIAMONDS:
                returnVal = (SPACING * 3) + 0.5;
                break;
            default:
                break;
        }
        return returnVal;
    }

    private double findXCoordinate(Rank rank) {
        final int SPACING = 73;
        int returnVal = 0;
        if (null != rank) 
            switch (rank) {
            case TWO:
                returnVal = (SPACING * 1) + 1;
                break;
            case THREE:
                returnVal = (SPACING * 2) + 1;
                break;
            case FOUR:
                returnVal = (SPACING * 3) + 1;
                break;
            case FIVE:
                returnVal = (SPACING * 4) + 1;
                break;
            case SIX:
                returnVal = (SPACING * 5) + 1;
                break;
            case SEVEN:
                returnVal = (SPACING * 6) + 1;
                break;
            case EIGHT:
                returnVal = (SPACING * 7) + 1;
                break;
            case NINE:
                returnVal = (SPACING * 8) + 1;
                break;
            case TEN:
                returnVal = (SPACING * 9) + 1;
                break;
            case JACK:
                returnVal = (SPACING * 10) + 1;
                break;
            case QUEEN:
                returnVal = (SPACING * 11) + 1;
                break;
            case KING:
                returnVal = (SPACING * 12) + 1;
                break;
            case ACE:
                returnVal = (SPACING * 13) + 1;
                break;
            default:
                break;
        }
        return returnVal;
    }

}
