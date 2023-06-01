package com.windofall.UI;

import com.windofall.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Recipe extends JFrame {
    private String[] items = new String[10];
    private int count = 1;
    private JButton[] jb = new JButton[10];

    public Recipe(){
        JButton[] fjb = new JButton[10];
        int a=56,b=-28,bor=70,bord=bor+10,borb=bor+10;
        int fbp=37,fbpa=81;
        int indexmark=10;
        this.setResizable(false);
        this.setTitle("合成表添加");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setLayout(null);
        JLabel backimg = new JLabel();
        Image image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/assets/crafting.png"))).getImage().getScaledInstance(786,590,Image.SCALE_DEFAULT);
        backimg.setIcon(new ImageIcon(image));
        backimg.setBounds(0,-30,800,600);
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
        jb[9]=new JButton();
        jb[9].setBounds(a+70*7-8,b-96,105,105);
        jb[9].addMouseListener(new Se());
        fjb[0] = new JButton();
        fjb[1] = new JButton();
        fjb[2] = new JButton();
        fjb[0].setText("create");
        fjb[1].setText(""+count);
        fjb[2].setText("UnP");
        fjb[0].addMouseListener(new Cre());
        fjb[1].addMouseListener(new Cou());
        fjb[2].addMouseListener(new UnP());
        fjb[0].setBounds(fbp,460,bor,bor);
        fjb[1].setBounds(fbp+fbpa,460,bor,bor);
        fjb[2].setBounds(fbp+fbpa*2,460,bor,bor);
        jp.add(jb[9],9);
        for (JButton jButton : fjb) {
            if (jButton != null) {
                jp.add(jButton, indexmark++);
            } else {
                break;
            }
        }
        jp.add(backimg,indexmark);
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
                    JFileChooser jf = new JFileChooser(Main.path+"\\MCDPH\\item\\");
                    FileNameExtensionFilter fef=new FileNameExtensionFilter("重置贴图文件(*.png)","png");
                    jf.setFileFilter(fef);
                    jf.showOpenDialog(getParent());
                    if (jf.getSelectedFile() != null) {
                        jb0.setIcon(new ImageIcon(new ImageIcon(jf.getSelectedFile().getPath()).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
                        for(int m=0;m<10;m++){
                            if(jb[m]==jb0){
                                items[m]=jf.getSelectedFile().getName().replace(".png","").replace("@",":");
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
                c=c.substring(0,c.length()-1);
            }
            c+="\n    },\n    \"result\":{\n        \"item\":\""+items[9]+"\",\n        \"count\":"+count+"\n    }\n}";
            System.out.println(c);
            JFileChooser jf0=new JFileChooser();
            FileNameExtensionFilter ff = new FileNameExtensionFilter("(*.json)","json");
            jf0.setFileFilter(ff);
            jf0.showSaveDialog(getParent());
            String filn=jf0.getSelectedFile().getName();
            if(!filn.contains(".json")){
                filn+=".json";
            }
            try {
                File file = new File(jf0.getCurrentDirectory(),filn);
                FileOutputStream fs = new FileOutputStream(file);
                byte[] bytes = c.getBytes();
                fs.write(bytes);
                fs.close();
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
    class UnP implements MouseListener{
        String modId ="";
        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser jf=new JFileChooser();
            FileNameExtensionFilter ff = new FileNameExtensionFilter("mod文件(*jar)","jar");
            jf.setFileFilter(ff);
            jf.showOpenDialog(getParent());

            try {
                JarFile jarf = new JarFile(jf.getSelectedFile());
                for(Enumeration<JarEntry> enums = jarf.entries();enums.hasMoreElements();){
                    JarEntry jare = enums.nextElement();
                    String filn=jare.getName();
                    if (filn.contains("assets/")&&filn.endsWith("/textures/")){
                        filn=filn.replace("assets/","");
                        modId=filn.replace("/textures/","");
                    }
                    if(filn.contains("assets/")&&filn.contains("/textures/item/")&&filn.endsWith(".png")){
                        filn=filn.replace("assets/","");
                        filn=filn.replace(modId,"");
                        filn=filn.replace("/textures/item/","");
                        filn=filn.replace("/","_");
                        filn=modId+"@"+filn;
                        InputStream imgd = jarf.getInputStream(jare);
                        File imgf = new File(Main.path+"\\MCDPH\\item\\"+filn);
                        FileOutputStream imgout = new FileOutputStream(imgf);
                        while(imgd.available()>0){
                            imgout.write(imgd.read());
                        }
                        imgd.close();
                        imgout.close();
                    }
                }
                System.out.println("资源提取完毕");
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
}
