package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Read a symptoms.txt file, and convert it into a results.out file
 * listing all the unique symptoms with their number of occurrence
 */
public class AnalyticsCounter {
	private final ISymptomReader reader;
	private final ISymptomWriter writer;

	/**
	 * AnalyticsCounter constructor
	 * @param reader a ISymptomReader object to read the symptoms.txt file
	 * @param writer a ISymptomWriter object to write in a new file results.out
	 */
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	/**
	 * Convert symptoms.txt file into a list of symptoms
	 * @return List<String>
	 */
	public List<String> getSymptoms() {
		return reader.getSymptoms();
	}

	/**
	 * Convert the list of symptoms into a Map where, for every unique symptoms,
	 * in contains a set <symptom, number of occurrence in symptoms.txt>
	 * @param symptoms List of all the symptoms in symptoms.txt.
	 *                 Note: This list contains all the occurrence of every symptom in the same order as in symptoms.txt
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomsCount = new HashMap<>();

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

	/**
	 * To sort the symptoms in alphabetical order, we convert the HashMap
	 * into a TreeMap because a TreeMap automatically sorts the keys in alphabetical order
	 * @param symptoms a Map of every unique symptoms with this set format <symptom, number of occurrence in symptoms.txt>
	 *                 in the order of appearance in symptoms.txt
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
        return new TreeMap<>(symptoms);
	}

	/**
	 * Create a file results.out listing all the unique symptoms in symptoms.txt file
	 * with their number of occurrence
	 * @param symptoms a Map of every unique symptoms with this set format <symptom, number of occurrence in symptoms.txt>
	 * 	               in alphabetical order
	 * @throws IOException if a problem occurs with the creation of the results.out file
	 */
	public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
		writer.writeSymptoms(symptoms);
	}
}
