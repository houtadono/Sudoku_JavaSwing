package io.houta.model;

import java.awt.*;

import javax.swing.*;

public class MyLabel extends JLabel{
    public MyLabel(String text){
        setFont(new Font("SF Mono",1,20));
        setText(text);
        
    }
}
