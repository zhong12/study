package com.java.study.multithreading;

import java.awt.*;

/**
 * Created by zhongjing on 2016/1/20 0020.
 */
public class BallRunnable implements Runnable {
    private Ball ball;
    private Component component;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 1000 * 100;
    public static final int DELAY = 5;

    public BallRunnable(Ball aBall, Component aComponent) {
        ball = aBall;
        component = aComponent;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= STEPS; i++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        } catch (Exception e) {
        }
    }



}
