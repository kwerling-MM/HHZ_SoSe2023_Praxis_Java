package com.hhz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyWindow {

    private JFrame window;

    MyWindow() {
        buildWindow();
    }

    private void buildWindow() {
        window = new JFrame("Layout Manager in Action");
        window.setSize(300, 300);
        window.setLayout(new GridBagLayout());

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });


        String[] labels = { "Name:  ", "Street:  ", "City:  " };

        for( int i = 0; i < labels.length; i++ ) {
            JLabel jl = new JLabel(labels[i], JLabel.TRAILING);
            JTextField jt = new JTextField(10);

            placeComponent( jl, 0, i );
            placeComponent( jt, 1, i );
        }

    }

    void placeComponent(Component comp, int x, int y ) {
        placeComponent( comp, x, y, 1);
    }
    void placeComponent(Component comp, int x, int y, int width ) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x; c.gridy = y;
        window.add(comp, c);
    }

    public void show() {
        window.setVisible( true );
    }
}
