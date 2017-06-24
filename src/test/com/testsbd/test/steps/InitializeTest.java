package com.testsbd.test.steps;

import com.testsbd.framework.base.BrowserType;
import com.testsbd.framework.base.DriverContext;
import com.testsbd.framework.base.FrameworkInitialize;
import com.testsbd.framework.utilities.DatabaseUtility;
import com.testsbd.framework.utilities.ExcelUtility;
import com.testsbd.framework.utilities.LogUtility;
import com.testsbd.framework.utilities.NotepadUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class InitializeTest extends FrameworkInitialize
{
    public static boolean Initialized = false;

    LogUtility LogUtil = new LogUtility();
    ExcelUtility ExcelUtil = new ExcelUtility();
    NotepadUtility NotepadUtil = new NotepadUtility();
    DatabaseUtility DatabaseUtil = new DatabaseUtility();

    @Before
    public void Initialize (Scenario scenario) throws IOException
    {
        if (!Initialized)
        {
            DatabaseUtil.InitiateConnection();
            NotepadUtil.ReadInput();
            InitializeBrowser(BrowserType.Chrome);
            DriverContext.Browser.Maximize();
            Initialized = true;
            LogUtil.logMessages("I", "Framework Initialized", this.getClass().getName());
            ExcelUtil.Open();
            ExcelUtil.setHeaders();
        }

        LogUtil.logMessages("I", "Scenario Name: " + scenario.getName(), this.getClass().getName());
    }

    @After
    public void AfterScenario(Scenario scenario) throws IOException {
        boolean scenarioPassed = true;
        String failScreenshotName = null;

        if(scenario.isFailed())
        {
            failScreenshotName = DriverContext.Browser.CaptureScreenshot(scenario.getName());
            scenarioPassed = false;
        }
        ExcelUtil.UpdateResults(scenario.getName(), scenarioPassed, failScreenshotName);
    }

    @After("@Quit")
    public void AfterAll() throws IOException {
        ExcelUtil.CloseFile("C:\\Automation\\ExcelData\\JQ_ABD_CUSS.xlsx");
    }
}
