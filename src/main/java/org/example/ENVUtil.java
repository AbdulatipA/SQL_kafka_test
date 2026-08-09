package org.example;

public class ENVUtil {

    public static String getEnvByName(String envName) {
        String envVariable = System.getenv(envName);

        if (envVariable != null && !envName.isBlank()) {
            return envVariable;
        }

        throw new RuntimeException("is not envName");
    }
}
