package ecom.swaglabs.Login;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import objRepo.LoginPOM;
import utilities.ExcelUtility;

public class LoginScript extends BaseClass{
	LoginPOM log;
	ExcelUtility ex;
	
	@BeforeMethod(alwaysRun = true)
	public void initialise() {
		log=new LoginPOM(driver);
		ex=new ExcelUtility();
	}
	@Test(groups="smoke")
	public void Login() throws IOException {
		String name=ex.getData("sheet2", 1, 1);
		String pass=ex.getData("sheet2", 1, 2);
		log.enterUName(name);
		log.enterPass(pass);
		log.clickLogin();
		Reporter.log("logged in successfully",true);
	}
	@Test(groups={"functional","integration"})
	public void VerifyLogin() throws IOException {
		String name=ex.getData("sheet2", 1, 1);
		String pass=ex.getData("sheet2", 1, 2);
		log.enterUName(name);
		log.enterPass(pass);
		log.clickLogin();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
	}

}
