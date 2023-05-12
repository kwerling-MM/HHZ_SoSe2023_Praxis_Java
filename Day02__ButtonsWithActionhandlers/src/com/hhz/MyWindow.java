package com.hhz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow {

    private JFrame window;

    MyWindow() {
        buildWindow();
    }

    private void buildWindow() {
        window = new JFrame("Buttons with Action handlers");
        window.setSize(300, 300);
        window.setLayout(new GridBagLayout());

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });


        String[] labels = { "Button 1", "Button 2", "Button 3" };
        JLabel[] jlArr = new JLabel[3];

        for( int i = 0; i < labels.length; i++ ) {
            JButton jb = new JButton( labels[i] );

            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println( ((JButton)e.getSource()).getText() );
                    String bText = ((JButton)e.getSource()).getText();
                    int lNum = Integer.parseInt(bText.substring(bText.length()-1, bText.length()));
                    for (int i = 0; i < jlArr.length; i++) {
                        jlArr[i].setText("");
                    }
                    jlArr[lNum -1].setText( "     X" );

                }
            });

            placeComponent( jb, 0, i );

            JLabel jl = new JLabel("", JLabel.TRAILING);
            jlArr[i] = jl;
            placeComponent( jl, 1, i );
         }
    }

    void placeComponent(Component comp, int x, int y ) {
        placeComponent( comp, x, y, 1);
    }
    void placeComponent(Component comp, int x, int y, int width ) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = x; c.gridy = y;
        c.gridwidth = width;
        window.add(comp, c);
    }

    public void show() {
        window.setVisible( true );
    }
}
