package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {
	WebDriver driver;
	public LoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="user-name")
	private WebElement un;
	
	@FindBy(id="password")
	private WebElement pwd;
	
	@FindBy(id="login-button")
	private WebElement login;
	
	@FindBy(css="[data-test='error']")
	private WebElement errmssg;
	
	public void enterUName(String value) {
		un.sendKeys(value);
	}
	public void enterPass(String value) {
		pwd.sendKeys(value);
	}
	public void clickLogin() {
		login.click();
	}
	public String getErrorMessage() {
		return errmssg.getText();
	}
	public boolean isLoginButtonDisplayed() {
		return login.isDisplayed();
	}
}
