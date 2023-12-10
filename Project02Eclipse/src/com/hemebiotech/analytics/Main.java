package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse/symptoms.txt");
        ISymptomWriter writer = new WriteSymptomDataToFile();
        AnalyticsCounter analyticsCounter = new AnalyticsCounter(reader, writer);

        List<String> symptomsList = analyticsCounter.getSymptoms();
        Map<String, Integer> symptomsCount = analyticsCounter.countSymptoms(symptomsList);
        Map<String, Integer> symptomsSorted = analyticsCounter.sortSymptoms(symptomsCount);
        analyticsCounter.writeSymptoms(symptomsSorted);
    }
}
