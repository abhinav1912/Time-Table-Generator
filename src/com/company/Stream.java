package com.company;

import java.util.HashMap;
import java.util.Map;

public class Stream {
    public String name;
    public int sectionCount;
    public Map<String, Integer> lectureCount;
    public Map<Integer, Section> sections;

    public Stream(String name, int sectionCount, Map<String, Integer> lectureCount) {
        this.name = name;
        this.sectionCount = sectionCount;
        this.sections = new HashMap<>();
        this.lectureCount = lectureCount;
        for (Integer i = 0; i<sectionCount; i++){
            this.sections.put(i+1, new Section(i+1, name));
        }
    }
}
