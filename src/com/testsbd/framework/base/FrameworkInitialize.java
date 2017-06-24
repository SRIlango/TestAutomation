package com.testsbd.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class FrameworkInitialize extends Base
{
    public void InitializeBrowser(BrowserType Type)
    {
        WebDriver driver = null;
        switch (Type) {
            case Chrome:
                System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case Firefox:
                System.setProperty("webdriver.gecko.driver", "C:\\Automation\\SeleniumDrivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case IE:
                break;
        }
        DriverContext.setDriver(driver);
        DriverContext.Browser = new Browser(driver);
    }
}
