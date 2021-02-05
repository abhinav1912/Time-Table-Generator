package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Time Table Generator");
        System.out.println("");
        System.out.println("Checking if stream data exists.");
        String streamFile = "streams.txt";
        boolean fileExists = FileHelper.checkFile(streamFile);

        if (!fileExists){
            System.out.println("stream.txt not found, attempting initialisation. \n");
            boolean fileCreated = FileHelper.createFile(streamFile);
            if (!fileCreated){
                System.out.println("Terminating execution.");
                return;
            }
        }
        else {
            System.out.println("File found, skipping initialisation.");
        }


    }
}
