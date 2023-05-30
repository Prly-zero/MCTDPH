package com.windofall.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Recipe extends JFrame {
    public Recipe(){
        this.setTitle("合成表添加");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        JButton[] jb = new JButton[9];
        int a=0,b=0;
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                jb[i*3+j]=new JButton();
                jb[i*3+j].setBounds(a+68,b+68,64,64);
                a+=68;
                jb[i*3+j].addMouseListener(new Se());
                jp.add(jb[i*3+j]);
            }
            a=0;
            b+=68;
        }
        JButton res = new JButton();
        res.setBounds(a+68*5,b-68,64,64);
        res.addMouseListener(new Se());
        jp.add(res);
        this.add(jp);
        this.setVisible(true);
    }
    class Se implements MouseListener {
        static Icon se_ic;
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton jb = (JButton) e.getSource();
            if(e.getButton()==MouseEvent.BUTTON1){
                if(e.getClickCount()==1) {
                    JFileChooser jf = new JFileChooser();
                    jf.showOpenDialog(getParent());
                    if (jf.getSelectedFile() != null) {
                        jb.setIcon(new ImageIcon(new ImageIcon(jf.getSelectedFile().getPath()).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
                    }
                } else if (e.getClickCount()==2) {
                    jb.setIcon(null);
                }
            } else if (e.getButton()==MouseEvent.BUTTON3) {
                if(e.getClickCount()==1){
                    jb.setIcon(se_ic);
                } else if (e.getClickCount()==2) {
                    se_ic=jb.getIcon();
                }
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
