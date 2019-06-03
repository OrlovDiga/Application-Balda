package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.IOException;

public class TimerStroke extends AnimationTimer {

    private Label timeLabel;
    private int time;
    private int countTime;
    private long lastUpdate;
    private GameWindowController controller;
    boolean blintState;


    TimerStroke(GameWindowController controller) {
        this.controller = controller;
        this.timeLabel = controller.timeLabel;
        time = controller.countTime;
        this.countTime = time;
        this.lastUpdate = controller.lastUpdate;
        if (controller.going % 2 == 1) {
            this.controller.nowPoint = controller.pointOne;
            this.controller.nowWords = controller.wordsOne;
        } else {
            this.controller.nowPoint = controller.pointTwo;
            this.controller.nowWords = controller.wordsTwo;
        }
    }


    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 1_000_000_000 && countTime != 0) {
            String k = "0" + countTime / 60 + ":" + countTime % 60;
            timeLabel.setText(k);
            countTime--;
            lastUpdate = now;
        }

        if (countTime == 0) {
            controller.going++;
            if (controller.going % 2 == 1) {
                controller.nowPoint = controller.pointOne;
                controller.nowWords = controller.wordsOne;
            } else {
                controller.nowPoint = controller.pointTwo;
                controller.nowWords = controller.wordsTwo;
            }
            countTime = time;
            lastUpdate = 0;
            controller.newButtonSymbol = null;
            controller.cancelSetSymbol();
        }

        if (controller.quanityFreeButton == 0) {
            try {
                controller.openEndWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        if (countTime == 30) {
//            ThreadTik one = new ThreadTik();
//            one.run();
//        }
//        if (countTime < 30) {
//            blintState = !blintState;
//            if (blintState) {
//                timeLabel.setStyle("-fx-text-inner-color: red;");
//            } else {
//                timeLabel.setStyle("-fx-text-inner-color: black;");
//            }
//        }
//
//        if (controller.quanityFreeButton == 0) {
//
//        }

    }

//    class ThreadTik implements Runnable {
//
//        @Override
//        public void run() {
//            if (countTime == 30) {
//                new MediaPlayer(new Media("file:///Users/macbook/untitled3/tikan-e-chasov-rovno-30-sekund.mp3")).play();
//            }
//        }
//    }
}
