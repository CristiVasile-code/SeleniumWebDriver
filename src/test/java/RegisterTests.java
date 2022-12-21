import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.RegisterPage;

public class RegisterTests {
    WebDriver driver;
    HomePage homePage;
    //LoginPage loginPage;
    //AccountPage accPage;
    RegisterPage regPage;

    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        homePage = new HomePage(driver);
        regPage = new RegisterPage(driver);
    }
    @Test
    public void register(){
        homePage.clickAccountButton();
        homePage.clickRegisterLink();
        regPage.setFirstNameField("Vasile");
        regPage.setLastNameField("Cristi");
        regPage.setEmailAddressField("cristivasile-code5@gmail.com");
        regPage.setPasswordField("123456");
        regPage.setConfField("123456");
        regPage.clickIsSubscribedRButton();
        regPage.clickRegisterButton();
        Assert.assertEquals("Thank you for registering with Madison Island.", regPage.getWelcomeMessage());
    }
    @Test
    public void registerWithExistingUser (){
        homePage.clickAccountButton();
        homePage.clickRegisterLink();
        regPage.setFirstNameField("Vasile");
        regPage.setLastNameField("Cristi");
        regPage.setEmailAddressField("cristivasile-code4@gmail.com");
        regPage.setPasswordField("123456");
        regPage.setConfField("123456");
        regPage.clickIsSubscribedRButton();
        regPage.clickRegisterButton();
        Assert.assertEquals("There is already an account with this email address. If you are sure that it is your email" +
                " address, click here to get your password and access your account.", regPage.getErrorMessage());

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
        wait(5);
        driver.close();
    }
    }

