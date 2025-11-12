import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ResetAppStateModule {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        // login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        // Add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        System.out.println("Product 1 added to cart!");
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        System.out.println("Product 2 added to cart!");
        Thread.sleep(1000);

        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);

        //   Reset App State
        driver.findElement(By.id("reset_sidebar_link")).click();
        System.out.println("App state reset successfully!");
        Thread.sleep(2000);

        // Cart is now empty
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        System.out.println("Cart is now empty after reset.");

        // logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        System.out.println("Logout successful!");

        driver.quit();
    }
}

