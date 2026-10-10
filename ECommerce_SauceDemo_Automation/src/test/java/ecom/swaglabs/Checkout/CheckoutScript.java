package ecom.swaglabs.Checkout;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import objRepo.CartPOM;
import objRepo.CheckoutPOM;
import objRepo.LoginPOM;
import objRepo.ProductCatalogPOM;
import utilities.ExcelUtility;

public class CheckoutScript extends BaseClass {

    LoginPOM log;
    ProductCatalogPOM pc;
    CartPOM cart;
    CheckoutPOM checkout;
    ExcelUtility ex;

    @BeforeMethod(alwaysRun = true)
    public void initialise() {
        log = new LoginPOM(driver);
        pc=new ProductCatalogPOM(driver);
        cart = new CartPOM(driver);
        checkout = new CheckoutPOM(driver);
        ex = new ExcelUtility();
    }

    @Test(groups = "smoke")
    public void VerifyCheckoutPage() throws IOException {

        String name = ex.getData("sheet2", 1, 1);
        String pass = ex.getData("sheet2", 1, 2);

        log.enterUName(name);
        log.enterPass(pass);
        log.clickLogin();

        cart.clickAddToCart();
        cart.clickCartIcon();
        checkout.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    @Test(groups = {"functional", "integration"})
    public void VerifyCheckoutDetails() throws IOException {

        String name = ex.getData("sheet2", 1, 1);
        String pass = ex.getData("sheet2", 1, 2);

        log.enterUName(name);
        log.enterPass(pass);
        log.clickLogin();

        cart.clickAddToCart();
        cart.clickCartIcon();
        checkout.clickCheckout();

        String firstName = ex.getData("sheet3", 1, 1);
        String lastName = ex.getData("sheet3", 1, 2);
        String postalCode = ex.getData("sheet3", 1, 3);

        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterPostalCode(postalCode);
        checkout.clickContinue();

        Assert.assertTrue(checkout.verifyCheckoutOverview());
    }
    @Test(groups = "system")
    public void VerifyCompleteCheckoutFlow() throws IOException {

        // Login
        String name = ex.getData("sheet2", 1, 1);
        String pass = ex.getData("sheet2", 1, 2);

        log.enterUName(name);
        log.enterPass(pass);
        log.clickLogin();

        // Add product to cart
        cart.clickAddToCart();
        cart.clickCartIcon();

        // Checkout
        checkout.clickCheckout();

        String firstName = ex.getData("sheet3", 1, 1);
        String lastName = ex.getData("sheet3", 1, 2);
        String postalCode = ex.getData("sheet3", 1, 3);

        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterPostalCode(postalCode);

        // Verify checkout overview
        checkout.clickContinue();
        Assert.assertTrue(checkout.verifyCheckoutOverview());

        // Finish purchase
        checkout.clickFinish();

        String expectedMessage = ex.getData("sheet3", 1, 4);
        String actualMessage = checkout.getThankYouMessage();

        Assert.assertEquals(actualMessage, expectedMessage);

        // Logout
        pc.clickHamburger();
        pc.clickLogout();

        // Verify successful logout
        Assert.assertTrue(log.isLoginButtonDisplayed(),"Logout failed: Login button is not displayed.");
    }
}