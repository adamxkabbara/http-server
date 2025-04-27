package com.adamkabbara.httpserver;

import com.adamkabbara.httpserver.config.Configuration;
import com.adamkabbara.httpserver.config.ConfigurationManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("Server starting ...");
        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/http.json");

        Configuration currentConfig = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Port: " + currentConfig.getPort());
        System.out.println("webroot: " + currentConfig.getWebroot());
    }
}