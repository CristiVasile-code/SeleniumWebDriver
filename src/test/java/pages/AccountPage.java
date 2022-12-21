package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;
    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hello strong")
    private WebElement welcomeTextElement;
    @FindBy(css=".nav-2>a")
    private WebElement mainMenuMen;
    @FindBy(css=".nav-2-2.menu-active>a")
    private WebElement subMenuShirts;
    @FindBy(id="search")
    private WebElement searchField;
    @FindBy(css="[title=\"Search\"]")
    private WebElement searchIcon;

    public String getWelcomeText(){
        return welcomeTextElement.getText();
    }
    public WebElement getMainMenuMen(){
        return mainMenuMen;
    }
    public WebElement getSubMenuShirts(){
        return subMenuShirts;
    }
    public void setSearchField(String value){
        searchField.sendKeys(value);
    }
    public void clickSearchIcon(){
        searchIcon.click();
    }
}
//        mainMenu = driver.findElement(By.cssSelector(".nav-2>a"));
//        actions = new Actions(driver);
//        actions.moveToElement(mainMenu);
//        subMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[2]/ul/li[3]/a"));
 //       actions.moveToElement(subMenu);
//        driver.findElement(By.id("search")).sendKeys("shirt");
//                //click pe iconul de search
//                driver.findElement(By.cssSelector("[title=\"Search\"]")).click();