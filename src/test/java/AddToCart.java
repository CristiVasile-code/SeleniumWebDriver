import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AddToCart {
    WebDriver driver;
    Actions actions;
    WebElement mainMenu;
    WebElement subMenu;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
    }
    @Test
    public void addToCartN(){
        //logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();

        driver.findElement(By.cssSelector(".nav-6 a")).click();
        driver.findElement(By.id("product-collection-image-412")).click();
        driver.findElement(By.id("swatch26")).click();
        driver.findElement(By.id("swatch80")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons>button")).click();
        wait(3);
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title h1")).getText();
        Assert.assertEquals(expectedText, actualText);

    }
    @Test
    public void addToCartFromDropDown(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //dropdown
        mainMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[2]/a"));
//Instantiating Actions class
         actions = new Actions(driver);
//Hovering on main menu
        actions.moveToElement(mainMenu);
// Locating the element from Sub Menu
         subMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[2]/ul/li[3]/a"));
//To mouseover on sub menu
        actions.moveToElement(subMenu);
//build()- used to compile all the actions into a single step
        actions.click().build().perform();

        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[3]/div[3]/ul/li[1]/div/div[2]/a")).click();
        driver.findElement(By.id("swatch17")).click();
        driver.findElement(By.id("swatch80")).click();
        driver.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[6]/div[2]/div[2]/button")).click();
        //Boolean display = driver.findElement(By.cssSelector(".page-title h1")).isDisplayed();
        //Assert.assertTrue(display);
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expectedText,actualText);
    }
    @Test
    public void removeItemFromCartAll(){
        //logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //navighez la cart
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector(".top-link-cart")).click();
        //click pe remove primul elem
        driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[6]/a")).click();
    }
    @Ignore
    @Test
    public void removeItemFromCartOne(){
        //logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //navighez la cart
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector(".top-link-cart")).click();
        //click pe remove primul elem
        String qty = driver.findElement(By.cssSelector("[name=\"cart[20420][qty]\"]")).getText();
        int inte = Integer.valueOf(qty);
        System.out.println(inte);
    }
    @Test
    public void removeAllItemsFromCart(){
        //logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //navighez la cart
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector(".top-link-cart")).click();
        //click pe empty cart
        driver.findElement(By.id("empty_cart_button")).click();
        String expectedText = "SHOPPING CART IS EMPTY";
        String actualText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expectedText,actualText);
    }
    @Test
    public void addToCartFromSearch(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //scrie "shirt" in search field
        driver.findElement(By.id("search")).sendKeys("shirt");
        //click pe iconul de search
        driver.findElement(By.cssSelector("[title=\"Search\"]")).click();
        //click pe primul item
        driver.findElement(By.id("product-collection-image-413")).click();
        //atribute
        driver.findElement(By.id("swatch26")).click();
        driver.findElement(By.id("swatch79")).click();
        //click pe Add to cart
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expectedText,actualText);
    }
    @Test
    public void checkRequiredFields(){
        //logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();

        driver.findElement(By.cssSelector(".nav-6 a")).click();
        driver.findElement(By.id("product-collection-image-412")).click();
        driver.findElement(By.id("swatch26")).click();
        //driver.findElement(By.id("swatch80")).click();

        driver.findElement(By.cssSelector(".add-to-cart-buttons>button")).click();
        //verific daca este afisat textul "Required field"
        WebElement ReqFields = driver.findElement(By.cssSelector(".validation-advice"));
        Assert.assertTrue(ReqFields.isDisplayed());
    }
    public void wait(int seconds){
        try{
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //imi face probleme partea asta, incetineste tot testul la final
    @After
    public void quit(){
        driver.close();
    }


    //ma duc pana la checkout, cu plata
}
