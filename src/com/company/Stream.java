package com.company;

import java.util.Map;

public class Stream {
    String name;
    int sectionCount;
    Map<String, Integer> lectureCount;
    Section[] sections;

    public Stream(String name, int sectionCount, Map<String, Integer> lectureCount) {
        this.name = name;
        this.sectionCount = sectionCount;
        this.sections = new Section[sectionCount];
        this.lectureCount = lectureCount;
        for (Integer i = 0; i<sectionCount; i++){
            this.sections[i] = new Section(i+1, name);
        }
    }
}
