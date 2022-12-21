package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
    WebDriver driver;
    public CheckOutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".method-checkout-cart-methods-onepage-bottom button")
    private WebElement checkOutButton;

    public void clickCheckOutButton(){
        checkOutButton.click();
    }
}
