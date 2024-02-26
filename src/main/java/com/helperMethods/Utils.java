package com.helperMethods;

import java.io.InputStream;
import java.util.ArrayList;

public class Utils {
    
    public static int[][] arrayListTo2DInt(ArrayList<Integer> list,int ySize, int xSize){

        int[][] newArr=new int[ySize][xSize];

        for (int j = 0; j < newArr.length; j++) {
            for (int i = 0; i < newArr[j].length; i++) {
                int index=j*ySize+i;

                newArr[j][i]=list.get(index);
            }
        }

        return newArr;
    }

    public static int[] get2DIntTo1DInt(int[][] arr2D){
        int[] arr1D=new int[arr2D.length*arr2D[0].length];

        for (int j = 0; j < arr2D.length; j++) {
            for (int i = 0; i < arr2D[j].length; i++) {
                int index=j*arr2D.length+i;

                arr1D[index]=arr2D[j][i];
            }
        }

        return arr1D;
    }

    public static String getStringFromInputstream(InputStream is){

        String path= LoadSave.class.getPackageName();
        System.out.println(path);
        return path;
    }
}
