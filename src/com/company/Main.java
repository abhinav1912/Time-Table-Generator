package com.company;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Time Table Generator");
        System.out.println("");
        System.out.println("Checking if stream data exists.");
        String streamFile = "streams.txt";
        boolean fileExists = FileHelper.checkFileExistence(streamFile);

        if (!fileExists){
            System.out.println("streams.txt not found, attempting initialisation. \n");
            boolean fileCreated = FileHelper.createFile(streamFile);
            if (!fileCreated){
                System.out.println("Terminating execution.");
                return;
            }
            else {
                System.out.println(streamFile+" successfully created.");
            }
        }
        else {
            System.out.println("File found, skipping initialisation.");
        }

        System.out.println("Attempting Initialisation.");
        Map<String, String[]> streams = FileHelper.initialiseStreamList();
        boolean success = FileHelper.writeFile(streamFile, streams);
        if (success) {
            System.out.println("Success.");
        }
        else {
            System.out.println("Fail");
        }

    }
}