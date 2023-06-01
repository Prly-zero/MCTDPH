package com.windofall;

import com.windofall.UI.Recipe;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    static File directory = new File(".");
    public static String path;
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            path = directory.getCanonicalPath();
            File f= new File(path+"\\MCDPH\\item\\");
            if(!f.exists()){f.mkdirs();}
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Recipe();
    }
}