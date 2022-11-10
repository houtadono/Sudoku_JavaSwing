package io.houta.model;

import javax.swing.JRadioButton;

import io.houta.config.Setting;

import java.awt.event.*;
import javax.swing.*;

public class MyRadioButton extends JRadioButton implements ItemListener, ActionListener{

    public MyRadioButton(boolean selected, String actionCommand){
        addItemListener(this);
        addActionListener(this);
        setSelected(selected);
        setActionCommand(actionCommand);
    }

    public void setSelected(boolean check){
        super.setSelected(check);
        if(check == false){
            setIcon(new ImageIcon(System.getProperty("user.dir") + "\\sudoku\\src\\main\\resources\\icons\\switch-off-icon_34344.png"));  
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        MyRadioButton tmp =  (MyRadioButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Your selected code here.
            tmp.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\sudoku\\src\\main\\resources\\icons\\switch-on-icon_34343.png"));
        }
        else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // Your deselected code here.
            tmp.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\sudoku\\src\\main\\resources\\icons\\switch-off-icon_34344.png"));   
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyRadioButton tmp = (MyRadioButton) e.getSource();
        Setting.properties.setProperty(tmp.getActionCommand(),String.valueOf( tmp.isSelected()));
    }
}
