package io.houta.ui;

import javax.swing.*;
import io.houta.model.StopwatchLabel;
import java.awt.*;

class PanelTop extends JPanel {
    private static String []list_size = {"9 x 9","6 x 6","4 x 4"};
    protected JComboBox<String> comboSize = new JComboBox<>(list_size);
    protected JToggleButton toggleBtSolution;
    protected JButton btBack;
    private JLabel nameGame ;
    private StopwatchLabel stopwatch;

    public PanelTop(JFrame parent){
        // 
        // setLayout(new GridBagLayout());
        comboSize.setFont(new Font("UTM Micra", 1, 15));
        toggleBtSolution = new JToggleButton("Solution");
        toggleBtSolution.setFont(new Font("UTM Micra", 1, 15));
        // comboSize.setPreferredSize(new Dimension(70,30));
        btBack = new JButton( new ImageIcon(System.getProperty("user.dir") + "\\sudoku\\src\\main\\resources\\icons\\arrowback_111142.png") );
        // btBack.setPreferredSize( new Dimension(60,20));
        
        nameGame = new JLabel();
        nameGame.setText("SUDOKU");

        stopwatch = new StopwatchLabel();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btBack)
                .addGap(50,50,50)
                .addComponent(nameGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(stopwatch)
                .addGap(18,18,18)
                .addComponent(comboSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toggleBtSolution))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btBack)
                    .addComponent(comboSize)
                    .addComponent(toggleBtSolution)
                    .addComponent(stopwatch)
                    .addComponent(nameGame))
                    
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parent.add(this,"North");
    }

    public void startGame(String size){
        nameGame.setVisible(true);
        comboSize.setVisible(true);
        toggleBtSolution.setVisible(true);
        comboSize.setSelectedItem(size);

        
        stopwatch.setVisible(true);
        stopwatch.reset();
    }

    public void newGameSize(){ 

        stopwatch.reset();
    }

    public void continueGame() {
        nameGame.setVisible(true);
        comboSize.setVisible(true);
        toggleBtSolution.setVisible(true);

        stopwatch.setVisible(true);
        stopwatch.start();
    }

    public void stopGame(){
        nameGame.setVisible(false);
        comboSize.setVisible(false);
        btBack.setVisible(true);
        toggleBtSolution.setVisible(false);

        stopwatch.setVisible(false);
        stopwatch.stop();
    }
}
