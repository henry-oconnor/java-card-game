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
        if (suit.toString().compareTo("Clubs") == 0) {
            return 0.5;
        } else if (suit.toString().compareTo("Spades") == 0) {
            return 98.5;
        } else if (suit.toString().compareTo("Hearts") == 0) {
            return 196.5;
        } else if (suit.toString().compareTo("Diamonds") == 0) {
            return 294.5;
        }
        return 0;
    }

    private double findXCoordinate(Rank rank) {
        if (rank == Rank.TWO) {
            return 1;
        } else if (rank.toString().compareTo("Three") == 0) {
            return 74;
        } else if (rank.toString().compareTo("Four") == 0) {
            return 147;
        } else if (rank.toString().compareTo("Five") == 0) {
            return 220;
        } else if (rank.toString().compareTo("Six") == 0) {
            return 293;
        } else if (rank.toString().compareTo("Seven") == 0) {
            return 366;
        } else if (rank.toString().compareTo("Eight") == 0) {
            return 439;
        } else if (rank.toString().compareTo("Nine") == 0) {
            return 512;
        } else if (rank.toString().compareTo("Ten") == 0) {
            return 585;
        } else if (rank.toString().compareTo("Jack") == 0) {
            return 658;
        } else if (rank.toString().compareTo("Queen") == 0) {
            return 731;
        } else if (rank.toString().compareTo("King") == 0) {
            return 804;
        } else if (rank.toString().compareTo("Ace") == 0) {
            return 877;
        }
        return 0;
    }

}
