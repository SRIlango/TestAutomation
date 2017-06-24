package com.testsbd.test.pages;

import com.testsbd.framework.base.BasePage;
import com.testsbd.framework.base.DriverContext;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ranjani.Ilango on 6/06/2017.
 */
public class PlaceBagPage extends BasePage
{

    @FindBy(how = How.ID, using = "tb_active")
    private WebElement btnActive;

    @FindBy(how = How.ID, using = "tt_connectorStatus")
    private WebElement txtConnectorStatus;

    @FindBy(how = How.ID, using = "ti_boardingPassString")
    private WebElement txtBoardingPassStream;

    @FindBy(how = How.ID, using = "tb_scanBoardingPass")
    private WebElement btnScanBP;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[1]/div/div/h1")
    private  WebElement txtPlaceBag;

    @FindBy(how = How.XPATH, using = "//*[@id='baggageAlert']/h3")
    private  WebElement alertBagTag;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[2]/div[2]/ul/li[2]/span/span[1]")
    private WebElement txtGrossWeightDisplay;

    @FindBy(how = How.ID, using = "ti_bagWeight")
    private WebElement txtBagWeight;

    @FindBy(how = How.ID, using = "tb_placeBag")
    private WebElement btnPlaceBag;

    @FindBy(how = How.ID, using = "tb_bagTag")
    private WebElement txtBagTag;

    @FindBy(how = How.XPATH, using = "//*[@id='container']/div/div[1]/div[2]/div[3]/button")
    private WebElement btnContinue;

    private WebDriverWait wait = new WebDriverWait(DriverContext.Driver, 15);

    public String ActivateAPP() throws InterruptedException {
        DriverContext.Browser.JScriptExecute("tb_active");
        Thread.sleep(2000);
       return txtConnectorStatus.getText();
    }

    public void ScanBP(String BoardingPassBarcode)
    {
        txtBoardingPassStream.clear();
        txtBoardingPassStream.sendKeys(BoardingPassBarcode);
        DriverContext.Browser.JScriptExecute("tb_scanBoardingPass");
    }

    public CheckInProcessPage PlaceBagOnBelt(String BagTagNo, String BagWeight)
    {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(txtPlaceBag),
                    ExpectedConditions.visibilityOf(alertBagTag)
            ));
            txtBagWeight.clear();
            txtBagWeight.sendKeys(BagWeight);
            txtBagTag.clear();
            txtBagTag.sendKeys(BagTagNo);

            if (Integer.parseInt(BagWeight) > 23000)
            {
                txtBagTag.sendKeys(";;B7C");
            }

            if ((BagTagNo == null) || (BagTagNo.length() < 5)) {
                txtBagTag.sendKeys(Keys.BACK_SPACE);
            }

            DriverContext.Browser.JScriptExecute("tb_placeBag");

            wait.until(ExpectedConditions.visibilityOf(txtGrossWeightDisplay));
            return new CheckInProcessPage();
        }catch(Exception E)
        {
            return null;
        }
    }

    public boolean VerifyPlaceNextBag()
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(txtPlaceBag));
            return true;
        }catch (Exception E)
        {
            return false;
        }
    }

    public void ContinueProcess()
    {
        btnContinue.click();
    }
}
