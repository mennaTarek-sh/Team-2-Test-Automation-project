import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class AddtoCartModule {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        // login
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        //Add product to cart
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        System.out.println("Product 'Sauce Labs Backpack' added successfully!");
        Thread.sleep(2000);
        driver.findElement(By.id("item_1_title_link")).click();
        System.out.println("Product 'Sauce Labs Bolt T-Shirt' added successfully!");
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart")).click();
        Thread.sleep(1000);
        // Go to cart and continue shopping
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("continue-shopping")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@data-test='inventory-item-sauce-labs-bike-light-img']")).click();
        System.out.println("Product 'Sauce Labs Bike Light' added successfully!");
        Thread.sleep(1000);
        driver.findElement(By.id("add-to-cart")).click();
        Thread.sleep(1000);
        // back
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        //Logout
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
        System.out.println("Add To Cart module completed successfully!");
        driver.quit();



    }
}

