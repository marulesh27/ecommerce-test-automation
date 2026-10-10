package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPOM {
    WebDriver driver;
    public CheckoutPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="checkout")
    private WebElement checkout;
    @FindBy(id = "first-name")
    private WebElement firstName;
    @FindBy(id = "last-name")
    private WebElement lastName;
    @FindBy(id = "postal-code")
    private WebElement postalCode;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(xpath = "//span[text()='Checkout: Overview']")
    private WebElement checkoutOverview;
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    private WebElement mssg;

    public void enterFirstName(String value) {
        firstName.sendKeys(value);
    }
    public void enterLastName(String value){
        lastName.sendKeys(value);
  }
    public void enterPostalCode(String value){
        postalCode.sendKeys(value);
    }
    public void clickContinue(){
        continueButton.click();
    }
    public boolean verifyCheckoutOverview() {
        return checkoutOverview.isDisplayed();
    }
    public void clickFinish(){
        finishButton.click();
    }
    public String getThankYouMessage() {
        return mssg.getText();
    }
    public void clickCheckout() {
    	checkout.click();
    }
}
