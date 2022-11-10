package io.houta.config;

// import java.awt.event.*;
import javax.swing.*;

import io.houta.model.MyLabel;
import io.houta.model.MyRadioButton;
import io.houta.model.Setting1;

public class PanelSetting1 extends JPanel {
    public MyLabel[] mlabel = new MyLabel[5];
    public MyRadioButton[] rb = new MyRadioButton[5];

    private Setting1 set1;

    public PanelSetting1(){
        setPreferredSize(new java.awt.Dimension(600, 600));

        mlabel[0] = new MyLabel("Stopwatch");
        mlabel[1] = new MyLabel("Error Checking");
        mlabel[2] = new MyLabel("Mark Duplicate Numbers");
        mlabel[3] = new MyLabel("Mark Zone");
        mlabel[4] = new MyLabel("mlabel5");

        Setting.readFileSetting();
        set1 = Setting.getSetting1();
        System.out.println(set1);

        rb[0] = new MyRadioButton(set1.isStopwatch(), "stopwatch");
        rb[1] = new MyRadioButton(set1.isErrorChecking(), "errorChecking");
        rb[2] = new MyRadioButton(set1.isMarkDuplicateNumbers(), "markDuplicateNumbers");
        rb[3] = new MyRadioButton(set1.isMarkZone(), "markZone");
        rb[4] = new MyRadioButton(true, "");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100,100,100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mlabel[0], javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(mlabel[1], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mlabel[2], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mlabel[3], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mlabel[4], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(116, 116, 116)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rb[0])
                    .addComponent(rb[1])
                    .addComponent(rb[2])
                    .addComponent(rb[3])
                    .addComponent(rb[4]))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mlabel[0])
                    .addComponent(rb[0]))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mlabel[1])
                    .addComponent(rb[1]))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mlabel[2])
                    .addComponent(rb[2]))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mlabel[3])
                    .addComponent(rb[3]))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mlabel[4])
                    .addComponent(rb[4]))
                .addContainerGap(330, Short.MAX_VALUE))
        );
    }
}
