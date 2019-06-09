package controllers;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

import java.io.IOException;

public class TimerStroke extends AnimationTimer {

    private Label timeLabel;
    private int time;
    private int countTime;
    private long lastUpdate;
    private GameWindowController controller;


    public TimerStroke(GameWindowController controller) {
        this.controller = controller;
        this.timeLabel = controller.timeLabel;
        time = controller.countTime;
        this.countTime = time;
        this.lastUpdate = controller.lastUpdate;

        if (controller.going % 2 == 1) {
            this.controller.nowPoint = controller.pointOne;
            this.controller.nowWords = controller.wordsOne;
            System.out.println(controller.going);
        } else {
            this.controller.nowPoint = controller.pointTwo;
            this.controller.nowWords = controller.wordsTwo;
            System.out.println(controller.going);
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

        if (controller.quanityFreeButton == 0) {
            try {
                controller.openEndWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (countTime == 0) {
//            controller.stopBlinking();
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

            if (!(controller.newButtonSymbol == null)) {
                controller.quanityFreeButton--;
                System.out.println(controller.quanityFreeButton);

                if (controller.quanityFreeButton == 0) {
                    try {
                            controller.openEndWindow();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            controller.newButtonSymbol = null;
            controller.cancelSetSymbol();
        }

        if (countTime == 30) {
//            controller.blinking();
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
}
