package ecom.swaglabs.Negative;

import java.io.IOException;
import org.testng.annotations.Test;
import base.BaseClass;
import objRepo.LoginPOM;
import objRepo.CartPOM;
import objRepo.CheckoutPOM;
import utilities.ExcelUtility;
import utilities.ScreenshotUtility;

public class NegativeCheckout extends BaseClass {

    @Test(groups = "negative")
    public void verifyMissingLastName() throws IOException {

        LoginPOM log = new LoginPOM(driver);
        CartPOM cart = new CartPOM(driver);
        CheckoutPOM checkout = new CheckoutPOM(driver);
        ExcelUtility ex = new ExcelUtility();
        // Read login credentials from Excel
        String username = ex.getData("sheet2", 1, 1);
        String password = ex.getData("sheet2", 1, 2);

        // Login
        log.enterUName(username);
        log.enterPass(password);
        log.clickLogin();

        // Navigate to checkout
        cart.clickAddToCart();
        cart.clickCartIcon();
        checkout.clickCheckout();

        // Read First Name from Excel; leave other fields empty
        String firstName = ex.getData("sheet3", 1, 1);
        checkout.enterFirstName(firstName);

        checkout.clickContinue();

        // Capture validation evidence
        ScreenshotUtility.captureScreenshot(driver, "MissingLastName");
    }

    @Test(groups = "negative")
    public void verifyMissingPostalCode() throws IOException {

        LoginPOM log = new LoginPOM(driver);
        CartPOM cart = new CartPOM(driver);
        CheckoutPOM checkout = new CheckoutPOM(driver);
        ExcelUtility ex = new ExcelUtility();

        // Read login credentials from Excel
        String username = ex.getData("sheet2", 1, 1);
        String password = ex.getData("sheet2", 1, 2);

        // Login
        log.enterUName(username);
        log.enterPass(password);
        log.clickLogin();

        // Navigate to checkout
        cart.clickAddToCart();
        cart.clickCartIcon();
        checkout.clickCheckout();

        // Read First Name and Last Name from Excel
        String firstName = ex.getData("sheet3", 1, 1);
        String lastName = ex.getData("sheet3", 1, 2);

        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);

        // Leave Postal Code empty
        checkout.clickContinue();

        // Capture screenshot of validation error
        ScreenshotUtility.captureScreenshot(driver, "MissingPostalCode");

        
    }
}


