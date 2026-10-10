package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPOM {

    WebDriver driver;

    public CartPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCart;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "inventory_item_name")
    private WebElement cartProduct;

    public void clickAddToCart() {
        addToCart.click();
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

    public String getCartProduct() {
        return cartProduct.getText();
    }
}