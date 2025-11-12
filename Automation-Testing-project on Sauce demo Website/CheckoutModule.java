//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.By;
//
//public class CheckoutModule {
//    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.edgedriver().setup();
//        WebDriver driver = new EdgeDriver();
//        driver.manage().window().maximize();
//
//        driver.get("https://www.saucedemo.com/");
//        // login
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        Thread.sleep(2000);
//        driver.findElement(By.id("login-button")).click();
//
//        //Add product to cart
//        Thread.sleep(2000);
//        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
//        // Open Cart
//        Thread.sleep(2000);
//        driver.findElement(By.className("shopping_cart_link")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("checkout")).click();
//        Thread.sleep(1000);
//        // Enter checkout info
//        driver.findElement(By.id("first-name")).sendKeys("Menna");
//        driver.findElement(By.id("last-name")).sendKeys("Tarek");
//        driver.findElement(By.id("postal-code")).sendKeys("12345");
//        Thread.sleep(2000);
//        driver.findElement(By.id("continue")).click();
//        Thread.sleep(2000);
//        // Click Finish
//        driver.findElement(By.id("finish")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("back-to-products")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("react-burger-menu-btn")).click();
//        Thread.sleep(1000);
//        //Logout
//        driver.findElement(By.id("logout_sidebar_link")).click();
//        Thread.sleep(1000);
//        System.out.println("Checkout completed successfully");
//        driver.quit();
//    }
//}
//-----------------------------------------------------------------------------------

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutModule {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
    }
    //  Test Case 1: Missing Postal Code
    @Test(priority = 1)
    public void testMissingPostalCode() throws InterruptedException {
        System.out.println("\n--- Test 1: Missing Postal Code ---");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Menna");
        driver.findElement(By.id("last-name")).sendKeys("Tarek");
        driver.findElement(By.id("postal-code")).sendKeys(""); // Postal code missing
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertEquals(errorMsg, "Error: Postal Code is required");
        System.out.println(" Error message displayed correctly: " + errorMsg);
        // Back to Products + Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
    }
    //  Test Case 2: Missing First Name
    @Test(priority = 2)
    public void testMissingFirstName() throws InterruptedException {
        System.out.println("\n--- Test 2: Missing First Name ---");
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys(""); // First name missing
        driver.findElement(By.id("last-name")).sendKeys("Tarek");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertEquals(errorMsg, "Error: First Name is required");
        System.out.println(" Error message displayed correctly: " + errorMsg);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
    }
    //  Test Case 3: Missing Last Name
    @Test(priority = 3)
    public void testMissingLastName() throws InterruptedException {
        System.out.println("\n--- Test 3: Missing Last Name ---");
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Menna");
        driver.findElement(By.id("last-name")).sendKeys(""); // Last name missing
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assert.assertEquals(errorMsg, "Error: Last Name is required");
        System.out.println(" Error message displayed correctly: " + errorMsg);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
    }
    //  Test Case 4: Successful Checkout
    @Test(priority = 4)
    public void testSuccessfulCheckout() throws InterruptedException {
        System.out.println("\n--- Test 4: Successful Checkout ---");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("first-name")).sendKeys("Menna");
        driver.findElement(By.id("last-name")).sendKeys("Tarek");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(1000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();
        Thread.sleep(1000);
        String successText = driver.findElement(By.className("complete-header")).getText();

        Assert.assertEquals(successText, "Thank you for your order!");
        System.out.println(" Checkout completed successfully with message: " + successText);
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


