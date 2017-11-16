package com.java.study.multithreading;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zhongjing on 2016/1/20 0020.
 */
public class BallComponent extends JPanel {
    public void add(Ball b) {
        balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    private ArrayList<Ball> balls = new ArrayList<Ball>();
}
