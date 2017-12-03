package texasholdem;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class SettingPane extends Pane {

    private Slider soundSlider;
    private Label soundLabel, sLabel, bgmList;
    private ComboBox bgmCB;
    private Button doneBtm;

    public SettingPane(BackGroundMusic music) {
        soundLabel = new Label("100");
        sLabel = new Label("Sound");
        soundSlider = new Slider();
        soundSlider.setValue(100);
        soundSlider.setOnMouseDragged(e -> SoundSliderHandle(music));

        bgmList = new Label("Music ");
        bgmCB = new ComboBox();
        bgmCB.setValue("人生は目玉焼きのよう");
        bgmCB.getItems().addAll("人生は目玉焼きのよう", "Attention!", "ブラン・ニュー・トゥデイ", "事務所は幼稚園", "嘘と真実の間", "チョコボのテーマ", "美好时光");
        bgmCB.setOnAction(e -> BgmCBHandle(music));

        doneBtm = new Button("DONE");

        getChildren().addAll(soundSlider, soundLabel, sLabel, bgmList, bgmCB, doneBtm);
        setTo1200x800();
    }

    //getter
    public Slider getSoundSlider() {
        return soundSlider;
    }

    public Label getSoundLabel() {
        return soundLabel;
    }

    public Label getSlabel() {
        return sLabel;
    }

    public Button getDoneBtm() {
        return doneBtm;
    }

    public void BgmCBHandle(BackGroundMusic music) {
        music.SwitchMusic(bgmCB.getValue().toString());
    }

    public void SoundSliderHandle(BackGroundMusic music) {
        soundLabel.setText((int) soundSlider.getValue() + "");
        music.getMediaPlayer().setVolume(soundSlider.getValue() / 100);
        if (soundSlider.getValue() <= 9) {
            soundLabel.setLayoutX(74 + (soundSlider.getValue()) * 1.85);
        } else if (soundSlider.getValue() <= 99) {
            soundLabel.setLayoutX(71 + (soundSlider.getValue()) * 1.85);
        }
    }

    public void setTo1200x800() {

        soundLabel.setLayoutX(256);
        soundLabel.setLayoutY(80);

        sLabel.setLayoutX(30);
        sLabel.setLayoutY(100);

        // soundSlider.setValue();
        soundSlider.setMinSize(200, 0);
        soundSlider.setLayoutX(70);
        soundSlider.setLayoutY(102);

        bgmList.setLayoutX(30);
        bgmList.setLayoutY(150);

        bgmCB.setLayoutX(70);
        bgmCB.setLayoutY(145);
        bgmCB.setMinSize(200, 0);

        doneBtm.setMinSize(250, 0);
        doneBtm.setLayoutX(25);
        doneBtm.setLayoutY(360);
    }
}
