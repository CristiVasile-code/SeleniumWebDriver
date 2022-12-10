import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.util.logging.Logger;

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

    @Test
    public void removeOneItemFromCart(){
        //logare
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //navighez la cart
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector(".top-link-cart")).click();
        //pun in variabila qty ce gasesc in atributul "value" si o transform in int
        String qty = driver.findElement(By.cssSelector("[name=\"cart[20436][qty]\"]")).getAttribute("value");
        int qtyInteger = Integer.valueOf(qty);
        if(qtyInteger > 1){
        String noulString = String.valueOf(qtyInteger-1);
        //sterg ce era in atributul value si trimit noul string
        driver.findElement(By.cssSelector("[name=\"cart[20436][qty]\"]")).clear();
        driver.findElement(By.cssSelector("[name=\"cart[20436][qty]\"]")).sendKeys(noulString);
        //dau click pe Update
        driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr.last.even > td.product-cart-actions > button")).click();
        Assert.assertTrue("Cantitatea a fost modificata", qtyInteger !=1);}
        else Assert.assertTrue("Cantitatea este 1", qtyInteger ==1);
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
    @Test
    public void checkCartLink(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".header-minicart>a")).click();
        Assert.assertTrue(driver.findElement(By.id("cart-sidebar")).isDisplayed());
    }
    //.cart-link
    @Test
    public void checkViewShoppingCartLink(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".header-minicart>a")).click();
        driver.findElement(By.cssSelector(".cart-link")).click();
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expectedText,actualText);
    }
    @Test
    public void checkCheckOutButtonOnCartDropDown(){
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("a[title=\"Log In\"]")).click();
        driver.findElement(By.id("email")).sendKeys("cristivasile-code@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        driver.findElement(By.cssSelector(".header-minicart>a")).click();
        driver.findElement(By.cssSelector(".checkout-button")).click();
        String expText = "CHECKOUT";
        String actText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expText,actText);
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
