import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class SortBoxModule {
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
        Thread.sleep(1000);
        //  Sort Box
        String[] filters = {"az", "za", "lohi", "hilo"};

        for (String filterValue : filters) {
            WebElement dropdownElement = driver.findElement(By.className("product_sort_container"));
            Select sortDropdown = new Select(dropdownElement);
            sortDropdown.selectByValue(filterValue);
            Thread.sleep(2000);
        }
          // logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);

        System.out.println("Sort Box test completed successfully!");
        driver.quit();
    }
}
