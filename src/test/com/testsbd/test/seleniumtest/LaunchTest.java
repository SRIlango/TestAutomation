package com.testsbd.test.seleniumtest;

import com.testsbd.framework.base.DriverContext;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Ranjani.Ilango on 2/06/2017.
 */
public class LaunchTest
{
    @Before
    public void Initialize()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\Automation\\SeleniumDrivers\\geckodriver.exe");
        DriverContext.Driver = new FirefoxDriver();
        DriverContext.Driver.manage().window().maximize();
        DriverContext.Driver.navigate().to("http://192.168.20.18/vBagDropV2/Home/Index/TT/MEL/T4");
    }
    @Test
    public void LaunchDriver()
    {

    }
}
