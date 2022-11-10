package io.houta.ui;

import javax.swing.*;

import io.houta.config.Setting;
import io.houta.model.MyButton;

import java.awt.*;
import java.awt.event.*;

public class PlayUI extends JFrame{
    private int size=0;
    private PanelTop panelTop;
    private PanelGame panelGame = null;
    private PanelClient panelClient;
    private PanelSetting panelSetting;
    private String where = null;

    public PlayUI() {
        inti(); // client
        addPanelTop(); // top
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(PlayUI.EXIT_ON_CLOSE);
        return;
    }
  
    public void inti(){
        panelClient = new PanelClient(this);
        for(int i = 0; i < 4; i++)
            panelClient.mBt[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt){
                    mBtActionListener(evt);
                }
            });
        pack();
    }

    public void addPanelTop(){
        panelTop = new PanelTop(this);
        panelTop.comboSize.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                comboSizeActionListener();
            }
        });
        panelTop.toggleBtSolution.addActionListener(new  ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                toggleBtSolutionActionListener();
            }
        });
        panelTop.btBack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btBackActionListener();
            }
        });
        Component[] com = panelTop.getComponents();
        for (int a = 0; a < com.length; a++) {
            com[a].setVisible(false);
        }
    }

// Event handling at panelClient

    public void mBtActionListener(ActionEvent evt){
        MyButton tmp = (MyButton) evt.getSource();
        panelTop.btBack.setVisible(true);
        switch(tmp.getText()){
            case "Play":
                where = "play";
                int i = 0;
                if(panelGame!=null) panelClient.mBt[i++].setText("Continue..");
                panelClient.mBt[i++].setText("9 x 9");
                panelClient.mBt[i++].setText("6 x 6");
                panelClient.mBt[i++].setText("4 x 4");
                panelClient.mBt[3].setVisible(true);
                if(i!=4) panelClient.mBt[3].setVisible(false);
                break;
            case "Setting":
                where = "setting";
                panelClient.setVisible(false);
                panelSetting = new PanelSetting(this);
                break;
            case "Scoreboard":
                where = "scoreboard";
                System.out.println(1);
                break;
            case "Quit":
                System.exit(0);
            case "Continue..":
                where = "ongame";
                panelTop.continueGame();
                panelTop.setVisible(true);
                panelClient.setVisible(false);
                panelGame.setVisible(true);
                break;
            default:
                where = "ongame";
                panelClient.setVisible(false);
                panelTop.startGame(tmp.getText());
                panelTop.setVisible(true);

        }
    }

    // end panelClient

// Event handling at panelTop
    public void btBackActionListener(){
        panelTop.btBack.setVisible(false);
        switch(where){
            case "play":
                panelClient.setVisible(false);
                inti();
                break;
            case "scoreboard":
                panelClient.setVisible(false);
                inti();
                break;
            case "setting":
                panelSetting.setVisible(false);
                panelClient.setVisible(false);
                inti();
                Setting.saveChange();
                break;
            case "ongame":
                panelClient.setVisible(true);
                panelTop.stopGame();
                panelGame.setVisible(false);
                break;
        }
        if(where.equals("ongame")){
            where = "play";
            panelTop.btBack.doClick();
            panelClient.mBt[0].doClick();
        }else where = null;
    }

    public void comboSizeActionListener(){
        int preSize = size;
        size = Integer.parseInt(panelTop.comboSize.getSelectedItem().toString().charAt(0)+"");
        panelTop.newGameSize();

        if(size==preSize){
            panelGame.changeCellValue();
            return;
        } 
        // create panelGame
        if(panelGame != null) panelGame.setVisible(false);
        panelGame = new PanelGame(size,this);
    }

    private void toggleBtSolutionActionListener(){
        if(panelTop.toggleBtSolution.isSelected()){  // Enable show solution
            panelTop.toggleBtSolution.setText("Hide Solution");
            panelGame.showSolution();
        }else{ // Enable hide solution
            panelTop.toggleBtSolution.setText("Solution");
            panelGame.hideSolution();
        }
    }

    // end panelTop

    @Override
    public void setVisible(boolean appear){
        super.setVisible(appear);
        this.panelTop.setVisible(appear);
        this.panelClient.setVisible(appear);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new PlayUI();
    }               

}