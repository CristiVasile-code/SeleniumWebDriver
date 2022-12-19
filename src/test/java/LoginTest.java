package tests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;


public class LoginTest {
     WebDriver driver;
     LoginPage loginPage;
     HomePage homePage;
     AccountPage accPage;
     LogoutPage logoutPage;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accPage = new AccountPage(driver);
        logoutPage = new LogoutPage(driver);
    }
    @Test
    public void loginWithValidData() {
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        Assert.assertEquals("Hello, Vasile Cristi!", accPage.getWelcomeText());
    }
    @Test
    public void logOut(){
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        homePage.clickAccountButton();
        driver.findElement(By.cssSelector("[title=\"Log Out\"]")).click();
        Assert.assertEquals("YOU ARE NOW LOGGED OUT",logoutPage.getPageTitle());
    }
    public void wait(int seconds){
        try{
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @After
    public void quit(){
        driver.close();
    }
}
