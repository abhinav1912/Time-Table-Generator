package com.company;

import java.io.File;
import java.io.IOException;

public class FileHelper {
    public static boolean createFile(String filename){
        try {
            File myObj =  new File(filename);
            myObj.createNewFile();
            return true;
        } catch (IOException e){
            System.out.println("Error Occurred");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkFile(String filename){
        File temp = new File(filename);
        if (temp.exists()) return true;
        else return false;
    }

    public static String readFile(String filename){
        // process
        return "";
    }

    public static boolean writeFile(String filename, String data){
        // process
        return true;
    }
}
