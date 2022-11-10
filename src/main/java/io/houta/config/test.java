package io.houta.config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import io.houta.model.StopwatchLabel;

public class test extends JFrame {
    StopwatchLabel watch ;
    
    public test() {

        JPanel a = new JPanel();
        a.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton jb1 = new JButton("start");
        jb1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                act(e);
                
            }
        });
        a.add(jb1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton jb2 = new JButton("stop");
        jb2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                act(e);
                
            }
        });
        a.add(jb2, gbc);
 
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        watch = new StopwatchLabel();
        a.add(watch, gbc);
        add(a);
        setVisible(true);
        setSize(600,600);
        setLocationRelativeTo(null);
    }
    public void act(ActionEvent e){
        JButton tmp = (JButton) e.getSource();
        if(tmp.getText().equals("start")){
            watch.start();
        }else{
            watch.stop();
        }
    }
    public static void main(String[] args) {
        new test();
    }
}