package com.company;

import java.util.Scanner;

public class Subject {
    int lectureCount;
    String name;
    Section[][] schedule;

    public Subject(String name, int lectureCount) {
        this.name = name;
        this.lectureCount = lectureCount;
        this.schedule = new Section[6][6];
    }

    public String getName() {
        return name;
    }

    public Section[][] getSchedule() {
        return schedule;
    }

    public Integer[] getFreeSlot(){
        Integer i=0,j=0;
        for (j=0; j<6; j++){
            for (i=0; i<6; i++){
                if (this.schedule[i][j] == null){
                    break;
                }
            }
        }
        return new Integer[]{i, j};
    }
}
