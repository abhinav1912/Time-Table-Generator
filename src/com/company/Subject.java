package com.company;

import java.util.Random;
import java.util.Scanner;

public class Subject {
    public int lectureCount;
    public String name;
    public Section[][] schedule;

    public Subject(String name, int lectureCount) {
        this.name = name;
        this.lectureCount = lectureCount;
        this.schedule = new Section[6][6];
        for (Integer i = 0; i<6; i++){
            for (Integer j =0; j<6; j++){
                schedule[i][j] = null;
            }
        }
    }

    public String getName() {
        return name;
    }

    public Section[][] getSchedule() {
        return schedule;
    }

    public void getFreeSlot(Section section){
        Integer i=0,j=0,k=0;
        Random rand = new Random();
        while (k<100){
            k += 1;
            i = rand.nextInt(6);
            j = rand.nextInt(6);
            if (this.schedule[i][j] == null && (section.schedule[i][j] == null)){
                this.schedule[i][j] = section;
                section.schedule[i][j] = this;
                return;
            }
        }
        for (j=0; j<6; j++){
            for (i=0; i<6; i++){
                if (this.schedule[i][j] == null && (section.schedule[i][j] == null)){
                    this.schedule[i][j] = section;
                    section.schedule[i][j] = this;
                    return;
                }
            }
        }
        return;
    }

    public void printSchedule(){
        for (Integer j=0; j<6; j++){
            for (Integer i=0; i<6; i++){
                if (schedule[j][i]==null){
                    System.out.printf("null ");
                }
                else System.out.printf("%s %d ", schedule[j][i].stream, schedule[j][i]._id);
            }
            System.out.println();
        }
    }
}
