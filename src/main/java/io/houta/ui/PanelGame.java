package io.houta.ui;

import io.houta.config.Setting;
import io.houta.model.Setting1;
import io.houta.model.Setting3;
import io.houta.model.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.*; 
import java.awt.event.*;

public class PanelGame extends JPanel{
    private JButton btCell[][];
    private Sudoku a;
    private Pos zone = null; // size a zone

    private Pos prePos = null;
    private int preValue = 0;  

    private int valueFromPanelBot = 0;
    private int keyCode = 0;  // if keyCode != 0 : is typing else click 

    private ArrayList<ArrayList<Pos>> listCurPosSortByValue = new ArrayList<>(); // save list pos String(i+" "+j) had value
    private ArrayList<Pos> listCurPos = new ArrayList<>();
    private ArrayList<Pos> listHidePos = new ArrayList<>();
    private PanelBot panelBot ;
    
    private Setting1 set1 = null;
    private Setting3 set3 = null;

    private int error_count = 0;

    public PanelGame(int size, JFrame parent){  // create new game with new size 
        Setting.readFileSetting();
        set1 = Setting.getSetting1();
        set3 = Setting.getSetting3();

        setLayout(new GridLayout(size, size, 2,2 ));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        a = new Sudoku(size);
        for(int i = 0; i<=size; i++){
            listCurPosSortByValue.add(new ArrayList<Pos>());
        }
        btCell =  new JButton[size][size];
        for(int i = 0; i<size; i++)
            for(int j = 0; j<size; j++){
                btCell[i][j] = new JButton();
                btCell[i][j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
                btCell[i][j].setActionCommand(i+" "+j);
                btCell[i][j].setFont(new Font("UTM Micra", 1, 30));
                btCell[i][j].setText(a.getCell(i, j));
                btCell[i][j].setForeground(Color.decode(set3.getColorForegroundDefaultCell()));
                
                btCell[i][j].addActionListener(new  ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        btCellActionListener(evt);
                    }
                });
                
                btCell[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if(SwingUtilities.isRightMouseButton(evt)){
                            btCellRightMouseClick(evt);
                            return;
                        }
                    }
                });

                btCell[i][j].addKeyListener(new KeyListener(){

                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        // btCellKeyPress(e);
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        btCellKeyPress(e);
                    }
                });
                
                add(btCell[i][j]);

                if(a.getCell(i, j)!=null){
                    listCurPosSortByValue.get(Integer.parseInt(a.getCell(i, j))).add(new Pos(i,j));
                    listCurPos.add(new Pos(i,j,a.getCell(i, j))) ;
                }else{
                    btCell[i][j].setForeground(Color.decode(set3.getColorForegroundAddCell()));
                    listHidePos.add(new Pos(i,j,a.getHideCell(i, j)));
                }     
            }
        
        // create Border 
        int column = (size%3==0)? 3 : 2;
        int row = (size%2==1) ? 3 : 2;
        for (int i = 0; i < size; i++){
            int top = 1;
            int bottom = 1;
            if( i%row == 0) top = 3;
            else if( (i+1)%row == 0 ) bottom = 3;

            for (int j = 0; j < size; j++) {
                int left = 1;
                int right = 1;
                if( j%column == 0 ) left = 3;
                if( (j+1)%column == 0) right = 3;
                btCell[i][j].setBorder(BorderFactory.createMatteBorder(top,left,bottom,right, Color.black)); // màu khung
            }
        }
        zone = new Pos(row,column);

        // create PanelBot
        this.panelBot = new PanelBot(size);
        for(int i = 0 ; i <= size; i++){
            panelBot.btSelect[i].addActionListener(new  ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    btSelectActionListener(evt);
                }
            });
        }
        
        parent.add(this);
        parent.add(this.panelBot,"South");
        setForcus();
    }

    public void changeCellValue(){ // create new game with old size
        listCurPosSortByValue.clear();
        listHidePos.clear();
        set1 = Setting.getSetting1();
        set3 = Setting.getSetting3();
        for(int i = 0; i<=btCell.length; i++){
            listCurPosSortByValue.add(new ArrayList<Pos>());
        }
        a.creatrMatric();
        for(int i = 0; i<btCell.length; i++)
            for(int j = 0; j<btCell.length; j++){
                btCell[i][j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
                btCell[i][j].setText(a.getCell(i, j));
                btCell[i][j].setForeground(Color.decode(set3.getColorForegroundDefaultCell()));

                if(a.getCell(i, j)!=null){
                    listCurPosSortByValue.get(Integer.parseInt(a.getCell(i, j))).add(new Pos(i,j));
                    listCurPos.add(new Pos(i,j,a.getCell(i, j))) ;  
                }else{
                    btCell[i][j].setForeground(Color.decode(set3.getColorForegroundAddCell()));
                    listHidePos.add(new Pos(i,j,a.getHideCell(i, j)));
                } 
                    
            }
        setVisible(true);
    }

    public void addValue(Pos p, int value){//
        this.btCell[p.i][p.j].setText(String.valueOf(value));
        this.listCurPosSortByValue.get(value).add(p);
        this.listCurPos.add(new Pos(p.i,p.j,String.valueOf(value)));
        colorDuplicateNumbers(value);
        if(set1.isErrorChecking()){
            if(value != Integer.parseInt(a.getHideCell(p.i,p.j))){
                error_count++;
                this.btCell[p.i][p.j].setForeground( Color.decode(set3.getColorForegroundErrorCell() ));
            }
        }
    }

    public void setValue(Pos p, int value, int value_old){//
        this.listCurPosSortByValue.get(value_old).remove(p);
        this.listCurPos.remove(p);

        addValue(p, value);
    }

    public void delValue(Pos p){
        JButton tmp = this.btCell[p.i][p.j];
        if(tmp.getText() == null || tmp.getForeground().equals( Color.decode(set3.getColorForegroundDefaultCell()))) return;
        for(int i = 1; i <= this.btCell.length; i++){
            if(this.listCurPosSortByValue.get(i).contains(p)){
                this.listCurPosSortByValue.get(i).remove(p);
                break;
            }
        }
        this.listCurPos.remove(p);
        this.btCell[p.i][p.j].setText(null);
        this.btCell[p.i][p.j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
        this.btCell[p.i][p.j].setForeground(Color.decode(set3.getColorForegroundAddCell()));
    }

    public Pos findPos(JButton tmp){
        for(int i = 0 ; i < btCell.length; i++)
            for(int j = 0; j < btCell.length; j++){
                if(tmp == btCell[i][j]){
                    return new Pos(i,j);
                }
            }
        return null;
    }

    public void showSolution(){
        panelBot.btSelect[0].doClick();
        panelBot.btSelect[0].setBackground(Color.white);

        // hide panelBot
        Component[] com = panelBot.getComponents();
        for (int a = 0; a < com.length; a++) {
            com[a].setEnabled(false);
        }
        //TODO here
        for(int i = 0 ; i < btCell.length; i++){
            for(int j = 0; j < btCell.length; j++){
                if(btCell[i][j].getText()==null){
                    btCell[i][j].setText(a.getHideCell(i, j));
                    btCell[i][j].setForeground(Color.decode(set3.getColorForegroundSolutionCell()));
                }else if( !btCell[i][j].getForeground().equals( Color.decode(set3.getColorForegroundAddCell()) ) ){
                    if( !btCell[i][j].getText().equals(a.getHideCell(i, j)) ){
                        btCell[i][j].setText(a.getHideCell(i, j));
                        btCell[i][j].setForeground(Color.decode(set3.getColorForegroundErrorCell()));
                    }
                }
            }
        }
    }

    public void hideSolution(){
        panelBot.setEnabled(true);

        Component[] com = panelBot.getComponents();
        for (int a = 0; a < com.length; a++) {
            com[a].setEnabled(true);
        }
        for(int i = 0 ; i < btCell.length; i++){
            for(int j = 0; j < btCell.length; j++){
                if( btCell[i][j].getForeground().equals( Color.decode(set3.getColorForegroundErrorCell()) ) ){
                    btCell[i][j].setText(listCurPos.get(listCurPos.indexOf(new Pos(i, j))).value );
                    btCell[i][j].setForeground(Color.decode(set3.getColorForegroundAddCell()));
                }
                if( btCell[i][j].getForeground().equals( Color.decode(set3.getColorForegroundSolutionCell()) ) ){
                    btCell[i][j].setText(null);
                    btCell[i][j].setForeground(Color.decode(set3.getColorForegroundAddCell()));
                }
            }
        }
        setForcus();
    }
    
    public void colorDuplicateNumbers(int curValue){  
        if( !set1.isMarkDuplicateNumbers()){
            this.preValue = curValue;
            return;
        }

        if(this.prePos != null){
            if( !this.btCell[prePos.i][prePos.j].getBackground().equals(Color.decode(set3.getColorBackgroundSelectZone())) )
                this.btCell[this.prePos.i][this.prePos.j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
            this.prePos = null;
        }
        if(this.preValue!=0){
            for(Pos tmp : this.listCurPosSortByValue.get(this.preValue)){
                if( !this.btCell[tmp.i][tmp.j].getBackground().equals(Color.decode(set3.getColorBackgroundSelectZone())) ) 
                    this.btCell[tmp.i][tmp.j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
            }
        }
        if( curValue!=0 )
            for(Pos tmp : this.listCurPosSortByValue.get(curValue)){
                this.btCell[tmp.i][tmp.j].setBackground(Color.decode(set3.getColorBackgroundSameValue()));
            }
        this.preValue = curValue;
    }

    public void colorColumnRowArea(Pos p){
        
        
        int start_row ,start_column;
        if(prePos != null){ // remove color of row column area
            for(int x = 0; x < btCell.length; x++){
                btCell[x][prePos.j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
                btCell[prePos.i][x].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
            }
            start_row = prePos.i/zone.i*zone.i;
            start_column = prePos.j/zone.j*zone.j;
            for(int i = start_row; i < start_row + zone.i; i++ )
                for(int j = start_column; j < start_column + zone.j; j++)
                    btCell[i][j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));
        }
        if(!set1.isMarkZone()) return ;
        //color of row column area
        if(p==null) return;
        for(int x = 0; x < btCell.length; x++){
            btCell[x][p.j].setBackground(Color.decode(set3.getColorBackgroundSelectZone()));
            btCell[p.i][x].setBackground(Color.decode(set3.getColorBackgroundSelectZone()));
        }
        start_row = p.i/zone.i*zone.i;
        start_column = p.j/zone.j*zone.j;
        for(int i = start_row; i < start_row + zone.i; i++ )
            for(int j = start_column; j < start_column + zone.j; j++)
                btCell[i][j].setBackground(Color.decode(set3.getColorBackgroundSelectZone()));
    }

    private void btCellActionListener(ActionEvent evt){ // when select a btCell[][]
        // get Pos 
        String strOnBtCell = evt.getActionCommand();
        int i = Integer.parseInt(strOnBtCell.charAt(0)+"");
        int j = Integer.parseInt(strOnBtCell.charAt(2)+"");
        Pos curPos = new Pos(i,j);
        
        // color column, row, and area when click 
        colorColumnRowArea(curPos);
        
        // // color cell(i,j), same value with this cell and add/set value when u click,type
        if(this.btCell[i][j].getForeground().equals( Color.decode(set3.getColorForegroundDefaultCell())) ){
            colorDuplicateNumbers(Integer.parseInt(this.btCell[i][j].getText()));
        }else if(this.keyCode < 49 || this.keyCode > 48+this.btCell.length){ // u click a cell
            if(this.valueFromPanelBot != 0 && this.keyCode == 0){ // if u select 1 -> size (panelBot) 
                if( this.btCell[i][j].getText()==null )
                    addValue(curPos, this.valueFromPanelBot);
                else if ( !this.btCell[i][j].getText().equals( String.valueOf(this.valueFromPanelBot) ) ) {
                    setValue(curPos, this.valueFromPanelBot, Integer.parseInt(this.btCell[i][j].getText()));
                    colorColumnRowArea(curPos);
                }
            }else if(this.btCell[i][j].getText()!=null)
                colorDuplicateNumbers(Integer.parseInt(this.btCell[i][j].getText()));
            else colorDuplicateNumbers(0);
        }else if(this.keyCode>48&&this.keyCode<49+this.btCell.length){
            if( this.btCell[i][j].getText()==null )
                addValue(curPos, this.keyCode - 48);
            else if ( !this.btCell[i][j].getText().equals( String.valueOf(this.keyCode - 48) ) ) 
                setValue(curPos, this.keyCode - 48, Integer.parseInt(this.btCell[i][j].getText()));
        }else colorDuplicateNumbers(0);

        if(prePos!=null && !this.btCell[prePos.i][prePos.j].getBackground().equals( Color.decode(set3.getColorBackgroundSelectZone())))
            this.btCell[prePos.i][prePos.j].setBackground(Color.decode(set3.getColorBackgroundDefaultCell()));

        this.btCell[curPos.i][curPos.j].setBackground(Color.decode(set3.getColorBackgroundClickCell()));
        prePos = curPos;
    }

    private void btCellRightMouseClick(MouseEvent evt){
        JButton tmp = (JButton) evt.getSource();
        delValue(findPos(tmp));
    }

    private void btCellKeyPress(KeyEvent e){
        Pos p = findPos((JButton) e.getSource());
        if(p==null) return ;
        keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP: //38
                if(p.i == 0) p.i = btCell.length;
                btCell[p.i-1][p.j].requestFocus();
                btCell[p.i-1][p.j].doClick();
                break;
            case KeyEvent.VK_DOWN: //40
                if(p.i == btCell.length - 1) p.i = -1;
                btCell[p.i+1][p.j].requestFocus();
                btCell[p.i+1][p.j].doClick();
                break;
            case KeyEvent.VK_LEFT: //37
                if(p.j == 0) p.j = btCell.length;
                btCell[p.i][p.j-1].requestFocus();
                btCell[p.i][p.j-1].doClick();
                break;
            case KeyEvent.VK_RIGHT: //39
                if(p.j == btCell.length - 1) p.j = -1;
                btCell[p.i][p.j+1].requestFocus();
                btCell[p.i][p.j+1].doClick();
                break;
            case KeyEvent.VK_DELETE:
            case KeyEvent.VK_0:
            case KeyEvent.VK_BACK_SPACE: 
                delValue(p);
                btCell[p.i][p.j].doClick();
                break;
            default:
                btCell[p.i][p.j].doClick();
                break;
        }
        keyCode = 0;
    }

    private void btSelectActionListener(ActionEvent evt){
        int posButtonSelect = Integer.parseInt(evt.getActionCommand());
        if(panelBot.preBt != null ){
            panelBot.preBt.setBackground(Color.white);
        }
        panelBot.btSelect[posButtonSelect].setBackground(Color.cyan);
        panelBot.preBt = panelBot.btSelect[posButtonSelect];
        colorColumnRowArea(null); prePos = null;
        colorDuplicateNumbers(posButtonSelect);   
        panelBot.value = posButtonSelect;
        valueFromPanelBot = panelBot.value;
    }

    private void setForcus(){
        btCell[0][0].doClick();
        btCell[0][0].requestFocus();
    }

    @Override
    public void setVisible(boolean appear){
        super.setVisible(appear);
        this.panelBot.setVisible(appear);
        if(appear) setForcus();
    }
}

class Pos{
    public int i;
    public int j;
    public String value;

    public Pos(){
        i = -1;
        j = -1;
    }

    public Pos(int i, int j){
        this.i = i;
        this.j = j;
    }

    public Pos(int i, int j,String value){
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o.getClass()==this.getClass()){
            return i == ((Pos)o).i && j == ((Pos)o).j;
        }
        return false;
    }

    public boolean equalsAll(Object o){
        return this.equals(o) && this.value.equals(((Pos)o).value);
    }

}

class PanelBot extends JPanel {
    public JButton []btSelect ;
    public JButton preBt = null;
    public int value = 0;

    public PanelBot(int size){
        btSelect = new JButton[size+1];
        setLayout(new FlowLayout());

        for(int i = 0 ; i <= size; i++){
            btSelect[i] = new JButton();
            btSelect[i].setBackground(Color.white);
            btSelect[i].setActionCommand(String.valueOf(i));
            btSelect[i].setFont(new Font("UTM Micra", 1, 30));
            btSelect[i].setForeground(Color.black);
            if( i==0 ) btSelect[i].setText(" ");
            else btSelect[i].setText(String.valueOf(i));
            add(btSelect[i]);
        }
    }
}
