package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter{
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
        FileWriter writer = new FileWriter("result.out");
        for (Map.Entry<String, Integer> symptomSet : symptoms.entrySet()) {
            writer.write(symptomSet.getKey() + ":" + symptomSet.getValue() + "\n");
        }
        writer.close();
    }
}
