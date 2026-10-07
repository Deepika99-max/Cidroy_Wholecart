package org.example.wholecart.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String CONFIG_FILE =
            "config/config.properties";

    public static final String TEST_DATA_FILE =
            "testdata/testdata.json";

    public static final String SCREENSHOT_DIRECTORY =
            "build/screenshots/";

    public static final int DEFAULT_TIMEOUT = 15;

    public static final int PAGE_LOAD_TIMEOUT = 30;

    public static final String DEFAULT_BROWSER = "chrome";

    public static final String DEFAULT_ENVIRONMENT = "qa";
}