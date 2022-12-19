package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
     WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".skip-account .label")
    private WebElement accountButton;

    @FindBy(css = "[title='Log In']")
    private WebElement loginLink;
    @FindBy(css="[title=\"Register\"]")
    private WebElement registerLink;
    @FindBy(css="[title=\"Log Out\"]")
    private WebElement logoutLink;

    public void clickAccountButton(){
        accountButton.click();
    }
    public void clickLoginLink(){
        loginLink.click();
    }
    public void clickRegisterLink(){
        registerLink.click();
    }
    public void clickLogoutLink(){
        logoutLink.click();
    }
}
