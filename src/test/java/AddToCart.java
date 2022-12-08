import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddToCart {
    WebDriver driver;
    Select cat;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }
    @Test
    public void addToCartN(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //cat = new Select(driver.findElement(By.cssSelector(".nav-2-2 a")));
        driver.findElement(By.cssSelector(".nav-6 a")).click();
        driver.findElement(By.id("product-collection-image-412")).click();
        driver.findElement(By.id("swatch26")).click();
        driver.findElement(By.id("swatch80")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        wait(3);
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title h1")).getText();
        Assert.assertEquals(expectedText, actualText);
       // driver.findElement(By.cssSelector();
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


    // logare, navigare la o sectiune, alegere un produs, punere in cos
    //nu stiu cum sa fac cu optiunile
    //aleg un produs fara optiuni si ma duc pana la checkout, cu plata
}
