package ecom.swaglabs.Cart;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import objRepo.CartPOM;
import objRepo.LoginPOM;
import utilities.ExcelUtility;

public class CartScript extends BaseClass {

    LoginPOM log;
    CartPOM cart;
    ExcelUtility ex;

    @BeforeMethod(alwaysRun = true)
    public void initialise() {
        log = new LoginPOM(driver);
        cart = new CartPOM(driver);
        ex = new ExcelUtility();
    }

    @Test(groups = "smoke")
    public void VerifyCartAccess() throws IOException {

        String name = ex.getData("sheet2", 1, 1);
        String pass = ex.getData("sheet2", 1, 2);
        log.enterUName(name);
        log.enterPass(pass);
        log.clickLogin();

        cart.clickAddToCart();
        cart.clickCartIcon();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }

    @Test(groups = {"functional", "integration"})
    public void VerifyAddtocart() throws IOException {

        String name = ex.getData("sheet2", 1, 1);
        String pass = ex.getData("sheet2", 1, 2);

        log.enterUName(name);
        log.enterPass(pass);
        log.clickLogin();

        cart.clickAddToCart();
        cart.clickCartIcon();

        String firstprod = ex.getData("sheet1", 1, 2);
        String actprod = cart.getCartProduct();

        Assert.assertEquals(actprod, firstprod);
    }
    
    @Test(groups = "system")
    public void VerifyCartSystemFlow() throws IOException {

        String name = ex.getData("sheet2", 1, 1);
        String pass = ex.getData("sheet2", 1, 2);

        log.enterUName(name);
        log.enterPass(pass);
        log.clickLogin();

        cart.clickAddToCart();
        cart.clickCartIcon();

        String firstprod = ex.getData("sheet1", 1, 2);
        String actprod = cart.getCartProduct();

        Assert.assertEquals(actprod, firstprod);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }
}