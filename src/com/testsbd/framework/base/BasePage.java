package com.testsbd.framework.base;

import org.openqa.selenium.support.PageFactory;

/**
 * Created by Ranjani.Ilango on 2/06/2017.
 */
public abstract class BasePage
{
    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance)
    {
        try
        {
            return (TPage)this;
        }
        catch (Exception E)
        {
            E.getStackTrace();
        }

        return null;
    }
}
