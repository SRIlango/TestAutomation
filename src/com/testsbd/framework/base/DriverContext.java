package com.testsbd.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ranjani.Ilango on 2/06/2017.
 */
public class DriverContext
{

    public static WebDriver Driver;

    public static Browser Browser;

    public static String ScenarioName;

    public static void setDriver(WebDriver driver)
    {
        Driver = driver;
    }
}
