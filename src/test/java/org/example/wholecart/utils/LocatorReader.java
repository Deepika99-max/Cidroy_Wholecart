package org.example.wholecart.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class LocatorReader {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper();

    private static final Map<String, JsonNode> LOCATOR_CACHE =
            new ConcurrentHashMap<>();

    private LocatorReader() {
    }

    public static By get(String locatorFile, String locatorName) {

        JsonNode locatorJson =
                loadLocatorFile(locatorFile);

        JsonNode locatorValue =
                locatorJson.get(locatorName);

        if (locatorValue == null || locatorValue.asText().isBlank()) {

            throw new IllegalArgumentException(
                    "Locator '" + locatorName
                            + "' not found in "
                            + locatorFile
            );
        }

        return By.xpath(locatorValue.asText());
    }

    private static JsonNode loadLocatorFile(
            String locatorFile) {

        return LOCATOR_CACHE.computeIfAbsent(
                locatorFile,
                LocatorReader::readLocatorFile
        );
    }

    private static JsonNode readLocatorFile(
            String locatorFile) {

        String resourcePath =
                "locators/" + locatorFile;

        try (InputStream inputStream =
                     LocatorReader.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     resourcePath
                             )) {

            if (inputStream == null) {

                throw new RuntimeException(
                        "Locator file not found: "
                                + resourcePath
                );
            }

            return OBJECT_MAPPER.readTree(
                    inputStream
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read locator file: "
                            + resourcePath,
                    e
            );
        }
    }
}