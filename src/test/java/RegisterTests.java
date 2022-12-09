import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTests {
    WebDriver driver;

    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }
    @Test
    public void register(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("[title=\"Register\"]")).click();
        driver.findElement(By.id("firstname")).sendKeys("Vasile");
        driver.findElement(By.id("lastname")).sendKeys("Cristi");
        driver.findElement(By.id("email_address")).sendKeys("cristivasile-code3@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("confirmation")).sendKeys("123456");
        driver.findElement(By.id("is_subscribed")).click();
        driver.findElement(By.cssSelector("[type=\"submit\"][title=\"Register\"]")).click();
        wait(2);
        String welcomeText = "Thank you for registering with Madison Island.";
        String actualText = driver.findElement(By.cssSelector(".success-msg span")).getText();
        Assert.assertEquals(welcomeText, actualText);
    }
    @Test
    public void registerWithExistingUser (){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("[title=\"Register\"]")).click();
        driver.findElement(By.id("firstname")).sendKeys("Vasile");
        driver.findElement(By.id("lastname")).sendKeys("Cristi");
        driver.findElement(By.id("email_address")).sendKeys("cristivasile-code3@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("confirmation")).sendKeys("123456");
        driver.findElement(By.id("is_subscribed")).click();
        driver.findElement(By.cssSelector("[type=\"submit\"][title=\"Register\"]")).click();
        wait(2);
        String errText = "There is already an account with this email address. If you are sure that it is your email" +
                " address, click here to get your password and access your account.";
        String actualText = driver.findElement(By.cssSelector(".error-msg span")).getText();
            System.out.println(actualText);
        Assert.assertEquals(errText, actualText);

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

