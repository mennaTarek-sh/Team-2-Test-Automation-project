import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FooterModule {
    public static void main(String[] args) throws Exception {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
        // Scroll to footer
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("footer_copy")));
//      ننزل للعنصر
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        Thread.sleep(1000);
//        عشان لما نفتح Tabs جديدة نرجع لها تاني.
        String mainWindow = driver.getWindowHandle();

        String[] socialSelectors = {
                "a[href='https://twitter.com/saucelabs']",
                "a[href='https://www.facebook.com/saucelabs']",
                "a[href='https://www.linkedin.com/company/sauce-labs/']"
        };

        for (String selector : socialSelectors) {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
            link.click();

            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    System.out.println("Opened URL: " + driver.getCurrentUrl());
                    driver.close();
                    driver.switchTo().window(mainWindow);
                }
            }
        }
        Thread.sleep(1000);
          //logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
        Thread.sleep(1000);
        driver.quit();
    }
}
