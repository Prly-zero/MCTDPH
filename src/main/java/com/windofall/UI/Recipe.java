package com.windofall.UI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

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
                jb[i*3+j].addActionListener(new Se());
                jp.add(jb[i*3+j]);
            }
            a=0;
            b+=68;
        }
        JButton res = new JButton();
        res.setBounds(a+68*5,b-68,64,64);
        res.addActionListener(new Se());
        jp.add(res);
        this.add(jp);
        this.setVisible(true);
    }
    class Se implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(getParent());
            JButton jb =(JButton)e.getSource();
            jb.setIcon(new ImageIcon(new ImageIcon(jf.getSelectedFile().getPath()).getImage().getScaledInstance(64,64,Image.SCALE_DEFAULT)));
        }
    }
}
