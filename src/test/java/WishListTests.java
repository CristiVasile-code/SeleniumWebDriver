import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;

public class WishListTests {
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
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }
    @Test
    public void wishListSale(){
        // logare
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
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
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        //wait(1);
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[2]/a")).click();
        driver.findElement(By.cssSelector(".btn-remove2")).click();
        //driver.findElement(By.cssSelector("[title=\"Remove Item\"]")).click();
    }
    @Test
    public void addToCartFromWishlist(){
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        //wait(1);
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(2) > a")).click();
        driver.findElement(By.cssSelector(".cart-cell [title=\"Add to Cart\"]")).click();
    }
    @Test
    public void checkLeftWlLink(){
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        driver.findElement(By.cssSelector(".block-content li:nth-child(8)>a")).click();
        String expText = "MY WISHLIST";
        String actText  = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expText,actText);
    }
    public void wait(int seconds){
        try{
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //@After
    public void quit(){
        wait(5);
        driver.close();
    }
}
