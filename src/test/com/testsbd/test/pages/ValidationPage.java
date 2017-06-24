package com.testsbd.test.pages;

import com.testsbd.framework.base.BasePage;
import com.testsbd.framework.base.DriverContext;
import com.testsbd.framework.utilities.LogUtility;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.List;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class ValidationPage extends BasePage
{
    @FindBy(how = How.ID, using = "errorHeading1")
    private WebElement txtErrorMessage;

    @FindBy(how = How.XPATH, using = "//*[@id='step1']/div[2]/h3")
    private WebElement imgScanBP;

    @FindBy(how = How.XPATH, using = "//*[@id='baggageAlert']/div/button[2]")
    public WebElement btnBaggageAlert;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[2]/div[1]/button")
    public WebElement btnExit;

    @FindBy(how = How.XPATH, using = "//*[@id='baggageAlert']/h3/span[1]")
    private  WebElement alertBagTagMissing;

    @FindBy(how = How.XPATH, using = "//*[@id='baggageAlert']/h3")
    private  WebElement alertHeavyTag;

    @FindBy(how = How.ID, using = "ti_unitname")
    private WebElement txtUnitName;

    @FindBy(how = How.ID, using = "tb_initialize")
    private  WebElement btnUnitInitialize;

    @FindBy(how = How.ID, using = "tt_connectorStatus")
    private WebElement txtConnectorStatus;

    public boolean IsSBDLaunched()
    {
        return txtUnitName.isDisplayed();
    }

    private WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 15);

    LogUtility logUtility = new LogUtility();

    public String ConfigureABDUnit(String ABDUnitName)
    {
        txtUnitName.clear();
        txtUnitName.sendKeys(ABDUnitName);
        DriverContext.Browser.JScriptExecute("tb_initialize");
        return txtConnectorStatus.getText();
    }

    public boolean ConfirmInBPScreen() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(txtErrorMessage),
                    ExpectedConditions.visibilityOf(imgScanBP),
                    ExpectedConditions.visibilityOf(btnBaggageAlert)
            ));
        }catch (TimeoutException E)
        {
            return false;
        }

        try {
            if ((txtErrorMessage.isDisplayed()) || (btnBaggageAlert.isDisplayed())) {
                btnExit.click();
            }
        }
        catch (NoSuchElementException N){}
        try
        {
            if (btnBaggageAlert.isDisplayed())
            {
                btnExit.click();
            }
        }catch (NoSuchElementException N){}

        return true;
    }

    public String ValidateErrorMessages()
    {
        try {
            wait.until((ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(alertBagTagMissing),
                    ExpectedConditions.visibilityOf(txtErrorMessage),
                    ExpectedConditions.visibilityOf(alertHeavyTag)
            )));
        }
        catch (TimeoutException E)
        {
            return "Expected Screen did not appear after 20 seconds";
        }

        try {
            if (txtErrorMessage.isDisplayed())
            {
                return txtErrorMessage.getText();
            }
        } catch (NoSuchElementException E) {}

        try {
            if (alertBagTagMissing.isDisplayed())
            {
                return alertBagTagMissing.getText();
            }
        }catch(NoSuchElementException N){}

        try{
            if (alertHeavyTag.isDisplayed())
            {
                return alertHeavyTag.getText();
            }
        }catch(NoSuchElementException N){}

        return null;
    }
}
