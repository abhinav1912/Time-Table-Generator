package com.company;

import java.util.Map;

public class Batch {
    Map<String, Stream> streamObjects;
    Map<String, Subject> subjectObjects;

    public Batch(Map<String, Stream> streamObjects, Map<String, Subject> subjectObjects) {
        this.streamObjects = streamObjects;
        this.subjectObjects = subjectObjects;
    }

    public Map<String, Stream> getStreamObjects() {
        return streamObjects;
    }

    public Map<String, Subject> getSubjectObjects() {
        return subjectObjects;
    }
}
