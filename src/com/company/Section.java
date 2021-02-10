package com.company;

public class Section {
    public int _id;
    public String stream;
    public Subject[][] schedule;

    public Section(int id, String stream) {
        this._id = id;
        this.stream = stream;
        this.schedule = new Subject[6][6];
        for (Integer i = 0; i<6; i++){
            for (Integer j =0; j<6; j++){
                schedule[i][j] = null;
            }
        }
    }

    public int getId() {
        return _id;
    }

    public String getStream() {
        return stream;
    }

    public Subject[][] getSchedule() {
        return schedule;
    }

    public void printSchedule(){
        for (Integer j=0; j<6; j++){
            for (Integer i=0; i<6; i++){
                if (schedule[j][i]==null){
                    System.out.printf("null ");
                }
                else System.out.printf("%s ", schedule[j][i].name);
            }
            System.out.println();
        }
    }
}
