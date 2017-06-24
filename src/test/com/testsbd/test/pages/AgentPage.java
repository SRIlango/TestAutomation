package com.testsbd.test.pages;

import com.testsbd.framework.base.BasePage;
import com.testsbd.framework.base.DriverContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Ranjani.Ilango on 13/06/2017.
 */
public class AgentPage extends BasePage
{
    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[1]/div/div/div[1]/div")
    private List<WebElement> pnlPassword;

    @FindBy(how = How.ID, using = "ti_agentBarcodeString")
    private WebElement txtAgentCardBarcode;

    @FindBy(how = How.ID, using = "tb_scanAgentBarcode")
    private WebElement btnScanAgentCard;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[2]/div[1]/button")
    public WebElement btnExit;

    @FindBy(how = How.XPATH, using = "//*[@id='admin']/div[1]/table/tbody/tr/td")
    private WebElement pageLastCheckedIn;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[2]/div[3]/button")
    private WebElement btnNext;

    private WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 15);

    public boolean ScanAgentCard(String Barcode, String Password) throws InterruptedException {
        txtAgentCardBarcode.clear();
        txtAgentCardBarcode.sendKeys(Barcode);
        DriverContext.Browser.JScriptExecute("tb_scanAgentBarcode");

        char[] password_array = Password.toCharArray();

        for (int i=0; i< password_array.length; i++)
        {
            switch (password_array[i])
            {
                case '1':
                    pnlPassword.get(2).click();
                    break;
                case '2':
                    pnlPassword.get(3).click();
                    break;
                case '3':
                    pnlPassword.get(4).click();
                    break;
                case '4':
                    pnlPassword.get(5).click();
                    break;
                case '5':
                    pnlPassword.get(6).click();
                    break;
                case '6':
                    pnlPassword.get(7).click();
                    break;
                case '7':
                    pnlPassword.get(8).click();
                    break;
                case '8':
                    pnlPassword.get(9).click();
                    break;
                case '9':
                    pnlPassword.get(10).click();
                    break;
                case '0':
                    pnlPassword.get(11).click();
                    break;
                default:
                    break;
            }
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(btnExit));
            btnNext.click();
        }catch (TimeoutException E)
        {
            return false;
        }
        return true;
    }

    public boolean LastCheckedInBags()
    {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageLastCheckedIn));
            return true;
        }catch (Exception E){        }
        return false;
    }
}
