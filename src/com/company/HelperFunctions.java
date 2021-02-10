package com.company;

import java.io.*;
import java.util.*;

public class HelperFunctions {
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
        Map<String, String[]> streamlist = new HashMap<>();
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
        return temp.exists();
    }

    public static boolean isFileEmpty(String filename){
        File file = new File(filename);
        return (file.length() == 0);
    }

    public static Map<String, String[]> readFile(String filename) {
        Map<String, String[]> streams = new HashMap<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                String[] subjects = parts[1].substring(1, parts[1].length()-1).split(",");
                streams.put(parts[0], subjects);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return streams;
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

    public static Map<String, Integer> getSectionCount(){
        Map<String, Integer> sectionCount = new HashMap<>();
        InputStream in = System.in;
        try{
            System.setIn(new FileInputStream(new File("section_count.txt")));
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Reading the number of Science sections.");
        sectionCount.put("science", scanner.nextInt());
        System.out.println("Reading the number of Commerce sections.");
        sectionCount.put("commerce", scanner.nextInt());
        System.out.println("Reading the number of Arts sections.");
        sectionCount.put("arts", scanner.nextInt());
        System.setIn(in);
        return sectionCount;
    }

    public static Map<String, Map<String, Integer>> getLectureCount(Map<String, String[]> streamData){
        InputStream in = System.in;
        try{
            System.setIn(new FileInputStream(new File("lecture_count.txt")));
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, Integer>> lectureCount = new HashMap<>();
        for(String entry : streamData.keySet()){
            Map<String, Integer> counter = new HashMap<>();
            System.out.println("Getting lecture count for subjects. " + entry);
            String[] list = streamData.get(entry);
            boolean isDataInvalid = true, isNegative = true;
            while (isDataInvalid || isNegative){
                Integer hours, totalHours = 0;
                isNegative = false;
                isDataInvalid = true;
                for (String i : list){
                    hours = scanner.nextInt();
                    if (hours <= 0) isNegative = true;
                    counter.put(i, hours);
                    totalHours += hours;
                }
                if (totalHours == 36){
                    isDataInvalid = false;
                }
                else System.out.println("Total hours must be 36, try again.\n");
                if (isNegative) System.out.println("Non-positive value detected, try again.\n");
            }
            lectureCount.put(entry, counter);
        }
        System.setIn(in);
        System.out.println("Lecture Count successfully read.");
        return lectureCount;
    }

    public static Batch getData(Map<String, String[]> streamData, Map<String, Integer> subjectsList){
        boolean isDataInvalid = true;
        while (isDataInvalid){
            isDataInvalid = false;
            Map<String, Integer> subjectList = new HashMap<>(subjectsList);
            Map<String, Integer> sectionCount = getSectionCount();
            Map<String, Map<String, Integer>> lectureCount = getLectureCount(streamData);
            for (Map.Entry<String, Integer> entry : sectionCount.entrySet()){
                Integer multiplier = entry.getValue();
                Map<String, Integer> count = lectureCount.get(entry.getKey());
                for (Map.Entry<String, Integer> subject : count.entrySet()){
                    Integer current = subjectList.get(subject.getKey());
                    current += multiplier * subject.getValue();
                    subjectList.put(subject.getKey(), current);
                }   
            }
            for (String subject : subjectList.keySet()){
                System.out.printf("Hours for %s: %d\n", subject, subjectList.get(subject));
                if (subjectList.get(subject) > 36) {
                    System.out.println("36 hours exceeded, try again.");
                    isDataInvalid = true;
                }
            }

            if (!isDataInvalid){
                Map<String, Subject> subjectObjects = new HashMap<>();
                Map<String, Stream> streamObjects = new HashMap<>();
                for (String stream : streamData.keySet()){
                    Stream newStream = new Stream(stream, sectionCount.get(stream), lectureCount.get(stream));
                    streamObjects.put(stream, newStream);
                }
                Integer i = 0;
                for (String subject : subjectList.keySet()){
                    subjectObjects.put(subject, new Subject(subject, subjectList.get(subject)));
                }
                Batch batch = new Batch(streamObjects, subjectObjects);
                return batch;
            }
        }
        return null;
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}

