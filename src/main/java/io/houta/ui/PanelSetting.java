package io.houta.ui;

import javax.swing.*;
import io.houta.config.PanelSetting1;
import io.houta.config.PanelSetting2;
import io.houta.config.PanelSetting3;
import io.houta.config.Setting;

import java.awt.*;
import java.awt.event.*;

public class PanelSetting extends JPanel{
    private JRadioButton rb1,rb2,rb3;
    private ButtonGroup btGroup = new ButtonGroup();
    private PanelSetting1 pSet1 ;
    private PanelSetting2 pSet2 ;
    private PanelSetting3 pSet3 ;

    PlayUI p = null;

    private JPanel bot = new JPanel();

    public PanelSetting(PlayUI parent){
        // default is rb1
        rb1 = new JRadioButton();
        rb2 = new JRadioButton();
        rb3 = new JRadioButton();
        btGroup.add(rb1);
        btGroup.add(rb2);
        btGroup.add(rb3);
        rb1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanelSetting1();
            }
        });
        rb2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanelSetting2();
            }
        });
        rb3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanelSetting3();
            }
        });
        rb1.doClick();
        bot.setLayout(new FlowLayout());
        bot.add(rb1);
        bot.add(rb2);
        bot.add(rb3);
        p = parent;
        parent.add(this);
        parent.add(this.bot,"South");
    }

    public void showPanelSetting1(){
        if(pSet2!=null) pSet2.setVisible(false);
        if(pSet3!=null) pSet3.setVisible(false);
        if( Setting.properties !=null) Setting.saveChange();
        pSet1 = new PanelSetting1();
        add(pSet1);
    }
    public void showPanelSetting2(){
        if(pSet1!=null) pSet1.setVisible(false);
        if(pSet3!=null) pSet3.setVisible(false);
        Setting.saveChange();
        pSet2 = new PanelSetting2();
        add(pSet2);
    }
    public void showPanelSetting3(){
        if(pSet1!=null) pSet1.setVisible(false);
        if(pSet2!=null) pSet2.setVisible(false);
        Setting.saveChange();
        pSet3 = new PanelSetting3();
        add(pSet3);
    }

    @Override
    public void setVisible(boolean appear){
        super.setVisible(appear);
        this.bot.setVisible(appear);
    }
}