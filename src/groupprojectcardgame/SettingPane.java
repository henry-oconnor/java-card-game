
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiach
 */
public class SettingPane extends Pane{
    private Slider soundSlider;
    private Label soundLabel,sLabel;
    
    public SettingPane(BackGroundMusic music)
    {
        soundLabel=new Label("100");
        sLabel=new Label("Sound");
        soundSlider=new Slider();
        soundSlider.setValue(100);
        soundSlider.setOnMouseDragged(e-> SoundSliderHandle(music));
        
        getChildren().addAll(soundSlider,soundLabel,sLabel);
        setTo1200x800();
    }
    
    //getter
    public Slider getSoundSlider() { return soundSlider;}
    public Label getSoundLabel() { return soundLabel;}
    public Label getSlabel() { return sLabel;}
    
    public void SoundSliderHandle(BackGroundMusic music)
    {
        soundLabel.setText((int)soundSlider.getValue() + "");
        music.getMediaPlayer().setVolume(soundSlider.getValue()/100);
        if(soundSlider.getValue()<=9)
            soundLabel.setLayoutX(74 + (soundSlider.getValue())*1.85);
        else if(soundSlider.getValue()<=99)
            soundLabel.setLayoutX(71 + (soundSlider.getValue())*1.85);
    }
    
    public void setTo1200x800()
    {
        
        soundLabel.setLayoutX(256);
        soundLabel.setLayoutY(80);
        
        sLabel.setLayoutX(30);
        sLabel.setLayoutY(100);
        
       // soundSlider.setValue();
        soundSlider.setMinSize(200, 0);
        soundSlider.setLayoutX(70);
        soundSlider.setLayoutY(102);
    }
}
