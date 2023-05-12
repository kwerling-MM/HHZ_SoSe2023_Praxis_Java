package com.hhz;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow {

    private JFrame window;

    MyWindow() {
        buildWindow();
    }

    private void buildWindow() {
        window = new JFrame("First Swing Gui");

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
    }

    public void show() {
        window.setVisible( true );
    }
}
