import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.AccountPage;
import pages.CheckOutPage;
import pages.HomePage;
import pages.LoginPage;

public class CartTests {
    WebDriver driver;
    Actions actions;
    WebElement mainMenu;
    WebElement subMenu;
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accPage;
    CheckOutPage checkOutPage;
    @Before
    public void initDrive(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accPage = new AccountPage(driver);
        checkOutPage = new CheckOutPage(driver);
    }
    @Test
    public void smokeTest(){
        //logare
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code5@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        accPage.setSearchField("tee");
        //click pe iconul de search
        accPage.clickSearchIcon();
        driver.findElement(By.id("product-collection-image-408")).click();
        driver.findElement(By.id("swatch27")).click();
        driver.findElement(By.id("swatch80")).click();
        //driver.findElement(By.id("qty")).clear();
        //driver.findElement(By.id("qty")).sendKeys("2");
        driver.findElement(By.cssSelector(".add-to-cart-buttons>button")).click();
        Assert.assertEquals("Chelsea Tee was added to your shopping cart.", driver.findElement(By.cssSelector(".success-msg span")).getText());
        checkOutPage.clickCheckOutButton();
        //
        driver.findElement(By.id("billing:street1")).sendKeys("Str. Lunga nr.2");
        driver.findElement(By.id("billing:city")).sendKeys("Galati");
        driver.findElement(By.id("billing:region_id")).click();
        driver.findElement(By.cssSelector("#billing\\:region_id > option:nth-child(14)")).click();
        driver.findElement(By.id("billing:postcode")).sendKeys("800326");
        driver.findElement(By.id("billing:telephone")).sendKeys("5552365");
        driver.findElement(By.cssSelector("#billing-buttons-container button")).click();
        //step 3
        while(!(driver.findElement(By.id("co-shipping-method-form")).isDisplayed())){
         wait(1);
        }
        //billing:use_for_shipping_yes

        driver.findElement(By.id("s_method_freeshipping_freeshipping")).click();
//        while(!(driver.findElement(By.id("checkout-shipping-method-load dt")).isDisplayed())){
//            wait(1);
//        }
        driver.findElement(By.cssSelector("[onclick=\"shippingMethod.save()\"]")).click();
        driver.findElement(By.cssSelector("onclick=\"payment.save()\"")).click();
       while(!(driver.findElement(By.cssSelector(".btn-checkout")).isDisplayed())){
            wait(1);
        }
        driver.findElement(By.cssSelector(".btn-checkout")).click();
       Assert.assertEquals("YOUR ORDER HAS BEEN RECEIVED.\n", driver.findElement(By.cssSelector
               (".page-title h1")).getText());
    }
    @Test
    public void addToCartFromVIPmenu(){
        //logare
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
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
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        mainMenu = driver.findElement(By.cssSelector(".nav-2>a"));
        actions = new Actions(driver);
        actions.moveToElement(mainMenu);
        subMenu = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[2]/ul/li[3]/a"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();

        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[3]/div[3]/ul/li[1]/div/div[2]/a")).click();
        driver.findElement(By.id("swatch17")).click();
        driver.findElement(By.id("swatch80")).click();
        driver.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[6]/div[2]/div[2]/button")).click();

        //wait(3);
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expectedText,actualText);
    }
    @Test
    public void removeItemFromCartAll(){
        //logare
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        //navighez la cart
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector(".top-link-cart")).click();
        //click pe remove primul elem
        driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[6]/a")).click();
    }

    @Test
    public void removeOneItemFromCart(){
        //logare
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
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
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
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
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        //scrie "shirt" in search field
        accPage.setSearchField("shirt");
        //click pe iconul de search
        accPage.clickSearchIcon();
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
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();

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
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        driver.findElement(By.cssSelector(".header-minicart>a")).click();
        Assert.assertTrue(driver.findElement(By.id("cart-sidebar")).isDisplayed());
    }
    @Test
    public void checkViewShoppingCartLink(){
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
        driver.findElement(By.cssSelector(".header-minicart>a")).click();
        driver.findElement(By.cssSelector(".cart-link")).click();
        String expectedText = "SHOPPING CART";
        String actualText = driver.findElement(By.cssSelector(".page-title>h1")).getText();
        Assert.assertEquals(expectedText,actualText);
    }
    @Test
    public void checkCheckOutButtonOnCartDropDown(){
        homePage.clickAccountButton();
        homePage.clickLoginLink();
        loginPage.setEmailField("cristivasile-code@gmail.com");
        loginPage.setPasswordField("123456");
        loginPage.clickButton();
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
    //@After
    public void quit(){
        driver.close();
    }


    //ma duc pana la checkout, cu plata
}
