package com.testsbd.framework.base;

import org.openqa.selenium.support.PageFactory;

/**
 * Created by Ranjani.Ilango on 2/06/2017.
 */
public class Base
{
    public static BasePage CurrentPage;

    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page)
    {
        Object Obj = PageFactory.initElements(DriverContext.Driver, page);
        return page.cast(Obj);
    }
}
