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
        window.setSize(300, 300);

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        JButton button = new JButton("Click me");
        JButton button2 = new JButton("Click me2");


        window.add( button );
        window.add( button2 );

    }

    public void show() {
        window.setVisible( true );
    }
}
