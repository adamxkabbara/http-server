package com.adamkabbara.httpserver.config;

import com.adamkabbara.httpserver.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ConfigurationManager is responsible for loading http configuration data
 */
public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myConfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    /**
     * Load http server configuration from file
     * @param filePath relative location of http server configuration
     */
    public void loadConfiguration(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;

        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error Parsing the Configuration File");
        }
        try {
            myConfiguration = Json.fromJson(jsonNode, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error Parsing the Configuration File, Internal");
        }
    }

    /**
     * Gets the current configuration.
     * @return Current configuration
     */
    public Configuration getCurrentConfiguration() {
        if (myConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return myConfiguration;
    }
}
