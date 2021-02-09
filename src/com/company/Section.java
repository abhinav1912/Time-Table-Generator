package com.company;

public class Section {
    int id;
    String stream;
    Subject[][] schedule;

    public Section(int id, String stream) {
        this.id = id;
        this.stream = stream;
        this.schedule = new Subject[6][6];
    }

    public int getId() {
        return id;
    }

    public String getStream() {
        return stream;
    }

    public Subject[][] getSchedule() {
        return schedule;
    }
}
