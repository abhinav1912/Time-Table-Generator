package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String[]> initialiseStreamList(){
        Map<String, String[]> streamlist = new HashMap<String, String[]>();
        String[] science = new String[] {"English", "Math", "Physics", "Chemistry", "CS"};
        String[] commerce = new String[] {"English", "Math", "Accounts", "Economics", "Business"};
        String[] arts = new String[] {"English", "Politics", "History", "Economics", "Psychology"};
        streamlist.put("science", science);
        streamlist.put("commerce", commerce);
        streamlist.put("arts", arts);
        return streamlist;
    }

    public static boolean checkFileExistence(String filename){
        File temp = new File(filename);
        if (temp.exists()) return true;
        else return false;
    }

    public static String readFile(String filename){
        // process
        return "";
    }

    public static boolean writeFile(String filename, Map<String, String[]> data){
        File file = new File(filename);
        BufferedWriter bf = null;
        boolean success = true;

        try {
            bf = new BufferedWriter(new FileWriter(file));
            for(Map.Entry<String, String[]> entry : data.entrySet()){
                bf.write(entry.getKey()+":"+Arrays.toString(entry.getValue()));
                bf.newLine();
            }

            bf.flush();
        }catch (IOException e){
            e.printStackTrace();
            success = false;
        }finally {
            try{
                bf.close();
            }catch (Exception e){}
        }
        return success;
    }
}

