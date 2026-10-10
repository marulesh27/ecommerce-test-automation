package ecom.swaglabs.Negative;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import objRepo.LoginPOM;
import utilities.ExcelUtility;
import utilities.ScreenshotUtility;

public class NegativeLogin extends BaseClass{
	
	@Test(groups="neg")
	public void invalid() throws IOException {
		LoginPOM log=new LoginPOM(driver);
		ExcelUtility ex=new ExcelUtility();
		String name=ex.getData("sheet2", 2, 1);
		String pass=ex.getData("sheet2", 2, 2);
		log.enterUName(name);
		log.enterPass(pass);
		log.clickLogin();
		System.out.println(log.getErrorMessage());
		Assert.assertTrue(log.getErrorMessage().contains("Username and password"));
		ScreenshotUtility.captureScreenshot(driver, "Invalid-login");
	}

}
