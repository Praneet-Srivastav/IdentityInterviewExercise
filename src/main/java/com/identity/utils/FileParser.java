package com.identity.utils;

import com.identity.model.CarDetails;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileParser is responsible for reading and parsing files containing
 * registration numbers and car details.
 * It provides functionalities to extract registration numbers and
 * parse car details from specified file paths.
 */
public class FileParser {
    private static final Pattern REG_PATTERN = Pattern.compile("[A-Z]{2}\\d{2}\\s?[A-Z]{3}|[A-Z]{2}\\d{2}\\s?[A-Z]{2}[A-Z]");

    public Set<String> extractRegistrationNumbers(String filePath) throws IOException {
        Set<String> registrations = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = REG_PATTERN.matcher(line);
                while (matcher.find()) {
                    registrations.add(matcher.group().replaceAll("\\s", ""));
                }
            }
        }
        return registrations;
    }

    /**
     * Parses the specified file to extract car details and returns a map
     * containing the registration number as the key and corresponding
     * car details as the value.
     */
    public Map<String, CarDetails> parseOutputFile(String filePath) throws IOException {
        Map<String, CarDetails> carDetailsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    CarDetails car = new CarDetails(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            Integer.parseInt(parts[3].trim())
                    );
                    carDetailsMap.put(car.getRegistrationNumber(), car);
                }
            }
        }
        return carDetailsMap;
    }
}