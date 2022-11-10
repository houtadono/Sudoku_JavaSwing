package io.houta.model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopwatchLabel extends JLabel implements ActionListener{
    private Timer timer = new Timer(1000, this);
    private int sumSecond = 0;
    private int h=0,m=0,s=0;

    public StopwatchLabel(){
        setFont(new Font("SF Mono",1,15));
        setText("00:00:00");
        setPreferredSize(new Dimension(60,40));
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }

    public void reset(){
        sumSecond = -1;
        setText("00:00:00");
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sumSecond+=1000;
        h = (sumSecond / 3600000);
        m = (sumSecond /   60000) % 60;
        s = (sumSecond /    1000) % 60;
        setText(String.format("%02d:%02d:%02d\n",h,m,s));
    }
}
