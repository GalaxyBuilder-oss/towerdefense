package com.helperMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {

    private static String resourcePath = "src/main/resources/res/";

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream ls = LoadSave.class.getResourceAsStream("/res/sprite.png");

        try {
            img = ImageIO.read(ls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


    // create a new levels from different values &
    // Save 2D int to file
    public static void createLevel(String name, int[] lvl) {
        File newLevel = new File(resourcePath + name);
        if (newLevel.exists()) {
            System.out.println("File : " + name + " already Exist!");
            return;
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writeFile(newLevel, lvl);
        }
    }

    // save level to text
    // Write file
    private static void writeFile(File file, int[] lvl) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (int i : lvl) {
                printWriter.println(i);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLevel(String name, int[][] lvl){

        File lvlFile=new File(resourcePath+name);

        if(lvlFile.exists()){
            writeFile(lvlFile, Utils.get2DIntTo1DInt(lvl));
        }else{
            System.out.println("File : "+name+" Does Not Exists!");
            return;
        }

    }

    // Read file
    // Load int array from file
    public static int[][] getLevelData(String name){
        File lvlFile=new File(resourcePath+name);
        if (lvlFile.exists()) {
            ArrayList<Integer> list = readFile(lvlFile);
            return Utils.arrayListTo2DInt(list, 20, 20);
        }else{
            System.out.println("File :"+name+" Does Not Exist!");
            return null;
        }
    }
    private static ArrayList<Integer> readFile(File file) {
        ArrayList<Integer> list=new ArrayList<>();
        try {
            Scanner inp = new Scanner(file);
            while (inp.hasNextLine()) {
                list.add(Integer.parseInt(inp.nextLine()));
            }
            inp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void tesDirectory(String fileName){
        File file=new File(resourcePath.concat(fileName));
        if(file.exists()){
            System.out.println(file.getAbsolutePath());
            System.out.println("file telah ada");
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
