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
 * Texas Hold'em
 * Java-285 group project
 * Group 2
 * Jiachao Chen, Bernard Heres, Moses Hong, Henry O'Connor
 *
 * @author jiach
 */
public class ChipImage {

    private final int IMAGEVIEW_HEIGHT = 15, IMAGEVIEW_WIDTH = 15;
    private Image image;
    private ImageView imageView;

    public ChipImage() {
        image = new Image(findFile(10));
        imageView = new ImageView(image);
        imageView.setFitHeight(IMAGEVIEW_HEIGHT);
        imageView.setFitWidth(IMAGEVIEW_WIDTH);
    }

    public ChipImage(int num) {
        image = new Image(findFile(num));
        imageView = new ImageView(image);
        imageView.setFitHeight(IMAGEVIEW_HEIGHT);
        imageView.setFitWidth(IMAGEVIEW_WIDTH);
    }

    // getter
    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getImageViewHeight() {
        return IMAGEVIEW_HEIGHT;
    }

    public int getImageViewWidth() {
        return IMAGEVIEW_WIDTH;
    }

    /**
     * find chip image location
     *
     * @param num
     * @return
     */
    private String findFile(int num) {
        switch (num) {
            case 10:
                return "Image/whileChips.png";
            case 50:
                return "Image/redChips.png";
            case 100:
                return "Image/blueChips.png";
            case 200:
                return "Image/greenChips.png";
            case 500:
                return "Image/blackChips.png";
        }
        return "Image/whileChips.png";
    }
}
