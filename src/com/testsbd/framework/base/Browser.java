package com.testsbd.framework.base;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class Browser
{
    private WebDriver _driver;

    public Browser(WebDriver driver) {
        this._driver = driver;
    }

    public BrowserType Type;

    public void GoToUrl(String Url)
    {
        _driver.get(Url);
    }

    public void Maximize()
    {
        _driver.manage().window().maximize();
    }

    public void JScriptExecute(String btnID)
    {
        JavascriptExecutor JSE = (JavascriptExecutor) _driver;
        JSE.executeScript("document.getElementById('" + btnID + "').click();");
    }

    public String CaptureScreenshot(String ScenarioName){
        File Source = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);

        File directory = new File("C:/Automation/Screenshots/");
        if(!directory.exists())
        {
            directory.mkdir();
        }

        try
        {
            String FileName = ScenarioName + "_Failed_" + System.currentTimeMillis();
            FileUtils.copyFile(Source, new File("C:\\Automation\\Screenshots\\"  + FileName + ".png"));
            return FileName;
        }catch (IOException E)
        {

        }
        return null;
    }
}
