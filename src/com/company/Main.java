package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static Map<String, String[]> loadData(String streamFile){
        System.out.println("Checking if stream data exists.");

        // checking if streamFile exists
        boolean fileExists = HelperFunctions.checkFileExistence(streamFile);

        if (!fileExists){
            // create a new file
            System.out.println(streamFile+" not found, creating new file. \n");
            boolean fileCreated = HelperFunctions.createFile(streamFile);
            if (!fileCreated){
                // exiting on file creation error
                System.out.println("Terminating execution.");
                return null;
            }
            else {
                System.out.println(streamFile+" successfully created.");
            }
        }

        // check if file contains data or not
        boolean isFileEmpty = HelperFunctions.isFileEmpty(streamFile);

        if (isFileEmpty){
            System.out.println("Empty file, attempting initialisation");
            // fetch a map of all streams
            Map<String, String[]> streamData = HelperFunctions.initialiseStreamList();
            // write stream data to file
            HelperFunctions.writeFile(streamFile, streamData);
            boolean success = HelperFunctions.writeFile(streamFile, streamData);
            if (success) {
                System.out.println("Stream successfully saved to file.");
                return streamData;
            }
            else {
                System.out.println("Terminating execution");
                return null;
            }
        }
        else {
            // loading stream data from file
            Map<String, String[]> streamData = HelperFunctions.readFile(streamFile);
            System.out.println("Data successfully read from file.");
            return streamData;
        }
    }


    public static void main(String[] args) {
        System.out.println("Time Table Generator\n");
        Map<String, String[]> streamData = loadData("streams.txt");

        // create a count list for all subjects/teachers
        Map<String, Integer> subjectList = new HashMap<>();
        for (Map.Entry<String, String[]> entry : streamData.entrySet()){
            for (String i : entry.getValue()){
                subjectList.put(i, 0);
            }
        }

        Batch batch = HelperFunctions.getData(streamData, subjectList);
        Map<String, Subject> subjectObjects = batch.getSubjectObjects();
//        Section test = new Section(66, "test");
//        subjectObjects.get("English").getFreeSlot(test);
//        subjectObjects.get("English").getFreeSlot(test);
//        subjectObjects.get("English").printSchedule();
        Map<String, Stream> streamObjects = new HashMap<>(batch.getStreamObjects());
        for (String key : streamObjects.keySet()){
            Stream stream = streamObjects.get(key);
            for (String subject : stream.lectureCount.keySet()){
                Integer count = stream.lectureCount.get(subject);
                for (Section section : stream.sections.values()){
                    for (Integer i =0; i<count; i++){
                        subjectObjects.get(subject).getFreeSlot(section);
                    }
                }
            }
        }

        for (Subject subject : subjectObjects.values()){
            System.out.println(subject.name);
            subject.printSchedule();
            System.out.println();
        }

        for (String stream : streamObjects.keySet()){
            Stream s = streamObjects.get(stream);
            System.out.println(stream);
            for (Section section : s.sections.values()){
                System.out.println(section._id);
                section.printSchedule();
            }
        }
    }
}