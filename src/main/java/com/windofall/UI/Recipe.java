package com.windofall.UI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Recipe extends JFrame {
    private String[] items = new String[10];
    private int count = 1;
    JButton[] jb = new JButton[10];
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
        JButton create = new JButton();
        JButton coutrl = new JButton();
        coutrl.setText(""+count);
        create.setText("create");
        coutrl.addMouseListener(new Cou());
        create.addMouseListener(new Cre());
        coutrl.setBounds(118,460,bor,bor);
        create.setBounds(37,460,bor,bor);
        jb[9]=new JButton();
        jb[9].setBounds(a+70*7-8,b-96,105,105);
        jb[9].addMouseListener(new Se());
        jp.add(jb[9],9);
        jp.add(create,10);
        jp.add(coutrl,11);
        jp.add(backimg,12);
        this.add(jp);
        this.setVisible(true);
    }
    class Se implements MouseListener {
        static Icon se_ic;
        static String se_id;
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton jb0 = (JButton) e.getSource();
            if(e.getButton()==MouseEvent.BUTTON1){
                if(e.getClickCount()==2) {
                    JFileChooser jf = new JFileChooser();
                    FileNameExtensionFilter fef=new FileNameExtensionFilter("重置贴图文件(*.png)","png");
                    jf.setFileFilter(fef);
                    jf.showOpenDialog(getParent());
                    if (jf.getSelectedFile() != null) {
                        jb0.setIcon(new ImageIcon(new ImageIcon(jf.getSelectedFile().getPath()).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
                        for(int m=0;m<10;m++){
                            if(jb[m]==jb0){
                                items[m]=(jf.getSelectedFile().getName()+"\b\b\b\b").replace("@",":");
                            }
                        }
                    }
                } else if (e.getClickCount()==1&&e.isControlDown()) {
                    jb0.setIcon(null);
                    for(int m=0;m<10;m++){
                        if(jb[m]==jb0){
                            items[m]=null;
                        }
                    }
                }
            } else if (e.getButton()==MouseEvent.BUTTON3) {
                if(e.isControlDown()){
                    se_ic=jb0.getIcon();
                    for(int m=0;m<10;m++){
                        if(jb[m]==jb0){
                            se_id=items[m];
                        }
                    }
                } else {
                    jb0.setIcon(se_ic);
                    for(int m=0;m<10;m++){
                        if(jb[m]==jb0){
                            items[m]=se_id;
                        }
                    }
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
    class Cre implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String c="{\n    \"type\": \"minecraft:crafting_shaped\",\n    \"pattern\":[\n        \"";
            String[] mark =new String[9];
            for (int x=0;x<9;x++){
                if (items[x]!=null){
                    for (int y=0;y<9;y++){
                        if (mark[y]!=null){
                            if(items[x].equals(mark[y])){
                                c+=y;
                                break;
                            }
                        }else{
                            mark[y]=items[x];
                            c+=y;
                            break;
                        }
                    }
                }else{
                    c+=" ";
                }
                if(x==2||x==5){
                    c+="\",\n        \"";
                } else if (x==8) {
                    c+="\"\n    ],\n    \"key\":{\n";
                }
            }
            if(mark[0]!=null) {
                for (int z = 0; z < 9; z++) {
                    if (mark[z] != null) {
                        if (z != 0) {
                            c += "\n";
                        }
                        c += "        \"" + z + "\":{\n            \"item\": \"" + mark[z] + "\"\n        },";
                    } else {
                        break;
                    }
                }
                c+="\b";
            }
            c+="\n    },\n    \"result\":{\n        \"item\":\""+items[9]+"\",\n        \"count\":"+count+"\n    }\n}";
            System.out.println(c);
            JFileChooser jf0=new JFileChooser();
            FileNameExtensionFilter ff = new FileNameExtensionFilter("(*.json)","json");
            jf0.setFileFilter(ff);
            jf0.showSaveDialog(getParent());
            String filn=jf0.getSelectedFile().getName();
            if(filn.indexOf(".json")==-1){
                filn+=".json";
            }
            try {
                File file = new File(jf0.getCurrentDirectory(),filn);
                FileOutputStream fs = new FileOutputStream(file);
                byte[] bytes = c.getBytes();
                fs.write(bytes);
                fs.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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
    class Cou implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton()==MouseEvent.BUTTON1&&count<64){
                count+=1;
                if(e.isControlDown()){count=64;}
                ((JButton)e.getSource()).setText(""+count);
            } else if (e.getButton()==MouseEvent.BUTTON3&&count>1) {
                count-=1;
                if(e.isControlDown()){count=1;}
                ((JButton)e.getSource()).setText(""+count);
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
