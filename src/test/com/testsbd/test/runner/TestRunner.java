package com.testsbd.test.runner;

import com.testsbd.framework.utilities.ExcelUtility;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;

/**
 * Created by Ranjani.Ilango on 7/06/2017.
 */

@CucumberOptions(
        features = {"src/test/com/testsbd/test/features/"},
        glue = {"com/testsbd/test/steps"},
        plugin = {"html:target/cucumber-html-report"},
        tags = {"@LaunchABD, @FlightClosed, @Quit"})

public class TestRunner extends AbstractTestNGCucumberTests
{

    @BeforeClass(alwaysRun = true)
    public static void BeforeClass()
    {
        ExcelUtility ExcelUtil = new ExcelUtility();

    }
}
