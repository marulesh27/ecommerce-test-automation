package objRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductCatalogPOM {
	WebDriver driver;
	public ProductCatalogPOM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(className="product_sort_container")
	private WebElement dropdown;
	
	@FindBy(className="inventory_item_name")
	private List<WebElement> prod;
	
	@FindBy(className="inventory_item_price")
	private List<WebElement> price;
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement hamburger;
	
	@FindBy(id="inventory_sidebar_link")
	private WebElement allitems;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement logout;
	
	public WebElement getDropdown() {
		return dropdown;
	}
	
	public void SelectOption(String option) {
		Select sel = new Select(dropdown);
		sel.selectByVisibleText(option);
	}
	public String getSelectedOption() {
	    Select sel = new Select(dropdown);
	    return sel.getFirstSelectedOption().getText();
	}
	public int getDropdownOptionsCount() {
	    Select sel = new Select(dropdown);
	    return sel.getOptions().size();
	}
	public List<WebElement> getProd(){
		return prod;
	}
	public List<WebElement> getPrice(){
		return price;
	}
	public void clickHamburger() {
		hamburger.click();
	}
	public void clickLogout() {
	    logout.click();
	}
	
}
