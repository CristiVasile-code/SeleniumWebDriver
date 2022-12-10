import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WishListTests {
    WebDriver driver;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }
    @Test
    public void wishListSale(){
        // logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //apasa pe SALE
        driver.findElement(By.cssSelector(".nav-5>a")).click();
        String title = driver.findElement(By.cssSelector(".page-title h1")).getText();
        if(title.equals("SALE")){
            System.out.println("Esti pe pagina Sale");}
        //apasa pe view details
        driver.findElement(By.cssSelector(".actions>a[title]")).click();
        // add to wishlist
        driver.findElement(By.className("link-wishlist")).click();

    }
    @Test
    public void removeItem(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //wait(1);
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[2]/a")).click();
        driver.findElement(By.cssSelector(".btn-remove2")).click();
        //driver.findElement(By.cssSelector("[title=\"Remove Item\"]")).click();
    }
    @Test
    public void addToCartFromWishlist(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //wait(1);
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(2) > a")).click();
        driver.findElement(By.cssSelector(".cart-cell [title=\"Add to Cart\"]")).click();
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
