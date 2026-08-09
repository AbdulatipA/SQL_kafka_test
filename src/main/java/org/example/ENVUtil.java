package org.example;

public class ENVUtil {

    public static String getEnvByName(String envName, String defaultName) {
        String envVariable = System.getenv(envName);

        if (envVariable != null && !envName.isBlank()) {
            return envVariable;
        } else {
            return defaultName;
        }
    }
}
