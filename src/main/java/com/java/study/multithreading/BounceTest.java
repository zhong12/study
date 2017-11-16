package com.java.study.multithreading;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhongjing on 2016/1/20 0020.
 */
public class BounceTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                                   public void run() {
                                       JFrame frame = new BounceFrame();
                                       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                       frame.setVisible(true);
                                   }
                               }
        );

    }
}
