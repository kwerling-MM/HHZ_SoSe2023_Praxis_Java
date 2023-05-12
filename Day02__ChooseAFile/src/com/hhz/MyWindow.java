package com.hhz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MyWindow {

    private JFrame window;

    MyWindow() {
        buildWindow();
    }

    private void buildWindow() {
        window = new JFrame("File Chooser");
        window.setSize(300, 300);
        window.setLayout(new GridBagLayout());

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });


        JButton jb = new JButton("Choose a file");
        JLabel jlBuffer = new JLabel(" ");
        JLabel jl = new JLabel("You chose no file");

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.home")));
                int result = jfc.showOpenDialog(window);
                if (result == JFileChooser.APPROVE_OPTION) {
                    jl.setText("You chose: " + jfc.getSelectedFile());
                }
            }
        });

        placeComponent(jb, 0, 0);
        placeComponent(jlBuffer, 0, 1);
        placeComponent(jl, 0, 2);

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
