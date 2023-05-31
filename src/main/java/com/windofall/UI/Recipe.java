package com.windofall.UI;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Recipe extends JFrame {
    public Recipe(){
        this.setResizable(false);
        this.setTitle("合成表添加");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        JLabel backimg = new JLabel();
        Image image = new ImageIcon(this.getClass().getResource("/assets/crafting.png")).getImage().getScaledInstance(786,590,Image.SCALE_DEFAULT);
        backimg.setIcon(new ImageIcon(image));
        backimg.setBounds(0,-30,800,600);
        JButton[] jb = new JButton[9];
        int a=56,b=-28,bor=70,bord=bor+10,borb=bor+10;
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                jb[i*3+j]=new JButton();
                jb[i*3+j].setBounds(a+bord,b+borb,bor,bor);
                a+=bord;
                jb[i*3+j].addMouseListener(new Se());
                jp.add(jb[i*3+j],i*3+j);
            }
            a=56;
            b+=borb;
        }
        JButton res = new JButton();
        JButton create = new JButton();
        create.setBounds(37,460,bor,bor);
        res.setBounds(a+70*7-8,b-96,105,105);
        res.addMouseListener(new Se());
        jp.add(res,9);
        jp.add(create,10);
        jp.add(backimg,11);
        this.add(jp);
        this.setVisible(true);
    }
    class Se implements MouseListener {
        static Icon se_ic;
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton jb = (JButton) e.getSource();
            if(e.getButton()==MouseEvent.BUTTON1){
                if(e.getClickCount()==2) {
                    JFileChooser jf = new JFileChooser();
                    jf.showOpenDialog(getParent());
                    if (jf.getSelectedFile() != null) {
                        jb.setIcon(new ImageIcon(new ImageIcon(jf.getSelectedFile().getPath()).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
                    }
                } else if (e.getClickCount()==1&&e.isControlDown()) {
                    jb.setIcon(null);
                }
            } else if (e.getButton()==MouseEvent.BUTTON3) {
                if(e.isControlDown()){
                    se_ic=jb.getIcon();
                } else {
                    jb.setIcon(se_ic);
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
