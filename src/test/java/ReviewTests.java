import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ReviewTests {
    WebElement mainMenu;
    WebElement subMenu;
    WebDriver driver;
    Actions actions;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }
    @Test
    public void addReview(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        mainMenu = driver.findElement(By.cssSelector(".nav-1>a"));

        actions = new Actions(driver);
        actions.moveToElement(mainMenu);
        subMenu = driver.findElement(By.cssSelector(".nav-1-2>a"));

        actions.moveToElement(subMenu);
        wait(1);
        actions.click().build().perform();
        driver.findElement(By.id("product-collection-image-421")).click();
        driver.findElement(By.cssSelector("body > div.wrapper > div.page > div.main-container.col1-layout > div > div.col-main > div.product-view > div.product-collateral.toggle-content.tabs > ul > li.last > span")).click();
        if(driver.findElement(By.cssSelector(".no-rating>a")).isDisplayed()) {
            driver.findElement(By.cssSelector(".no-rating>a")).click();
            driver.findElement(By.id("Price_5")).click();
            driver.findElement(By.id("Value_5")).click();
            driver.findElement(By.id("Quality_5")).click();
            driver.findElement(By.id("review_field")).sendKeys("bla");
            driver.findElement(By.id("summary_field")).sendKeys("more bla");
            driver.findElement(By.cssSelector("[title=\"Submit Review\"]")).click();
            String expText = "Your review has been accepted for moderation.";
            String actText = driver.findElement(By.cssSelector(".success-msg span")).getText();
            Assert.assertEquals(expText,actText);
        }
    }
    public void wait(int seconds){
        try{
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
