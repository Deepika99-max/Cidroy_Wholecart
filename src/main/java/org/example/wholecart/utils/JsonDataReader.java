package org.example.wholecart.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.wholecart.constants.FrameworkConstants;

import java.io.InputStream;

public final class JsonDataReader {

    private static JsonNode rootNode;

    private JsonDataReader() {
    }

    static {
        loadJsonData();
    }

    private static void loadJsonData() {

        try (InputStream inputStream =
                     JsonDataReader.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     FrameworkConstants.TEST_DATA_FILE
                             )) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "Test data file not found: "
                                + FrameworkConstants.TEST_DATA_FILE
                );
            }

            ObjectMapper objectMapper = new ObjectMapper();

            rootNode = objectMapper.readTree(inputStream);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to load test data.",
                    e
            );
        }
    }

    public static String getValue(String path) {

        JsonNode node = rootNode;

        for (String field : path.split("\\.")) {
            node = node.path(field);
        }

        if (node.isMissingNode()) {
            throw new RuntimeException(
                    "Test data not found for path: " + path
            );
        }

        return node.asText();
    }
}