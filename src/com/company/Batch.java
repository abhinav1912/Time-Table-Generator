package com.company;

import java.util.HashMap;
import java.util.Map;

public class Batch {
    public Map<String, Stream> streamObjects;
    public Map<String, Subject> subjectObjects;

    public Batch(Map<String, Stream> streamObjects, Map<String, Subject> subjectObjects) {
        this.streamObjects = new HashMap<>(streamObjects);
        this.subjectObjects = new HashMap<>(subjectObjects);
    }

    public Map<String, Stream> getStreamObjects() {
        return streamObjects;
    }

    public Map<String, Subject> getSubjectObjects() {
        return subjectObjects;
    }
}
