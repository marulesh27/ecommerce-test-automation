package ecom.swaglabs.Product;
import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import objRepo.LoginPOM;
import objRepo.ProductCatalogPOM;
import utilities.ExcelUtility;
	
	public class ProductCatalogTest extends BaseClass{
		LoginPOM log;
		ProductCatalogPOM pc;
		ExcelUtility ex;
		
		@BeforeMethod(alwaysRun = true)
		public void initialise() {
			log =new LoginPOM(driver);
			pc=new ProductCatalogPOM(driver);
			ex=new ExcelUtility();
		}
		
		@Test(groups={"smoke","integration"})
		public void VerifyProductPage() throws IOException {
			String name=ex.getData("sheet2", 1, 1);
			String pass=ex.getData("sheet2", 1, 2);
			log.enterUName(name);
			log.enterPass(pass);
			log.clickLogin();
			Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
		}
		
		@Test(groups="smoke")
			public void VerifyDropdown() throws IOException {
			String name=ex.getData("sheet2", 1, 1);
			String pass=ex.getData("sheet2", 1, 2);
			log.enterUName(name);
			log.enterPass(pass);
			log.clickLogin();
			Assert.assertTrue(pc.getDropdown().isDisplayed());
		}
	
		@Test(groups = "functional")
		public void Verify_A_ZSorting()throws Exception {
			
			String name=ex.getData("sheet2", 1, 1);
			String pass=ex.getData("sheet2", 1, 2);
			log.enterUName(name);
			log.enterPass(pass);
			log.clickLogin();
			
			String opt=ex.getData("sheet1", 1, 1);
			String firstprod=ex.getData("sheet1", 1, 2);
			pc.SelectOption(opt);
			String actprod=pc.getProd().get(0).getText();
			Assert.assertEquals(actprod, firstprod);
		}
		
		@Test(groups = "functional")
		public void Verify_Z_ASorting()throws Exception{
			String name=ex.getData("sheet2", 1, 1);
			String pass=ex.getData("sheet2", 1, 2);
			log.enterUName(name);
			log.enterPass(pass);
			log.clickLogin();
			
			String opt=ex.getData("sheet1", 2, 1);
			String firstprod=ex.getData("sheet1", 2, 2);
			pc.SelectOption(opt);
			String actprod=pc.getProd().get(0).getText();
			Assert.assertEquals(actprod, firstprod);
		}
		
		@Test(groups = "functional")
		public void Verify_low_to_highSorting()throws Exception{
			String name=ex.getData("sheet2", 1, 1);
			String pass=ex.getData("sheet2", 1, 2);
			log.enterUName(name);
			log.enterPass(pass);
			log.clickLogin();
			
			String opt=ex.getData("sheet1", 3, 1);
			pc.SelectOption(opt);
			double fp=Double.parseDouble(pc.getPrice().get(0).getText().replace("$", ""));
			int li=pc.getPrice().size()-1;
			double lp=Double.parseDouble(pc.getPrice().get(li).getText().replace("$", ""));
			Assert.assertTrue(fp<=lp);
			
		}
		
		@Test(groups = "functional")
		public void Verify_high_to_lowSorting()throws Exception{
			String name=ex.getData("sheet2", 1, 1);
			String pass=ex.getData("sheet2", 1, 2);
			log.enterUName(name);
			log.enterPass(pass);
			log.clickLogin();
			
			String opt=ex.getData("sheet1", 4, 1);
			String firstprod=ex.getData("sheet1", 4, 2);
			pc.SelectOption(opt);
			String actprod=pc.getProd().get(0).getText();
			Assert.assertEquals(actprod, firstprod);
		}
		
		@Test(groups = "functional")
		public void VerifyAllSortingOptions() throws IOException {

		    String name = ex.getData("sheet2", 1, 1);
		    String pass = ex.getData("sheet2", 1, 2);

		    log.enterUName(name);
		    log.enterPass(pass);
		    log.clickLogin();
            Assert.assertEquals(pc.getDropdownOptionsCount(), 4);
		}
		@Test(groups = "functional")
		public void VerifySelectedSortingOption() throws IOException {

		    String name = ex.getData("sheet2", 1, 1);
		    String pass = ex.getData("sheet2", 1, 2);

		    log.enterUName(name);
		    log.enterPass(pass);
		    log.clickLogin();

		    String option = ex.getData("sheet1", 1, 1);
		    pc.SelectOption(option);
		    Reporter.log("Selected sorting option: " + pc.getSelectedOption(), true);

		    Assert.assertEquals(pc.getSelectedOption(), option);
		}
		
		@Test(groups = "system")
		public void VerifyProductCatalogSystemFlow() throws IOException {

		    String name = ex.getData("sheet2", 1, 1);
		    String pass = ex.getData("sheet2", 1, 2);

		    log.enterUName(name);
		    log.enterPass(pass);
		    log.clickLogin();

		    String opt = ex.getData("sheet1", 1, 1);
		    String firstprod = ex.getData("sheet1", 1, 2);

		    pc.SelectOption(opt);

		    String actprod = pc.getProd().get(0).getText();

		    Assert.assertEquals(actprod, firstprod);
		    Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
		}
	}
