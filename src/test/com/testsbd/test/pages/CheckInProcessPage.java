package com.testsbd.test.pages;

import com.testsbd.framework.base.BasePage;
import com.testsbd.framework.base.DriverContext;
import gherkin.lexer.Pa;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Ranjani.Ilango on 7/06/2017.
 */
public class CheckInProcessPage extends BasePage
{
    @FindBy(how = How.ID, using = "errorHeading1")
    private WebElement txtErrorMessage;

    @FindBy(how = How.XPATH, using = "//*[@id='currentWeight']/div[1]/h2")
    public WebElement txtWelcome;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[2]/div[3]/button")
    private WebElement btnCheckIn;

    @FindBy(how = How.ID, using = "tb_moveToVerfication")
    private WebElement btnMoveToVerification;

    @FindBy(how = How.XPATH, using = "//*[@id='processing']/table")
    private WebElement txtPleaseWait;

    @FindBy(how = How.ID, using = "tb_deliverBag")
    private WebElement btnDeliverBag;

    @FindBy(how = How.XPATH, using = "//*[@id='success']/div[2]/div[2]")
    private WebElement pageCheckedIn;

    private WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 20);

    public boolean CheckInBags() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(txtWelcome));
        btnCheckIn.click();
        Thread.sleep(2000);
        DriverContext.Browser.JScriptExecute("tb_moveToVerfication");

        try
        {
            wait.until(ExpectedConditions.textToBePresentInElement(txtPleaseWait, "Please wait while we check-in your bag"));
            wait.until(ExpectedConditions.textToBePresentInElement(txtPleaseWait, "Please wait ..."));
            Thread.sleep(3000);
            DriverContext.Browser.JScriptExecute("tb_deliverBag");
            return true;
        }catch (TimeoutException E)
        {
            return false;
        }
    }

    public void TryToCheckIn() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(txtWelcome));
        btnCheckIn.click();
        Thread.sleep(2000);
        DriverContext.Browser.JScriptExecute("tb_moveToVerfication");
    }

    public boolean VerifySuccessfulCheckIn()
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageCheckedIn));
            return true;
        }catch (Exception E)
        {
            return false;
        }
    }
}
