package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="firstname")
    private WebElement firstNameField;
    @FindBy(id="lastname")
    private WebElement lastNameField;
    @FindBy(id="email_address")
    private WebElement emailAddressField;
    @FindBy(id="password")
    private WebElement passwordField;
    @FindBy(id="confirmation")
    private WebElement confField;
    @FindBy(id="is_subscribed")
    private WebElement isSubsRadio;
    @FindBy(css="[type=\"submit\"][title=\"Register\"]")
    private WebElement registerButton;
    @FindBy(css=".success-msg span")
    private WebElement welcomeMessage;
    @FindBy(css=".error-msg span")
    private WebElement errorMessage;


    public void setFirstNameField(String value){
        firstNameField.sendKeys(value);
    }
    public void setLastNameField(String value){
        lastNameField.sendKeys(value);
    }
    public void setEmailAddressField(String value){
        emailAddressField.sendKeys(value);
    }
    public void setPasswordField(String value){
        passwordField.sendKeys(value);
    }
    public void setConfField(String value){
        confField.sendKeys(value);
    }
    public void clickIsSubscribedRButton(){
        isSubsRadio.click();
    }
    public void clickRegisterButton(){
        registerButton.click();
    }
    public String getWelcomeMessage(){
        return welcomeMessage.getText();
    }
    public String getErrorMessage(){
        return errorMessage.getText();
    }
}
