package com.testsbd.test.steps;

import com.testsbd.framework.base.Base;
import com.testsbd.framework.base.DriverContext;
import com.testsbd.framework.utilities.CucumberUtility;
import com.testsbd.framework.utilities.LogUtility;
import com.testsbd.test.pages.AgentPage;
import com.testsbd.test.pages.ValidationPage;
import com.testsbd.test.pages.PlaceBagPage;
import com.testsbd.test.pages.CheckInProcessPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class ABDSteps extends Base
{
    LogUtility logUtil = new LogUtility();

    @Given("^I set up the correct launch link$")
    public void iSetUpTheCorrectLaunchLink(DataTable table) throws Throwable
    {
        CucumberUtility.ConvertDataTableToDictionary(table);

        String SBDWebUrl = "http://192.168.20.18/vBagDropV2/Home/Index/" + CucumberUtility.GetCellValue("Airline")
                + "/" + CucumberUtility.GetCellValue("Port")
                + "/" + CucumberUtility.GetCellValue("Terminal");
        DriverContext.Browser.GoToUrl(SBDWebUrl);
        CurrentPage = GetInstance(ValidationPage.class);

        try {
            Assert.assertTrue(CurrentPage.As(ValidationPage.class).IsSBDLaunched(), "vBD not loaded successfully");
            logUtil.logMessages("I", "vBD Unit Configured", this.getClass().getName());
        }catch (AssertionError E)
        {
            logUtil.logMessages("E", "vBD Configuration Issue", this.getClass().getName());
            throw E;
        }
    }

    @And("^I initialize the Configurations with Unit Name \"([^\"]*)\"$")
    public void iInitializeTheConfigurationsWithUnitName(String UnitName) throws Throwable
    {
        try {
            Assert.assertEquals(CurrentPage.As(ValidationPage.class).ConfigureABDUnit(UnitName), "Initialize");
        }catch(AssertionError E)
        {
            logUtil.logMessages("E", E.getMessage(), this.getClass().getName());
            throw E;
        }

    }

    @Given("^I am on Boarding Pass scan screen$")
    public void iConfirmBPScanScreen() throws Throwable
    {
        CurrentPage = GetInstance(ValidationPage.class);
        try {
            Assert.assertTrue(CurrentPage.As(ValidationPage.class).ConfirmInBPScreen(), "vBD not in Boarding Pass Scan screen");
        }catch (AssertionError E)
        {
            logUtil.logMessages("E", E.getMessage(), this.getClass().getName());
            throw E;
        }
    }

    @And("^I activate the APP$")
    public void iActivateTheAPP() {
        CurrentPage = GetInstance(PlaceBagPage.class);
        try {
            Assert.assertEquals(CurrentPage.As(PlaceBagPage.class).ActivateAPP(), "Active");
        } catch(AssertionError E)
        {
            logUtil.logMessages("E", E.getMessage(), this.getClass().getName());
            throw E;
        } catch (InterruptedException e) {
            logUtil.logMessages("E",e.getMessage(), this.getClass().getName());
        }
    }

    @When("^I scan the Boarding pass$")
    public void iScanTheBoardingPass(DataTable table){
        CucumberUtility.ConvertDataTableToDictionary(table);
        CurrentPage.As(PlaceBagPage.class).ScanBP(CucumberUtility.GetCellValue("Boarding Pass Stream"));
    }

    @Then("^I validate the outcomes$")
    public void iValidateErrors(DataTable table) {
        CurrentPage = GetInstance(ValidationPage.class);
        CucumberUtility.ConvertDataTableToDictionary(table);
        try {
            Assert.assertEquals(CurrentPage.As(ValidationPage.class).ValidateErrorMessages(), (CucumberUtility.GetCellValue("Error Message")));
        }catch(AssertionError E)
        {
            logUtil.logMessages("E",E.getMessage(), this.getClass().getName());
            throw E;
        }
    }

    @And("^I enter the Bag Tag No and Weight$")
    public void iEnterBagTagNoAndWeight(DataTable Table){
        CurrentPage = GetInstance(PlaceBagPage.class);
        CucumberUtility.ConvertDataTableToDictionary(Table);
        CurrentPage = CurrentPage.As(PlaceBagPage.class).PlaceBagOnBelt(CucumberUtility.GetCellValue("Bag Tag No"), CucumberUtility.GetCellValue("Bag Weight"));
    }

    @Then("^I Check in the Bag$")
    public void iCheckInBag() {
        CurrentPage = GetInstance(CheckInProcessPage.class);
        try {
            Assert.assertTrue(CurrentPage.As(CheckInProcessPage.class).CheckInBags(), "Bags was not checkedin successfully");
        }catch (AssertionError E)
        {
            logUtil.logMessages("E",E.getMessage(), this.getClass().getName());
            throw E;
        } catch (InterruptedException e) {
            logUtil.logMessages("e", e.getMessage(), this.getClass().getName());
        }
    }

    @And("^Check whether bag is checked-in successfully$")
    public void iCheckSuccessfulBagCheckIn() {
        try {
            Assert.assertTrue(CurrentPage.As(CheckInProcessPage.class).VerifySuccessfulCheckIn(), "Bag Not checked in successfully");
        }catch (AssertionError E)
        {
            logUtil.logMessages("E",E.getMessage(), this.getClass().getName());
            throw E;
        }

    }

    @And("^I check for Place Next Bag Screen$")
    public void iCheckPlaceNextBagScreen() {
        CurrentPage = GetInstance(PlaceBagPage.class);
        try {
            Assert.assertTrue(CurrentPage.As(PlaceBagPage.class).VerifyPlaceNextBag(), "Place Next Bag Page not found");
        } catch (AssertionError E)
        {
            logUtil.logMessages("E", E.getMessage(), this.getClass().getName());
            throw E;
        }
    }

    @Then("^I try to check in the Bag$")
    public void iTryToCheckInTheBag() throws InterruptedException{
        CurrentPage = GetInstance(CheckInProcessPage.class);
        CurrentPage.As(CheckInProcessPage.class).TryToCheckIn();
    }

    @And("^I Click on Continue Button$")
    public void iClickOnContinueButton() {
        CurrentPage = GetInstance(PlaceBagPage.class);
        CurrentPage.As(PlaceBagPage.class).ContinueProcess();
    }

    @Then("^I scan Agent card and enter Password$")
    public void iScanAgentCardAndEnterPassword(DataTable Table) throws Throwable {
        CurrentPage = GetInstance(AgentPage.class);
        CucumberUtility.ConvertDataTableToDictionary(Table);
        try {
            Assert.assertTrue(CurrentPage.As(AgentPage.class).ScanAgentCard(CucumberUtility.GetCellValue("Agent card Barcode"), CucumberUtility.GetCellValue("Password"))
                    , "Page failed to appear after 20 seconds");
        }catch (AssertionError E)
        {
            logUtil.logMessages("E", E.getMessage(), this.getClass().getName());
            throw E;
        }

    }

    @Then("^I verify the last checked in bags$")
    public void iVerifyTheLastCheckedInBags()
    {
        try {
            Assert.assertTrue(CurrentPage.As(AgentPage.class).LastCheckedInBags(), "Last Checked in bags screen not loaded after 20 seconds");
        }catch (AssertionError E)
        {
            logUtil.logMessages("E",E.getMessage(), this.getClass().getName());
            throw  E;
        }
    }
}
