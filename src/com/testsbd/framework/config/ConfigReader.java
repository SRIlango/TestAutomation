package com.testsbd.framework.config;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ranjani.Ilango on 21/06/2017.
 */
public class ConfigReader
{
    public static void PopulateSetiings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadFrameworkProperties();
    }

    private void ReadFrameworkProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("FrameworkConfig.properties"));

        FrameworkSettings.ConnectionString = properties.getProperty("ConnectionString");
        FrameworkSettings.LogFilePath = properties.getProperty("Logs");
        FrameworkSettings.ExcelFilePath = properties.getProperty("Excel");
        FrameworkSettings.RelocInputFilePath = properties.getProperty("RelocInput");
        FrameworkSettings.KioskLogsFilePath = properties.getProperty("KioskLogs");
    }
}
