import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    WebDriver driver;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }
    @Test
    public void loginWithValidData() {
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        WebElement welcomeTextElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > div.welcome-msg > p.hello > strong"));
        String expectedText = "Hello, Vasile Cristi!";
        String actualText = welcomeTextElement.getText();
        Assert.assertEquals(expectedText, actualText);
    }
    @Test
    public void logOut(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("[title=\"Log Out\"]")).click();
        String expectedText = "YOU ARE NOW LOGGED OUT";
        String actualText = driver.findElement(By.cssSelector(".page-title h1")).getText();
        Assert.assertEquals(expectedText,actualText);
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
