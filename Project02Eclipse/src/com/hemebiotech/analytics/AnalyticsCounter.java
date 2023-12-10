package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	public static ISymptomReader reader;
	public static ISymptomWriter writer;

	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		AnalyticsCounter.reader = reader;
		AnalyticsCounter.writer = writer;
	}

	public List<String> getSymptoms() {
		return reader.getSymptoms();
	}

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

	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		// On converti la HashMap en TreeMap car une TreeMap tri automatiquement les clés par ordre alphabétique
        return new TreeMap<>(symptoms);
	}

	public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {
		writer.writeSymptoms(symptoms);
	}
}
