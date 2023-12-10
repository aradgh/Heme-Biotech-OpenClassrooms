package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;

/**
 * Write the symptoms analytics results in a file
 */
public interface ISymptomWriter {
	public void writeSymptoms(Map<String, Integer> symptoms) throws IOException;
}
