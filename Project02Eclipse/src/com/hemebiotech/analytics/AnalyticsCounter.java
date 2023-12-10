package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	private static int headacheCount = 0;
	private static int rashCount = 0;
	private static int pupilCount = 0;

	private static ReadSymptomDataFromFile reader;
	private static WriteSymptomDataToFile writer;

	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		reader = reader;
		writer = writer;
	}

	public List<String> getSymptoms() {
		return reader.getSymptoms();
	}

	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		HashMap<String, Integer> symptomsCount = new HashMap<>();

		for (String symptom : symptoms) {
			if(symptomsCount.containsKey(symptom)) {
				int count = symptomsCount.get(symptom);
				symptomsCount.put(symptom, count + 1);
			} else {
				symptomsCount.put(symptom, 1);
			}
		}

		return symptomsCount;
	}

	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		// On converti la HashMap en TreeMap car une TreeMap tri automatiquement les clés par ordre alphabétique
        return new TreeMap<>(symptoms);
	}

	public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
		writer.writeSymptoms(symptoms);
	}

	public static void main(String args[]) throws Exception {
		// first get input
		BufferedReader reader = new BufferedReader (new FileReader("Project02Eclipse/symptoms.txt"));
		String line = reader.readLine();

		int i = 0;
		int headCount = 0;
		while (line != null) {
			i++;
			System.out.println("symptom from file: " + line);
			if (line.equals("headache")) {
				headCount++;
				System.out.println("number of headaches: " + headCount);
			}
			else if (line.equals("rush")) {
				rashCount++;
			}
			else if (line.contains("pupils")) {
				pupilCount++;
			}

			line = reader.readLine();	// get another symptom
		}
		
		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		writer.write("headache: " + headacheCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("dialated pupils: " + pupilCount + "\n");
		writer.close();
	}
}
